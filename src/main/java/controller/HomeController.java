package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


//http://localhost:8009/
//http://localhost:8082/

@Controller
@SuppressWarnings("all")
public class HomeController {

    //value = "/"
    @RequestMapping(value = "/s", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {

        System.out.println("Home page requested, locale = " + locale);
        Date date = new Date();

        DateFormat dateFormat =
                DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);


        String formattedDate = dateFormat.format(date);

        model.addAttribute("ServerTime", formattedDate);

        return "home";//вьюшка
///
    }


    /*
    * по шагам
    * http://qaru.site/questions/1397251/tomcat-spring-integration-returning-404
    *
    *
    */

    //https://www.journaldev.com/3358/spring-requestmapping-requestparam-pathvariable-example
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    //@Validated who?
    public String posteacher(@Validated User user, Model model) {

        System.out.println("model.User Page Requested");
        model.addAttribute("userName", user.getUserName());

        return "user";//вьюшка

    }

    @RequestMapping(value = "/lala", method = RequestMethod.GET)
    @ResponseBody
    public String showSomething() {
        return "Hello world";
    }

    //ЛОгин
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login_page() {
        return "login";
    }


    //Страница списка всех групп

    @RequestMapping(value = "/groupslist", method = RequestMethod.GET)
    public String groups_page() {
        return "main";
    }


}
