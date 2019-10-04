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
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String listTeachers(Model model) {

        List<Teacher> teacherList = teacherService.getTeachersList();

        teacherList.forEach(tcs -> {
            tcs.setGroups(new ArrayList<>(new HashSet<>(tcs.getGroups())));
        });

        model.addAttribute("teachers", teacherList);

        return "teachers/list-teachers";
    }

    @GetMapping("/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("groups", groupService.getGroupsList());
        return "teachers/show-teacher-form";

    }

    @PostMapping("/processform")
    public String processTeacherForm(Model model, @Valid @ModelAttribute("teacher") Teacher newTeacher, BindingResult result) {

        if (result.hasErrors()) {

            System.out.println(result.getAllErrors());

            model.addAttribute("teacher", newTeacher);
            return "teachers/show-teacher-form";

        } else {

            if (newTeacher.getId() == null) {

                teacherService.add(newTeacher);
            } else {
                teacherService.update(newTeacher);
            }

            model.addAttribute("teachers", teacherService.getTeachersList());

            return "redirect:/teachers/";
        }
    }

    @GetMapping("/update/{Id}")
    public String updateTeacher(Model model, @PathVariable Long Id) {

        Teacher teacher = teacherService.findById(Id);

        teacher.setGroups(new ArrayList<>(new HashSet<>(teacher.getGroups())));

        model.addAttribute("teacher", teacher);

        //isUpdate marker
        model.addAttribute("update", true);


        List<Group> thisTeacherGroups = teacher.getGroups();

        List<Group> allGroups = groupService.getGroupsList();

        allGroups.removeAll(thisTeacherGroups);

/*
       int asSize = allStudents.size();

        //Формируем студентов, которых нет в этой группе
        for (int i = 0; i < thisGroupStudents.size(); i++) {
            for (int j = 0; j < asSize; j++) {
                if ((thisGroupStudents.get(i).getId().equals(allStudents.get(j).getId()))){
                    asSize--;
                    allStudents.remove(j);
                }
            }
        }
        */


        //todo make a notInThisTeacherGroups

        model.addAttribute("groups", allGroups);

        return "teachers/show-teacher-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model, @PathVariable Long Id) {
        teacherService.delete(Id);
        model.addAttribute("teachers", teacherService.getTeachersList());
        return "redirect:/teachers/";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dateFormat.setLenient(true);
        //  dataBinder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(dateFormat, true));


        //teacherService
        dataBinder.registerCustomEditor(Date.class, "dateOfBirth", new DateEditor(teacherService));



        //dataBinder.registerCustomEditor(String.class, new DateEditor());


        //new DateCustomEditor
      /*  dataBinder.registerCustomEditor(Date.class,"dateOfBirth", new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch(ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());
            }

        });

    }*/

        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));
    }
}