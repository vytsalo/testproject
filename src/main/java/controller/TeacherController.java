package controller;

import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TeacherService;

import static valid.Validation.isValid;


//на майн файле вывести структуру
@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public String listTeachers(Model model){

        model.addAttribute("teachers", teacherService.getTeachersList());

        return "teachers/list-teachers";

    }


    @GetMapping("/add")
    public String addTeacher(Model model){

        model.addAttribute("teacher", new Teacher());

        return  "teachers/show-teacher-form";

    }

    @PostMapping("/processform")
    public String processForm(Model model, @ModelAttribute("teacher") Teacher newTeacher){

        //Если прошли валидацию, то
        if (isValid(newTeacher))
            if (newTeacher.getId()==null)
                teacherService.add(newTeacher);
            else
                teacherService.update(newTeacher);

        model.addAttribute("teachers",teacherService.getTeachersList());

        return "teachers/list-teachers";

    }



    @GetMapping("/update/{Id}")
    public String updateTeacher(Model model,@PathVariable Long Id) {
        //избавиться
        model.addAttribute("teacher", teacherService.findById(Id));

        model.addAttribute("update", true);

        return "teachers/show-teacher-form";

    }


    @GetMapping("/delete/{Id}")
    public String deleteTeacher(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        teacherService.delete(Id);
        model.addAttribute("teachers",teacherService.getTeachersList());
        return "teachers/list-teachers";

    }



}