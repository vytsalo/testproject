package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Контроллер ошибок
@Controller
public class HTTPErrorHandler{

    /*
    Коды ошибок
    4** - ошибка клиента
    5** - ошибка сервера
    */

    //Ошибка 400 - Некорректный запрос(Что-то пошло не так)
    @RequestMapping(value="/400")
    public String error400(){
        return "errors/error400";
    }

    //Ошибка 404 - Страница не найдена
    @RequestMapping(value="/404")
    public String error404(){
        return "errors/error404";
    }

    //Ошибка 500 - Сущность с ID таким - то не найдена
    @RequestMapping(value="/500")
    public String error500(){
        return "errors/error500";
    }

}