package controller;

import entities.Teacher;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@SuppressWarnings("all")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    //@Validated who?
    public String posteacher(@Validated User user, Model model) {

        System.out.println("model.User Page Requested");
        model.addAttribute("userName", user.getUserName());

        return "user";//вьюшка

    }
}
