package controller;

import com.google.gson.Gson;
import entities.Group;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.EntitiesService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private EntitiesService<Student> studentService;

    @Autowired
    private EntitiesService<Group> groupService;

    //todo переделать в модель энд вью
    @GetMapping//http://localhost:8081/students?page=1 так чтобы было
    public String listStudents(Model model, @RequestParam(value = "page") int page){
        int size = 10;
        model.addAttribute("students", studentService.getListWithPagination(((page-1) * size) ,size));
        model.addAttribute("page", page);
        return "students/list-students";//вьюшка list-students.jsp
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("student", new Student());
        //добавляем группы
        model.addAttribute("groups", groupService.getList());
        return "students/show-student-form";
    }

    @PostMapping("/processform")
    public String processStudentForm(Model model, @Valid @ModelAttribute("student") Student newStudent, BindingResult result){

        //Если есть ошибки при валидации
        if (result.hasErrors()){

                    //выводим все ошибки
                    System.out.println(result.getAllErrors());

                        //добавляем то, что есть, и перезагружаем форму

                        model.addAttribute("student", newStudent);
                        return "students/show-student-form";

                } else {

                        if (newStudent.getId() == null){
                            //добавляем нового студента

                            if (newStudent.getGroup()!=null){

                            Group tempGroup = groupService.findById(newStudent.getGroup().getId());

                            newStudent.setGroup(null);

                            studentService.add(newStudent);


                           tempGroup.addStudent(newStudent);

                          newStudent.setGroup(tempGroup);

                            studentService.update(newStudent);

                            } else studentService.add(newStudent);



                        }
                        else
                            studentService.update(newStudent);

            /* STUDENT TO GROUP*/


            Group currentGroup = newStudent.getGroup();

            //если у студента есть группа
            if (currentGroup!=null) {
                //добавили студента в группу
                currentGroup.addStudent(newStudent);
                //обновили группу
                groupService.update(currentGroup);

            }
            /* /STUDENT TO GROUP*/

            model.addAttribute("students", studentService.getList());

                    return "redirect:/students/";//Редирект чтобы не открывался сам процессформ
                }
    }

    //Обработка исключений не найдена страница
    @GetMapping("/update/{Id}")
    public String updateStudent(Model model, @PathVariable Long Id){

        //находим студента по ID
        Student student = studentService.findById(Id);

        model.addAttribute("student", student);

        model.addAttribute("update", true);

        //добавляем группы
        model.addAttribute("groups", groupService.getList());

        return "students/show-student-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model, @PathVariable Long Id) {
        //удаляем группу по ID
        studentService.delete(Id);
        model.addAttribute("students", studentService.getList());

        return "redirect:/students?page=1";//todo редиректить на ссылку pageFrom, откуда пришли
    }

    //Контроллер для пересылки Ajax студентам
    @PostMapping(value = "/ajaxgroup", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String processAjaxGroup(@RequestBody(required = false) String searchString) {
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
        /*
        1) создаем поле в сущности с определенным классом - тип поля
        2) как называется поле
        3) формат-даты
        */
        //, "dateOfBirth", - field
        //без указания поля будет обрабатывать все поля типа Date
        dataBinder.registerCustomEditor(Date.class, new DateEditor());

        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));
    }

}