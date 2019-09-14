package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Контроллер ошибок
@Controller
public class HTTPErrorHandler{

    /*
    Коды ошибок
    4** -
    5** -
    */


    @RequestMapping(value="/400")
    public String error400(){
        return "errors/error400";//Что-то пошло не так
    }

    //Ошибка 404 -
    @RequestMapping(value="/404")
    public String error404(){
        return "errors/error404";//Страница не найдена
    }

    //Ошибка 500 -
    @RequestMapping(value="/500")
    public String error500(){
        return "errors/error500";//Кто-то с ID таким - то не найден
    }

}