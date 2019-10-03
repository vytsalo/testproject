package controller;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.GroupService;
import service.StudentService;
import service.TeacherService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


//http://localhost:8082/
//todo примеры удаленного:валидации и т.д. сделать отдельно
@Controller
public class MainController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String listGroups(Model model){

        return "main";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView authTestPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("testsecurity/auth");

        return model;

    }

}