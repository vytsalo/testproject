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

        teacherList.forEach(teacher ->
                Collections.sort(teacher.getGroups(), Comparator.comparing(Group::getTitle))
        );
      /*  teacherList.forEach(tcs -> {
            //Collections.sort

            tcs.setGroups(Collections.sort(tcs.getGroups(), Comparator.comparing(Group::getTitle)));
        });*/

        model.addAttribute("teachers", teacherList);

        return "teachers/list-teachers";
    }

    @GetMapping("/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("groups", groupService.getList());
        return "teachers/show-teacher-form";
    }

    //таблица с фиксированными размерами и клиентским поиском
    @PostMapping(value = "/ajaxprocessform", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String processAjaxPage(Model model, @RequestBody(required = false) String searchString) {
        if (searchString==null) searchString="";

        //получаем списки
        List<Teacher> teacherList = teacherService.findByParam(searchString);

        //обнуляем списки групп, чтобы вывести
        teacherList.forEach(t -> t.setGroups(new ArrayList<>()));

        return new Gson().toJson(teacherList);

    }



    @PostMapping("/processform")
    public String processTeacherForm(Model model, @Valid @ModelAttribute("teacher") Teacher newTeacher, BindingResult result) {

        if (result.hasErrors()) {

            System.out.println(result.getAllErrors());

            model.addAttribute("teacher", newTeacher);
            return "teachers/show-teacher-form";

        } else {

            if (newTeacher.getId() == null) {

                /*

                Обнуляем список групп
                Добавляем препода без групп
                Добавляем всем группам этого препода
                Обновляем группы

                Устанавливаем преподу группы
                Обновляем препода


                 */


                //проблема в индексах?

                ArrayList<Group> thisTeacherGroups = new ArrayList<>(newTeacher.getGroups());

                newTeacher.setGroups(new ArrayList<>());

                teacherService.add(newTeacher);

                //индексы сломаны

                //передавать только тайтл?
                thisTeacherGroups.forEach(g -> {
                    g.addTeacher(newTeacher);
                    groupService.update(g);
                });

                newTeacher.setGroups(thisTeacherGroups);

                teacherService.update(newTeacher);

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
        List<Group> groupList = groupService.findByParam(searchString);

        //обнуляем списки преподов и студентов, чтобы вывести
        groupList.forEach(g -> {
            g.setTeachers(new ArrayList<>());
            g.setStudents(new ArrayList<>());
        });


        return new Gson().toJson(groupList);

    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dateFormat.setLenient(true);
        //  dataBinder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(dateFormat, true));


        //teacherService
        dataBinder.registerCustomEditor(Date.class,"dateOfBirth",  new DateEditor());

        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));
    }
}