package controller;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.GroupService;
import service.StudentService;
import service.TeacherService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private TeacherService teacherService;

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

        model.addAttribute("notInGroupTeachers", teacherService.getTeachersList());

        return "groups/show-group-form";
    }


    @PostMapping("/processform")
    public String processGroupForm(Model model, @ModelAttribute("group") Group newGroup, BindingResult result){

        if (result.hasErrors()){

            System.out.println(result.getAllErrors());

            model.addAttribute("group", newGroup);

            return "groups/show-group-form";

        } else {

            /*получили студентов, установили нулл, передали, потом вернули и заапдейтили*/
            if (newGroup.getId() == null){
                //todo одинаковые потому что переменные такие же?
                //Получаем список студентов группы
                List<Student> studentsThisGroup = newGroup.getStudents();
                //Обнуляем студентов
                newGroup.setStudents(Collections.EMPTY_LIST);

                //Получаем список преподов этой группы
                List<Teacher> teachersThisGroup = newGroup.getTeachers();
                //Обнуляем преподов
                newGroup.setTeachers(Collections.EMPTY_LIST);


                //добавляем в базу новую группу, чтобы получить id
                groupService.add(newGroup);

                //todo загрузка логов и конфигов сразу
                //Назначаем всем студентам группу


                for (int i = 0; i < studentsThisGroup.size(); i++) {
                    studentsThisGroup.get(i).setGruppa(newGroup);
                    studentService.update(studentsThisGroup.get(i));
                }

            /*    studentsThisGroup.forEach(student -> {
                   student.setGruppa(newGroup);
                    //мб из-за этого?
                   studentService.update(student);
                });*/

               /* //Добавляем всем преподавателям эту группу
                teachersThisGroup.forEach(teacher -> {
                    //if (!(teacher.getGroups().contains(newGroup)))
                    teacher.addGroup(newGroup);
                    teacherService.update(teacher);
                });*/


               //TODO ПРЕПОДОВ ДОБАВЛЯЕТ 2 РАЗА СТУДЕНТОВ 3 РАЗА
                for (int i = 0; i < teachersThisGroup.size(); i++) {
                    for (int j = 0; j < teachersThisGroup.get(i).getGroups().size(); j++) {
                       // if (!(teachersThisGroup.get(i).getGroups().get(j).getId().equals(newGroup.getId()))) {

                            //if (!(teachersThisGroup.get(i).getGroups().contains(newGroup))) {
                                teachersThisGroup.get(i).addGroup(newGroup);
                                teacherService.update(teachersThisGroup.get(i));
                           // }
                        }
                    }




                //todo если добавляет то по 2 раза, а если пусто не добавляет вовсе

                //Добавляем студентов и преподавателей в эту группу
                newGroup.setStudents(studentsThisGroup);

                newGroup.setTeachers(teachersThisGroup);


                groupService.update(newGroup);

            }
            else {

                //todo где-то тут добавить проверку на

                /*
                    найти преподавателей по ID, добавить им эту группу, если её еще нет
                    обновить преподавателя
                    добавить преподавателей в группу, если их еще нет
                    обновить группу
                 */

                //получаем ID нашей группы
                Long idGroup = newGroup.getId();

                //Юзать это
                Group dbGroup = groupService.findById(idGroup);

                //список преподов которые есть в базе

                /* прямо здесь сделать выборку из того что есть
                *  совпадения + список групп
                *  */

                List<Teacher> allDBTeachers = dbGroup.getTeachers();

                List<Teacher> teachersThisGroup = newGroup.getTeachers();

                List<Teacher> allTeacherDatabase = teacherService.getTeachersList();

                Teacher tempT;
                List<Group> grT = new ArrayList<>();

                //todo проблема в методах add?
                //удаление преподов


                //удаляем эту группу у преподов, которые были там раньше но сейчас нет
                for (int i = 0; i < allDBTeachers.size(); i++) {
                    allDBTeachers.get(i).removeGroup(dbGroup);//newGroup
                    teacherService.update(allDBTeachers.get(i));
                }

                System.out.println(allDBTeachers);

                //todo поиск куда надо

                //тут ошибка при добавлении преподов в группу умножаются неудаленные

                //добавляем преподавателей в группы
                if (teachersThisGroup.size()!=0) {

                    for (int i = 0; i < allTeacherDatabase.size(); i++) {
                        for (int j = 0; j < teachersThisGroup.size(); j++) {
                            if (allTeacherDatabase.get(i).getId().equals(teachersThisGroup.get(j).getId())) {
                                tempT = teachersThisGroup.get(j);
                                grT = allTeacherDatabase.get(i).getGroups();
                                //важно, если не содержит группу, то добавляем ее
                                if (!(grT.contains(newGroup)))
                                grT.add(newGroup);
                                tempT.setGroups(new ArrayList<>(grT));
                                teachersThisGroup.set(j,tempT);
                                //teachersThisGroup.add(allTeacherDatabase.get(i));
                            }
                        }
                    }
                }

                //потом добавляем текущую группу


                System.out.println(teachersThisGroup);
                //найти по айди и засетить группы

/*

                if (allDBTeachers.size()!=0) {
                    for (int i = 0; i < allDBTeachers.size(); i++) {
                        for (int j = 0; j < modelTeacher.size(); j++) {
                            if (allDBTeachers.get(i).getId().equals(modelTeacher.get(j).getId())) {
                                teachersThisGroup.add(allDBTeachers.get(i));

                            }
                        }
                    }
                }*/
                System.out.println(teachersThisGroup);


 // newGroup.getTeachers();


                //получаем преподавателей для удаления из них группы
                allDBTeachers.removeAll(teachersThisGroup);


                Teacher tempTeacher;


                //на этом моменте уже группы 2!

                //todo перенести update на конец

                //проходимся по преподавателям в модели и добавляем им группу
                //обновляем преподавателей
                for (int i = 0; i < teachersThisGroup.size(); i++) {
                    tempTeacher = teachersThisGroup.get(i);
                    tempTeacher.addGroup(dbGroup);
                    teacherService.update(tempTeacher);
                }


                /* преподавателей тоже добавить в группу и группу в преподавателей*/


                //находим группу, ставим ей преподов и обновляем
                //Перезаписываем здесь, а надо добавлять


                dbGroup.setTeachers(teachersThisGroup);


                //groupService.update(dbGroup);

                System.out.println(teachersThisGroup);

                System.out.println(newGroup);


                //Студенты, которые сейчас есть в базе
                List<Student> serviceStudents = groupService.findById(newGroup.getId()).getStudents();

                //получаем список студентов в модели
                List<Student> modelStudents = newGroup.getStudents();

                Student temp = null;

                //если не контейнс то группа нулл
                //Если студент был удален из группы, ставим ему группу нулл
                for (int i = 0; i < serviceStudents.size(); i++) {
                    //temp тут чтоб короче
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

        //с самого начала отправляет два экземпляра(JSTL FIX)?
        //todo добавить только тех преподов, которых нет в группе
        //todo два раза добавляет из-за апдейта?
        Group group = groupService.findById(Id);

        model.addAttribute("group", group);

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //добавляем группы
        //
        model.addAttribute("groups", groupService.getGroupsList());


        /* Выбираем студентов, которых нет в группе */


        //Если не контейнс то добавляем


        //почему вывел 3 раза?
        List<Student> allStudents = studentService.getStudentsList();

        List<Student> thisGroupStudents = group.getStudents();

       //если id равны то ремув из списка

        int tempSize = thisGroupStudents.size();
        for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < tempSize; j++) {
                if(allStudents.get(i).getId().equals(thisGroupStudents.get(j).getId())) {
                    allStudents.remove(allStudents.get(i));
                    tempSize--;
                }
            }

        }

        if (!(allStudents.equals(null)))
            model.addAttribute("notInGroupStudents", allStudents);


/*
        //Если не контейнс то добавляем
        List<Teacher> allTeachers = teacherService.getTeachersList();

        List<Teacher> thisGroupTeachers = group.getTeachers();

        //если id равны то ремув из списка
        for (int i = 0; i < allTeachers.size(); i++) {
            for (int j = 0; j < thisGroupTeachers.size(); j++) {
                if(allTeachers.get(i).getId().equals(thisGroupTeachers.get(j).getId())) {
                    allTeachers.remove(allTeachers.get(i));
                }
            }

        }

        //

        if (!(allTeachers.equals(null)))
            model.addAttribute("notInGroupStudents", allTeachers);*/
//todo сделать везде одинаковый стиль гет тичерс и гет тичерслист


/*

        List<Teacher> allTeachers = teacherService.getTeachersList();

        List<Teacher> thisGroupTeachers = group.getTeachers();

        //если id равны то ремув из списка
        for (int i = 0; i < allTeachers.size(); i++) {
            for (int j = 0; j < thisGroupTeachers.size(); j++) {
                if(allTeachers.get(i).getId().equals(thisGroupTeachers.get(j).getId())) {
                    allTeachers.remove(allTeachers.get(i));
                }
            }

        }

        if (!(allStudents.equals(null))) {
            model.addAttribute("notInGroupTeachers", teacherService.getTeachersList());
        }
*/


        model.addAttribute("notInGroupTeachers", teacherService.getTeachersList());

        //todo remove unnecessary commentaries

        return "groups/show-group-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID

        //todo сделать удаление связей группы, а потом только удаление самой группы?

        //todo вернуть студентов в группы из security update commit
        Group group = groupService.findById(Id);

        //удаляем связи группы и преподавателей
        List<Teacher> thisGroupTeachers = group.getTeachers();

        //удаляем группу у преподавателя
        thisGroupTeachers.forEach(tGt ->{
            tGt.removeGroup(group);
            //заапдейтили, очистили список
            teacherService.update(tGt);
        });

        //удаляем всех преподавателей у группы
        group.setTeachers(Collections.emptyList());













        //удаляем связи группы и преподавателей
        List<Student> thisGroupStudents = group.getStudents();

        //удаляем группу у преподавателя
        thisGroupStudents.forEach(tGS ->{
            tGS.setGruppa(null);
            //заапдейтили, очистили список
            studentService.update(tGS);
        });

        //удаляем всех преподавателей у группы
        group.setStudents(Collections.emptyList());

        //заапдейтили, очистили список
        groupService.update(group);

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


        //todo не переделывает html? не убирает нейм?
        //dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));


    }


}