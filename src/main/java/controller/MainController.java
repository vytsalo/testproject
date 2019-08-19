package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//http://localhost:8082/

//Контроллеры для каждого отдельного класса
//Главная страница, юзеры и т.д.
@Controller
public class MainController {
//сделать классом
    @RequestMapping(value = "/")
    public String v_proj() {
        return "main";
    }

}