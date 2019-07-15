package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//http://localhost:8082/

//Контроллеры для каждого отдельного класса
@Controller
public class HomeController {



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login_page() {
        return "login";
    }

    //Страница списка всех групп
    //@RequestMapping(value = "/groupslist", method = RequestMethod.GET)
    @GetMapping(value = "/groupslist")
    public String groups_page() {
        return "main";
    }

    @RequestMapping(value = "/")
    public String v_proj() {
        return "moen";
    }

    @RequestMapping(value = "/s")
    @ResponseBody
    public String moen_rage() {
        return "Fandom cage";
    }

}