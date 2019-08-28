package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


//http://localhost:8082/
//todo примеры удаленного:валидации и т.д. сделать отдельно
//Контроллеры для каждого отдельного класса
//Главная страница, юзеры и т.д.
@Controller
public class MainController {
//сделать классом
    @RequestMapping(value = "/")
    public String v_proj() {
        return "main";
    }


    @RequestMapping(value = {"/welcome**" }, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("testsecurity/hello");
        return model;

    }


    //todo spring security custom login page
    //todo difference between ModelAndView and String controller
    //todo передаю в модель и вью - не рест?


    //todo стиль логина сделать одинаковым и вынести в ксс

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("testsecurity/admin");

        return model;

    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView authTestPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("testsecurity/auth");

        return model;

    }


}