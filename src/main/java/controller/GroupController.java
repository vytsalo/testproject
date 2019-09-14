package controller;

import entities.Group;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.GroupService;
import service.StudentService;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    //Нарушает правила ограничаения внешнего ключа

    @GetMapping("/")//"","/"
    public String listGroups(Model model){
        model.addAttribute("groups",groupService.getGroupsList());
        return "groups/list-groups";
    }

    @GetMapping("/add")
    public String addGroup(Model model){
        //добавить список групп
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("group", new Group());


        model.addAttribute("notInGroupStudents", studentService.getStudentsList());


        //добавить студентов всех как модель
        return "groups/show-group-form";
    }


    @PostMapping("/processform")
    public String processGroupForm(Model model, @ModelAttribute("group") Group newGroup, BindingResult result){

        if (result.hasErrors()){

            System.out.println(result.getAllErrors());

            model.addAttribute("group", newGroup);
            return "groups/show-group-form";

        } else {

            if (newGroup.getId() == null){

                //получили студентов, установили нулл, передали, потом вернули и заапдейтили

                //Получаем список студентов группы
                List<Student> studentsThisGroup = newGroup.getStudents();

                //Обнуляем студентов
                newGroup.setStudents(Collections.EMPTY_LIST);

                //добавляем в базу новую группу, чтобы получить id
                groupService.add(newGroup);


               /* //лямбдой
                for (int i = 0; i < studentsThisGroup.size(); i++) {
                    studentsThisGroup.get(i).setGruppa(newGroup);
                    studentService.update(studentsThisGroup.get(i));
                }*/
//todo загрузка логов и конфигов сразу

                studentsThisGroup.forEach(student -> {
                   student.setGruppa(newGroup);
                   studentService.update(student);
                });





                newGroup.setStudents(studentsThisGroup);

                groupService.update(newGroup);

            }
            else {

                //список студентов - группа одна
                //группа - студенты - список


                //Студенты, которые сейчас есть в базе
                List<Student> serviceStudents = groupService.findById(newGroup.getId()).getStudents();

                //получаем список студентов в модели
                List<Student> modelStudents = newGroup.getStudents();

                Student temp = null;

                //если не контейнс то группа нулл
                //Если студент был удален из группы, ставим ему группу нулл

                for (int i = 0; i < serviceStudents.size(); i++) {
                    if (!modelStudents.contains(serviceStudents.get(i))) {
                        temp = serviceStudents.get(i);
                        temp.setGruppa(null);
                        studentService.update(temp);
                    }
                }

                //Для каждого студента устанавливаем текущую группу
                for (int i = 0; i < modelStudents.size(); i++) {
                    temp = modelStudents.get(i);
                    temp.setGruppa(newGroup);
                    studentService.update(temp);
                }

                groupService.update(newGroup);

            }


            return "redirect:/groups/";//Редирект чтобы не открывался сам процессформ
        }
    }

    //выполняется метод, потом только страница получается. модель которую тут заполняем
    //в этой же страничке используется
    @GetMapping("/update/{Id}")
    public String updateGroup(Model model,@PathVariable Long Id){

        Group group = groupService.findById(Id);

        model.addAttribute("group", group);

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //добавляем группы
        //
        model.addAttribute("groups", groupService.getGroupsList());


        List<Student> allStudents = studentService.getStudentsList();

        List<Student> thisGroupStudents = group.getStudents();

       //если id равны то ремув из списка
        for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < thisGroupStudents.size(); j++) {
                if(allStudents.get(i).getId().equals(thisGroupStudents.get(j).getId())) {
                    allStudents.remove(allStudents.get(i));
                }
            }

        }

        if (!(allStudents.equals(null)))
            model.addAttribute("notInGroupStudents", allStudents);

        return "groups/show-group-form";


        //allStudents.remove(thisGroupStudents);


/*
        thisGroupStudents.forEach(tGS -> {
            if (tGS > 2) {
                System.out.println(tGS);
            }
        });

*/

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        groupService.delete(Id);
        model.addAttribute("groups",groupService.getGroupsList());
        return "redirect:/groups/";
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);//false;
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        //поле которое отвечает за группу
        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));


    }


}