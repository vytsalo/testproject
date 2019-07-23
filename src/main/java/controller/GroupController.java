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


    /*
    Spring Validation
    JavaScript Jquery Client Validation
    Masked Input JS
    Поменять дату стринг на дату
    Регулярками дату, телефон е-майл и ввод по шаблону (? (???) - ??? - ?? - ?? )

    */

    /*

    Единый ID - generationtype=sequence

    CSS add resource handlers

    баг с нажатием несколько раз - не баг

    Как сделать форму - like по всем полям, если есть совпадения

    */


    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String listgroup(Model model){
        model.addAttribute("groups", groupService.getGroupsList());
        return "groups/list-groups";
    }

    //Сделать файлик notes с замечаниями чего исправить или поменять
    @GetMapping("/add")
    public String addGroup(Model model) {

        //Просто передаем пустой экземпляр, который потом заполняем с помощью формы
        model.addAttribute("group", new Group());// - "group"

        //+ Логирование

        return "groups/show-group-form";//возвращает груп форм, а ссылается на процесс пост
    }

    //норм название дать actionform
    //редирект на список групп
    //подробные коменты всего
    //как сделать, чтобы напрямую его нельзя было вызвать - редирект?
    @PostMapping("/processform")
    public String processForm(Model model,@ModelAttribute("group") Group newGroup) {

        //Надо ли сортировку по алфавиту?

        //форма поиска
        //строка поиска

        /*
            Переходим на update - страницу
            считываем id
            заполняем данные в форму
            отправляем в пост метод
        */


        if (newGroup.getId()==null)
            groupService.add(newGroup);
        else
            groupService.update(newGroup);

        //надо ли?
        model.addAttribute("groups",groupService.getGroupsList());

        //и отправляет вьюшку
        return "groups/list-groups";
    }

//оптимизация всего контроллера


    @GetMapping("/update/{Id}")
    public String updateGroup(Model model,@PathVariable Long Id) {

        model.addAttribute("group", groupService.findById(Id));

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //        return "redirect:/viewemp";//will redirect to viewemp request mapping
        //        return "forward:/" - проброс
        return "groups/show-group-form";

        //какая разница между
        //"redirect:" + "/list"
        //и вызовом метода, если убрать считывание группы
    }


    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        groupService.delete(Id);
        model.addAttribute("groups",groupService.getGroupsList());
        return "groups/list-groups";

    }


    //-------TEST------


    @GetMapping("/test")
    public String testgroup(Model model){
        return "groups/index";
    }

}
