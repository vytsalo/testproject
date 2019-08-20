package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Контролеер ошибок
@Controller
public class HTTPErrorHandler{

    @RequestMapping(value="/400")//razvodit rukami
    public String error400(){
        return "errors/error400";//Что-то пошло не так
    }

    //todo make a new pictures for each error page
    @RequestMapping(value="/404")
    public String error404(){
        // DO stuff here
        return "errors/error404";//Страница не найдена
    }

    @RequestMapping(value="/500")
    public String error500(){
        return "errors/error500";//Кто-то с ID таким - то не найден
    }

}