package controller;

import entities.Group;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.GroupService;
import service.TeacherService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/")//"","/"
    public String listTeachers(Model model){
        model.addAttribute("teachers",teacherService.getTeachersList());
        return "teachers/list-teachers";//вьюшка teachers.jsp
    }

    @GetMapping("/add")
    public String addTeacher(Model model){
        //добавить список групп
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("teacher", new Teacher());

        //добавляем группы
        model.addAttribute("groups", groupService.getGroupsList());

        return "teachers/show-teacher-form";

    }


    @PostMapping("/processform")//valid
    public String processTeacherForm(Model model, @Valid @ModelAttribute("teacher") Teacher newTeacher, BindingResult result){

        if (result.hasErrors()){

            System.out.println(result.getAllErrors());

            model.addAttribute("teacher", newTeacher);
            return "teachers/show-teacher-form";

        } else {

            if (newTeacher.getId() == null){
                //взять те, что есть в базе и добавить
             //   List <Group> thisGroupsList = newTeacher.getGroups();

              //  newTeacher.setGroups(new ArrayList<>(Collections.EMPTY_LIST));

                teacherService.add(newTeacher);
            }
            else {
                teacherService.update(newTeacher);
            }

            model.addAttribute("teachers", teacherService.getTeachersList());

            return "redirect:/teachers/";
        }
    }

    @GetMapping("/update/{Id}")
    public String updateTeacher(Model model,@PathVariable Long Id){

        Teacher teacher = teacherService.findById(Id);

        model.addAttribute("teacher", teacher);

        //isUpdate
        model.addAttribute("update", true);

        model.addAttribute("groups", groupService.getGroupsList());

        return "teachers/show-teacher-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        teacherService.delete(Id);
        model.addAttribute("teachers",teacherService.getTeachersList());
        return "redirect:/teachers/";
    }

    //todo make a ModelandView name (difference between)
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        dataBinder.registerCustomEditor(Date.class, "date_of_birth", new CustomDateEditor(dateFormat, true));

        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));


    }

}