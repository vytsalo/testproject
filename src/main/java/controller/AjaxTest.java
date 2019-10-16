package controller;

import com.google.gson.Gson;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TeacherService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilevvs on 15.10.2019.
 */
@Controller
public class AjaxTest {

    @Autowired
    TeacherService teacherService;


    @GetMapping("/ajaxtest")
    public String ajaxPage(Model model, @ModelAttribute("searchString") String searchString){

        model.addAttribute("searchString",searchString);
        return "test/ajax";

    }


    //нажиматься если она не емпти?


    //как получить ответ от сервера?

    //триггер надо какой-то для джаваскрипта написать?

//responseBody
    //https://devcolibri.com/spring-mvc-%D0%B8-ajax-%D0%B8%D0%BB%D0%B8-%D0%BA%D0%B0%D0%BA-%D1%8F-%D0%B1%D0%BE%D1%80%D0%BE%D0%BB%D1%81%D1%8F-%D1%81-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B5%D0%BD%D0%B8%D0%B5%D0%BC-json-%D0%BE%D1%82/
    //http://develnotes.org/article83750#.XaWsaEYzbct
    //вывод через js
   // https://ru.stackoverflow.com/questions/547558/%D0%9A%D0%B0%D0%BA-%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B8%D1%82%D1%8C-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5-%D0%B8%D0%B7-json-%D0%B2-%D0%BA%D0%BE%D0%BD%D1%82%D1%80%D0%BE%D0%BB%D0%BB%D0%B5%D1%80%D0%B5-spring-mvc

    @PostMapping(value = "/ajaxprocessform", produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String processAjaxPage(Model model, @ModelAttribute("searchString") String searchString) {


        List<Teacher> slistItems = teacherService.findByParam(searchString);

        //обнуляем списки, чтобы вывести
        slistItems.forEach(t -> t.setGroups(new ArrayList<>()));//new ArrayList<>(Collections.emptyList())

      /*  String jsonsStr = new Gson().toJson(slistItems);

        System.out.println(jsonsStr);*/

        return new Gson().toJson(slistItems);

      //  return "test/ajax";





    }


/*
    //Передаем список групп напрямую, без продюсеса
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public @ResponseBody List<Teacher> getTeachers() {
        List<Teacher> slistItems = teacherService.getTeachersList();

        //обнуляем списки, чтобы вывести
        slistItems.forEach(t -> t.setGroups(new ArrayList<>(Collections.emptyList())));

        return slistItems;
    }
*/


    @RequestMapping(value="/users", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public @ResponseBody String getTeachers() {

        List<Teacher> slistItems = teacherService.getTeachersList();

        //обнуляем списки, чтобы вывести
        slistItems.forEach(t -> t.setGroups(new ArrayList<>()));//new ArrayList<>(Collections.emptyList())

      /*  String jsonsStr = new Gson().toJson(slistItems);

        System.out.println(jsonsStr);*/

        return new Gson().toJson(slistItems);
    }



}
