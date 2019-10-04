package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.GroupService;


//http://localhost:8082/
//todo примеры удаленного:валидации и т.д. сделать отдельно

//TODO MAKE A COMMON STYLE OF AUTH JSP PAGES.
// TODO MAKE A TABLES WITH STYLES IN FORMS
// TODO MAKE A TABLES WITH STYLES IN LISTS
// TODO LOGIN FORM IF USER IS NOT AUTHORISE
// TODO UL LI IN THE LIST?
// TODO

//TODO начать с самого начала от корня - сиюрл валю
//работает на любой странице
//<a href="<c:url value="/logout" />" >Выйти</a>

/*
1. заменить формат даты на удобный для пользователя +-
2. добавление студентов и преподавателей в группы сделать поиском - добавить строку поиска
4. после логаута отправлять пользователя на главную страницу - logout redirect +
5. локализовать страницы логина/логаута// Кнопка войти, если нет авторизации
*/

//TODO TEACHER'S ENTER THE SITE BUG
//HOW TO KNOW WHERE AM I?

@Controller
public class MainController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String listGroups(Model model){

        return "main";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView authTestPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("testsecurity/auth");

        return model;

    }

}