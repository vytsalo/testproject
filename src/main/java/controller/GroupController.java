package controller;

import com.google.gson.Gson;
import entities.Group;
import entities.Student;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.EntitiesService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
//todo remove this or replace to main controller

@RequestMapping("/groups")
public class GroupController {

    @Autowired
    //Квалифаер - id бина, который описали
    @Qualifier("groupService")
    EntitiesService<Group> groupService;

    @Autowired
    @Qualifier("teacherService")
    EntitiesService<Teacher> teacherService;

    @Autowired
    @Qualifier("studentService")

    EntitiesService<Student> studentService;

    @GetMapping("/")//"","/"
    public String listGroups(Model model){

        List<Group> groups = groupService.getList();


        groups.forEach(grp -> grp.setTeachers(new ArrayList<>(new HashSet<>(grp.getTeachers()))));


        model.addAttribute("groups",groups);
        return "groups/list-groups";
    }

    //todo если гетлист нулл то ретурт гет тичерслист
    @GetMapping("/add")
    public String addGroup(Model model){
        //добавить список групп
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("group", new Group());

        model.addAttribute("notInGroupStudents", studentService.getList());

        model.addAttribute("notInGroupTeachers", teacherService.getList());

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



                //TODO ERROR HANDLERS FIX AUTH
                //ERROR PAGES при неправильных ссылках все сбивается
                //http://212.193.37.103:8082/teachers/update/210/as
                //todo избавиться от сиаутвалю
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

                List<Teacher> allTeacherDatabase = teacherService.getList();

                //todo error in processform while sending the model
                //todo fix operations

                Teacher tempT;
                List<Group> grT = new ArrayList<>();//--

                //удаляем эту группу у преподов, которые были там раньше но сейчас нет
                for (int i = 0; i < allDBTeachers.size(); i++) {
                    allDBTeachers.get(i).removeGroup(dbGroup);//newGroup
                    teacherService.update(allDBTeachers.get(i));
                }

                  //todo поиск куда надо

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
                            }
                        }
                    }
                }

                //получаем преподавателей для удаления из них группы
                allDBTeachers.removeAll(teachersThisGroup);

                Teacher tempTeacher;

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

    //TODO get ride of / at the urls beginings
    //TODO find a way to remove / in jsp include and CSS links in showgroupform
    //TODO баг из -за связей 1 к мени и мени ту мени, проверить не увеличиваются ли они еще при передаче
    //TODO -/ not working in resources
    //TODO bug with main group page. conflict between "" and "groups/"
    //выполняется метод, потом только страница получается. модель которую тут заполняем
    //в этой же страничке используется
    @GetMapping("/update/{Id}")
    public String updateGroup(Model model,@PathVariable Long Id){

        Group group = groupService.findById(Id);

        group.setTeachers(new ArrayList<>(new HashSet<>(group.getTeachers())));
        group.setStudents(new ArrayList<>(new HashSet<>(group.getStudents())));

        model.addAttribute("group", group);
        model.addAttribute("update", true);
        model.addAttribute("groups", groupService.getList());


        List<Teacher> allTeachers = teacherService.getList();
        allTeachers.removeAll(group.getTeachers());

        List<Student> allStudents = studentService.getList();
        List<Student> thisGroupStudents = group.getStudents();

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

        //Добавляем студентов и преподавателей, которых нет в группе
        model.addAttribute("notInGroupTeachers", allTeachers);
        model.addAttribute("notInGroupStudents", allStudents);

        //todo remove unnecessary commentaries

        return "groups/show-group-form";
    }

    //удаляем группу по ID
    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {

        //todo сделать удаление связей группы, а потом только удаление самой группы?

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

        model.addAttribute("groups",groupService.getList());
        return "redirect:/groups/";
    }




    //нажиматься если она не емпти?

    //как получить ответ от сервера?

    //триггер надо какой-то для джаваскрипта написать?

//responseBody
    //https://devcolibri.com/spring-mvc-%D0%B8-ajax-%D0%B8%D0%BB%D0%B8-%D0%BA%D0%B0%D0%BA-%D1%8F-%D0%B1%D0%BE%D1%80%D0%BE%D0%BB%D1%81%D1%8F-%D1%81-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B5%D0%BD%D0%B8%D0%B5%D0%BC-json-%D0%BE%D1%82/
    //http://develnotes.org/article83750#.XaWsaEYzbct
    //вывод через js
    // https://ru.stackoverflow.com/questions/547558/%D0%9A%D0%B0%D0%BA-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B8%D1%82%D1%8C-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5-%D0%B8%D0%B7-json-%D0%B2-%D0%BA%D0%BE%D0%BD%D1%82%D1%80%D0%BE%D0%BB%D0%BB%D0%B5%D1%80%D0%B5-spring-mvc

    @PostMapping(value = "/ajaxprocessform", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    //почему добавляется равно
    //required false для того, чтобы не было бад рекуэста
    //response body allow null values
    //https://stackoverflow.com/questions/12934045/null-values-as-empty-strings-when-using-responsebody-annotation
    public String processAjaxPage(Model model, @RequestBody(required = false) String searchString) {
        if (searchString==null) searchString="";

        //получаем списки
        List<Teacher> slistItems = teacherService.findByParam(searchString);

        //обнуляем списки групп, чтобы вывести
        slistItems.forEach(t -> t.setGroups(new ArrayList<>()));

        return new Gson().toJson(slistItems);

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