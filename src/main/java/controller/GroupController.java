package controller;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.GroupService;


//логи программы(кастомные) в отдельный файл
@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //Список групп просто по ссылке
    @GetMapping("/list")
    public String listgroup(Model model){

        model.addAttribute("groups", groupService.getGroupsList());

        return "groups/list-groups";
    }









    @GetMapping("/add")
    public String addGroup(Model model) {
        System.out.println("in addGroup ");//удалить юзлесы, но добавить логирование
        Group group = new Group();//добавить сразу в форму сократить
        model.addAttribute(group);
        return "groups/show-group-form";//возвращает груп форм, а ссылается на процесс пост
    }

    //норм название дать actionform
    @PostMapping("/processform")//@Valid
    public String processForm(Model model,@ModelAttribute("Group") Group newGroup) {

        //делает дела
        //add and update

     /*   Если update = true то делаем
                groupService.update(newGroup);
        else groupService.add(newGroup);*/
        //форма поиска
        //строка поиска

        //null
       // groupService.add(newGroup);


        if (newGroup.getId()==null)
            groupService.add(newGroup);
        else
            groupService.update(newGroup);


      /* if (newGroup.getId().equals(null)){
            groupService.add(newGroup);
        } else {
            groupService.update(newGroup);
        }*/


      //парсить строку?



        // update or add
      /*  if (uoa.equals("u")){
            groupService.update(newGroup);
        } else groupService.add(newGroup);*/


        //id не может быть null
        //groupService.add(newGroup);
        //groupService.update(newGroup);




     /*   if (groupService.findById(newGroup.getId())!=null){
            System.out.println("update");
        } else System.out.println("add");*/



    /*    if (model.containsAttribute("update"))
            System.out.println("aosidas9idha=diha['sdha[shfaihfoia------------------------____________________");
*/




        //надо ли?
        model.addAttribute("groups",groupService.getGroupsList());


        System.out.println("in process form");

        //и отправляет вьюшку
        return "groups/list-groups";
    }


    //сделать не через ?= а http://localhost:8082/groups/update/2 вот так

    //объект находится, но с ним ничего не делается
    @GetMapping("/update")
    //обязательный параметр при обновлении
    //редактирование группы
    //http://localhost:8082/groups/update?GroupId=2
    //при апдейте добавляет
    public String updateGroup(Model model,@RequestParam("GroupId") Long Id) {

        //сделать считывание параметров id группы для редактирования и полей ввода

        model.addAttribute("group",groupService.findById(Id));

        //new
        //update

        //ключ есть long id передается в модель

        //-----
        model.addAttribute("update", true);

        return "groups/show-group-form";

    }

    //через гет с указанием id в адресной строке!
    //удаляет группу с айди 2
    //http://localhost:8082/groups/delete?GroupId=2
    @GetMapping("/delete")
    public String deleteGroup(Model model,@RequestParam("GroupId") Long Id) {

        //удаляем группу по ID
        groupService.delete(Id);

        //Выводим группы
        model.addAttribute("groups",groupService.getGroupsList());

        System.out.println("in process form");
        return "groups/list-groups";

    }
    
    


}
