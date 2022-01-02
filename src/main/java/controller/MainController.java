package controller;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.EntitiesService;
import service.HumanService;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class MainController {

    //todo auth controller

    @Autowired
    HumanService humanService;

    @Autowired
    private EntitiesService<Group> groupService;

    @Autowired
    private EntitiesService<Student> studentService;

    @Autowired
    private EntitiesService<Teacher> teacherService;

    @GetMapping("/")
    public String mainPage(){
    return "main";
    }

    @GetMapping("/init")
    public String init() throws ParseException, IOException {
       humanService.initDB();
       return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if ("".equals(error)) {//Objects.equals(
            model.addAttribute("errorMessage", "Неверный логин или пароль");
        }
        return "security/login";
    }

    @GetMapping("/auth")
    public String authPage() {
        return "security/auth";
    }




    @GetMapping("/export")
    public HttpStatus export() {

        try {

            humanService.exportToExcel();

            return HttpStatus.OK;

        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.UNPROCESSABLE_ENTITY;

        }
    }

}