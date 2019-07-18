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
    //поменять на @GetMapping("/")
    @GetMapping("/list")
    public String listgroup(Model model){

        model.addAttribute("groups", groupService.getGroupsList());

        return "groups/list-groups";
    }









    @GetMapping("/add")
    public String addGroup(Model model) {
        System.out.println("in addGroup ");//удалить юзлесы, но добавить логирование
        Group group = new Group();//добавить сразу в форму сократить


        //-----------------------------NEW------------------------
        model.addAttribute("group", group);// - "group"


        return "groups/show-group-form";//возвращает груп форм, а ссылается на процесс пост
    }

    //норм название дать actionform
    //редирект на список групп
    //подробные коменты всего
    @PostMapping("/processform")//@Valid                  //Group
    public String processForm(Model model,@ModelAttribute("group") Group newGroup) {

        //форма поиска
        //строка поиска




        /*
            Переходим на update - страницу
            считываем id
            заполняем данные в форму
            отправляем в пост метод
        * */


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("id=" + newGroup.getId());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();





       /* if (newGroup.getId().equals(null))
            groupService.add(newGroup);
        else
            groupService.update(newGroup);*/


       //сократить до унарной ?
        if (newGroup.getId()==null)
            groupService.add(newGroup);
        else
            groupService.update(newGroup);


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

    //поменять на
   /* @GetMapping("/update/{id}")
    public String updateGroup(Model model, @PathVariable Long Id)
*/
//ГАЙД ПО ПЕРЕДАЧЕ
 //produces = "application/json"

    public String updateGroup(Model model,@RequestParam("GroupId") Long Id) {//еще и модель?

        //сделать считывание параметров id группы для редактирования и полей ввода


       // Group group = new Group();//добавить сразу в форму сократить


        Group group = groupService.findById(Id);
        model.addAttribute("group", group);



//        model.addAttribute("group",groupService.findById(Id));


        //ключ есть long id передается в модель


        model.addAttribute("update", true);

        return "groups/show-group-form"; //"redirect:" + "/list"

    }









    //через гет с указанием id в адресной строке!
    //удаляет группу с айди 2
    //http://localhost:8082/groups/delete?GroupId=2
    //сделать так
    //http://localhost:8082/groups/delete/2
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
