package controller;

import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TeacherService;


//на майн файле вывести структуру
@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String listTeachers(Model model){

        model.addAttribute("teachers", teacherService.getTeachersList());

        return "teachers/list-teachers";

    }


    @GetMapping("/addteacher")
    public String addTeacher(Model model){
        System.out.println("in addTeacher");
        Teacher teacher = new Teacher();

        model.addAttribute(teacher);

        return  "teachers/show-teacher-form";

    }

    @PostMapping("/processform")
    public String processForm(Model model, @ModelAttribute("teacher") Teacher newTeacher){
        teacherService.add(newTeacher);

        model.addAttribute("teachers", teacherService.getTeachersList());

        return "teachers/list-teachers";

    }


    //Поменять параменты на адекватные не тичерайди а просто айди

    @GetMapping("/update")
    public String updateTeacher(Model model, @RequestParam("teacherId") Long Id){

        model.addAttribute("teacher", teacherService.findById(Id));

        return "teachers/show-teacher-form";

    }

    @GetMapping("/delete")
    //http://localhost:8082/teachers/delete?teacherId=2
    public String deleteTeacher(Model model, @RequestParam("teacherId") Long Id){
        teacherService.delete(Id);

        //проверки на нахождение?

        model.addAttribute("customers", teacherService.findById(Id));

        System.out.println("in process form");

        return "teachers/list-teachers";

    }



}