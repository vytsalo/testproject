package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Контролеер ошибок
@Controller
public class HTTPErrorHandler{

    @RequestMapping(value="/404")
    public String error404(){
        // DO stuff here
        return "errors/error404";//Страница не найдена
    }

    @RequestMapping(value="/400")
    public String error400(){
        return "errors/error400";//Кто-то с ID таким - то не найден
    }

}