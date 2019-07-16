package controller;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.GroupService;

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
        System.out.println("in addGroup ");
        Group group=new Group();//добавить сразу в форму сократить
        model.addAttribute(group);
        return "groups/show-group-form";//возвращает груп форм, а ссылается на процесс пост
    }

    @PostMapping("/processform")//@Valid
    public String processForm(Model model,@ModelAttribute("Group") Group newGroup ) {

        groupService.add(newGroup);

        //display
        model.addAttribute("groups",groupService.getGroupsList());

        System.out.println("in process form");

        return "groups/list-groups";
    }


    //формируем ссылку по id как? текстовое поле?
    //объект находится, но с ним ничего не делается
    //к окне id или в форме
    @GetMapping("/update")
    //обязательный параметр при обновлении
    //редактирование группы
    //http://localhost:8082/groups/update?GroupId=2
    //при апдейте добавляет
    public String updateGroup(Model model,@RequestParam("GroupId") Long Id ) {

        //считывание параметров id группы для редактирования и полей ввода

        model.addAttribute("group",groupService.findById(Id));

        //new
        //groupService.update(groupService.findById(Id));

        return "groups/show-group-form";

    }

    //через гет с указанием id в адресной строке?
    //через что выводить id!


    //удаляет группу с айди 2
    //http://localhost:8082/groups/delete?GroupId=2

    //как сделать в поле вывод или через строку
    @GetMapping("/delete")
    public String deleteGroup(Model model,@RequestParam("GroupId") Long Id) {

        //adding logic here
        groupService.delete(Id);

        //display
        model.addAttribute("groups",groupService.getGroupsList());

        System.out.println("in process form");
        return "groups/list-groups";

    }
    
    


}
