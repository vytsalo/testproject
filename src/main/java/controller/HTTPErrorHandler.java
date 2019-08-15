package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Рабочее 404
@Controller
public class HTTPErrorHandler{

    @RequestMapping(value="/404")
    public String error404(){
        // DO stuff here
        return "errors/error404";//Страница не найдена
    }
}