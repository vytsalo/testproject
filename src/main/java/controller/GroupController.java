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

    /*

    Как сделать форму - like по всем полям, если есть совпадения

    */

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String listgroup(Model model){
        model.addAttribute("groups", groupService.getGroupsList());
        return "groups/list-groups";
    }

    @GetMapping("/add")
    public String addGroup(Model model) {

        //Просто передаем пустой экземпляр, который потом заполняем с помощью формы
        model.addAttribute("group", new Group());// - "group"

        //Вместе с моделью отправляет сообщение об ошибке

        return "groups/show-group-form";//возвращает груп форм, а ссылается на процесс пост
    }

    //норм название дать actionform
    //подробные коменты всего
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

        //не добавлять аттрибут?

        //Если прошли валидацию, то
            if (newGroup.getId()==null)
                groupService.add(newGroup);
            else
                groupService.update(newGroup);
        //надо ли?
        model.addAttribute("groups",groupService.getGroupsList());

        //и отправляет вьюшку
        return "groups/list-groups";
        //Если валидация не прошла, ретурнить другую вью?
    }

    //todo fix log4j

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

}
