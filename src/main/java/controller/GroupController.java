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
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
                studentsThisGroup.forEach(student -> {
                   student.setGruppa(newGroup);
                   studentService.update(student);
                });

                //Добавляем всем преподавателям эту группу
                teachersThisGroup.forEach(teacher -> {
                    teacher.addGroup(newGroup);
                    teacherService.update(teacher);
                });

                //todo если добавляет то по 2 раза, а если пусто не добавляет вовсе

                //Добавляем студентов и преподавателей в эту группу
                newGroup.setStudents(studentsThisGroup);

                newGroup.setTeachers(teachersThisGroup);


                groupService.update(newGroup);

            }
            else {

                /*
                    найти преподавателей по ID, добавить им эту группу, если её еще нет
                    обновить преподавателя
                    добавить преподавателей в группу, если их еще нет
                    обновить группу
                 */

                //то, что есть в модели
                Long idGroup = newGroup.getId();

                //serviceTeacher
                //список преподов которые есть в базе
                List<Teacher> allTeachers = groupService.findById(idGroup).getTeachers();

                //список преподов в модели
                List<Teacher> teachersThisGroup = newGroup.getTeachers();

                newGroup.setTeachers(Collections.EMPTY_LIST);


                //удаление
                //разность списков которые в модели и те, что в бд
                //где не совпадают, удаляем


                Teacher tempTeacher;

                Group dbTempGroup = groupService.findById(idGroup);
/*
                for (int i = 0; i < allTeachers.size(); i++) {
                    //если не содержит, то удаляем
                    tempTeacher = allTeachers.get(i);
                    if (!teachersThisGroup.contains(tempTeacher)){

                        tempTeacher.removeGroup(dbTempGroup);//findById newGroup
                        teacherService.update(tempTeacher);

                        dbTempGroup.deleteTeacher(tempTeacher);
                        groupService.update(dbTempGroup);

                    }
                }
*/

                for (int i = 0; i < teachersThisGroup.size(); i++) {
                    for (int j = 0; j < allTeachers.size(); j++) {
                        if (teachersThisGroup.get(i).getId().equals(allTeachers.get(j).getId())) { //equals
                                //сначала группу у преподавателя

                                //tempTeacher

                                //сначала тичеров удаляем, потом группу
                                teachersThisGroup.get(i).removeGroup(dbTempGroup);
                                teacherService.update(teachersThisGroup.get(i));


                                dbTempGroup.deleteTeacher(teachersThisGroup.get(i));//teachersThisGroup.get(i)

                                groupService.update(dbTempGroup);
                        }
                    }
                }

                //и у группы удалить тичера







                //для каждого списка студентов добавляем эту (текущую) группу
                teachersThisGroup.forEach(teacher -> {
                    teacher.addGroup(newGroup);
                    teacherService.update(teacher);
                });

                newGroup.setTeachers(teachersThisGroup);


              /*
                //Преподаватели, которые сейчас есть в базе
                List<Teacher> serviceTeachers = groupService.findById(newGroup.getId()).getTeachers();

                //получаем список преподов в модели
                List<Teacher> modelTeachers = newGroup.getTeachers();

                Teacher tempTeacher = null;

                //Сейчас 1 группа
                //Если препод был удален из группы, удаляем группу из препода

                for (int i = 0; i < serviceTeachers.size(); i++) {

                    tempTeacher = serviceTeachers.get(i);

                    if (!modelTeachers.contains(tempTeacher)) {

                        //removeGroup
                        tempTeacher.removeGroup(newGroup);

                        teacherService.update(tempTeacher);
                    }
                }

                //Каждому преподу, который есть в модели добавляем текущую группу
                for (int i = 0; i < modelTeachers.size(); i++) {
                    tempTeacher = modelTeachers.get(i);
                    tempTeacher.addGroup(newGroup);
                    teacherService.update(tempTeacher);
                }


                /*REDACTION*/


                //список тичеров по id
                //проперти эдитор


                /*
                почему мы группу передавали по id
                а студентов и преподов через коллекцию

                */



                //в контроллере получать список групп по id
                //
                //
                //this group
               // for (int i = 0; i < 5; i++) {

                    //check for coincidence
                   // groupService.findById(new Long(5)).getTeachers().get(i);


               // }
                //teacherService.findById(new Long(5));













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
        for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < thisGroupStudents.size(); j++) {
                if(allStudents.get(i).getId().equals(thisGroupStudents.get(j).getId())) {
                    allStudents.remove(allStudents.get(i));
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

            model.addAttribute("notInGroupTeachers", teacherService.getTeachersList());

//group.getTeachers();


        return "groups/show-group-form";

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


        //todo не переделывает html? не убирает нейм?
        //dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));


    }


}