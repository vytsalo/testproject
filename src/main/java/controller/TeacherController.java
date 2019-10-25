package controller;

import com.google.gson.Gson;
import entities.Group;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.EntitiesService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private EntitiesService<Teacher> teacherService;

    @Autowired
    private EntitiesService<Group> groupService;

    @GetMapping("/")
    public String listTeachers(Model model) {

        List<Teacher> teacherList = teacherService.getList();

        teacherList.forEach(tcs -> {
            tcs.setGroups(new ArrayList<>(new HashSet<>(tcs.getGroups())));
        });

        model.addAttribute("teachers", teacherList);

        return "teachers/list-teachers";
    }

    @GetMapping("/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("groups", groupService.getList());
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

            model.addAttribute("teachers", teacherService.getList());

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

        List<Group> allGroups = groupService.getList();

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
        model.addAttribute("teachers", teacherService.getList());
        return "redirect:/teachers/";
    }



    //Контроллер для пересылки Ajax
    @PostMapping(value = "/ajaxgroup", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String processAjaxGroup(Model model, @RequestBody(required = false) String searchString) {
        if (searchString==null) searchString="";

        //получаем списки групп, удовлетворяющих параметру
        List<Group> glistItems = groupService.findByParam(searchString);

        //обнуляем списки преподов и студентов, чтобы вывести
        glistItems.forEach(g -> {
            g.setTeachers(new ArrayList<>());
            g.setStudents(new ArrayList<>());
        });

        return new Gson().toJson(glistItems);

    }





    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dateFormat.setLenient(true);
        //  dataBinder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(dateFormat, true));


        //teacherService
        dataBinder.registerCustomEditor(Date.class,"dateOfBirth",  new DateEditor());



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