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

    //todo загрузка логов и конфигов сразу

    @Autowired
    private GroupService groupService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/")//"","/"
    public String listGroups(Model model){
        model.addAttribute("groups",groupService.getGroupsList());
        return "groups/list-groups";
    }
    //TODO сделать хешсетом а не арейлистом



    //todo если гетлист нулл то ретурт гет тичерслист
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

                //группы не добавляет, просто взять группы из старой базы и добавить к ним

                //Назначаем всем студентам группу
                Student tempStudent;
                for (int i = 0; i < studentsThisGroup.size(); i++) {
                    tempStudent = studentsThisGroup.get(i);
                    tempStudent.setGruppa(newGroup);
                    studentService.update(tempStudent);
                }

              //у каждого студента, которого передали берем айди и находим в базе список его групп

                List<Group> tempListGroups;
                for (int i = 0; i < teachersThisGroup.size(); i++) {
                    //получили группы, которые есть у преподавателя
                    tempListGroups = teacherService.findById(teachersThisGroup.get(i).getId()).getGroups();
                    //проверку сделать если контейнс
                    tempListGroups.add(newGroup);
                    teachersThisGroup.get(i).setGroups(new ArrayList<>(tempListGroups));
                    teacherService.update(teachersThisGroup.get(i));
                }

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

                List<Teacher> allDBTeachers = dbGroup.getTeachers();

                List<Teacher> teachersThisGroup = newGroup.getTeachers();

                List<Teacher> allTeacherDatabase = teacherService.getTeachersList();

                //todo error in processform while sending the model
                //todo fix operations

                Teacher tempT;
                List<Group> grT = new ArrayList<>();// --

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
                    if (!(modelStudents.contains(serviceStudents.get(i)))) {
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
    //не понятно, от чего зависит
    //только одни данные заполняются - все норм
    //два - сразу, или через какое - то время начинается дублирование инфы, добавляется повторно
    @GetMapping("/update/{Id}")
    public String updateGroup(Model model,@PathVariable Long Id){

        //с самого начала отправляет два экземпляра(JSTL FIX)?
        //todo добавить только тех преподов, которых нет в группе
        //todo два раза добавляет из-за апдейта?
        Group group = groupService.findById(Id);

        //При редактировании с 0 групп и 0 преподов выводит тоже нули

        //добавляет в базу 1 студента, а выводит 2 в модели
        //в бд тоже 2 через groupService.findById(new Long(651))

        model.addAttribute("group", group);
        model.addAttribute("update", true);
        model.addAttribute("groups", groupService.getGroupsList());


        //TODO view error fix

        List<Teacher> allTeachersList = teacherService.getTeachersList();

        List<Teacher> thisGroupTeachersList = group.getTeachers();
        List<Teacher> notInThisGroupTeacher = new ArrayList<>();

        if (thisGroupTeachersList.size()==0) {notInThisGroupTeacher = allTeachersList;}
        else {

            int tmpSize = thisGroupTeachersList.size();

            //просто ремувами
            for (int i = 0; i < allTeachersList.size(); i++) {

                for (int j = 0; j < tmpSize; j++) {

                    if (!(allTeachersList.get(i).getId().equals(thisGroupTeachersList.get(j).getId()))) {
                        notInThisGroupTeacher.add(allTeachersList.get(i));
                    }
                    break;
                }
            }
        }

        model.addAttribute("notInGroupTeachers", notInThisGroupTeacher);





//После добавления преподавателей или студентов, все умножается. Если что-то одно, то норм
//на груплист отображается норм, а там нет
//после редактирования и отправки и студентов и преподов, получается вот это




        //3 раза один и тот же студент. пофиксить?

        /* Выбираем студентов, которых нет в группе */
        List<Student> allStudents = studentService.getStudentsList();
        List<Student> thisGroupStudents = group.getStudents();


   /*     for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < thisGroupStudents.size(); j++) {
                if (allStudents.get(i).getId().equals(thisGroupStudents.get(j).getId()))
                    allStudents.remove(i);
            }
        }*/

    allStudents.removeAll(thisGroupStudents);


       System.out.println(allStudents);
        /*
       //если id равны то ремув из списка
      int tempSize = thisGroupStudents.size();
        int tempSise = allStudents.size();
        for (int i = 0; i < tempSise; i++) {
            for (int j = 0; j < tempSize; j++) {
                if(allStudents.get(i).getId().equals(thisGroupStudents.get(j).getId())) {
                    allStudents.remove(allStudents.get(i));
                    tempSize--;
                    tempSise--;

                }
            }
        }*/

        //убрать проверку
        //if (!(allStudents.equals(null)))
            model.addAttribute("notInGroupStudents", allStudents);


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