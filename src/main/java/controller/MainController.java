package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.GroupService;


//http://localhost:8082/
//todo примеры удаленного:валидации и т.д. сделать отдельно

//TODO MAKE A COMMON STYLE OF AUTH JSP PAGES.
// TODO MAKE A TABLES WITH STYLES IN FORMS
// TODO MAKE A TABLES WITH STYLES IN LISTS
// TODO LOGIN FORM IF USER IS NOT AUTHORISE
// TODO UL LI IN THE LIST?
// TODO REPLACE ALL LINKS TO C:URL


//TODO MAKE A GENERIC INTERFACES FOR GROUP STUDENTS AND TEACHERS DAO AND SERVICES

//TODO начать с самого начала от корня - сиюрл валю
//работает на любой странице
//<a href="<c:url value="/logout" />" >Выйти</a>

//TODO RENAME AUTH TO AUTH_SHAPKA


/*
1. заменить формат даты на удобный для пользователя СПРОСИТЬ
2. добавление студентов и преподавателей в группы сделать поиском - добавить строку поиска как?
4. после логаута отправлять пользователя на главную страницу - logout redirect +
5. локализовать страницы логина/логаута// Кнопка войти, если нет авторизации +
*/


//TODO как сделать поиском?

//TODO TEACHER'S ENTER THE SITE BUG
//HOW TO KNOW WHERE AM I?

//TODO hibernate search query all fields


@Controller
public class MainController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String mainPage(Model model){

        return "main";
    }

    //refactoring
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                                    Model model) {
        String errorMessge = null;

        if(error != null) {
            errorMessge = "Неверный логин или пароль";
        }

        model.addAttribute("errorMessge", errorMessge);
        return "testsecurity/login";
    }



    //ЛОГАУТ СУКЕСФУЛ ЛОГАУТ КОНФИГС

    //редиректит, но не выходит. передать валидный токен в кнопке логаут с вопроса о csrf подстверждении с
    //стаковерфлоу
    //https://howtodoinjava.com/spring5/security5/login-form-example/
    //https://www.mkyong.com/spring-security/spring-security-form-login-example/

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView authTestPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("testsecurity/auth");

        return model;

    }

}