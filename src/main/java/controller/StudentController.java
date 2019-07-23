package controller;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.StudentService;

/*
Вьюхи:
    Главная: список всех групп
    Группы:
        Группа 1 - список преподавателей и студентов
        Группа 2 - список преподавателей и студентов
        Группа 3 - список преподавателей и студентов
*/



/*

@RequestMapping(value = "/fetch")
public ModelAndView listEmployee(ModelAndView model) throws IOException {

        List<Employee> listEmp = empDao.empList();
        model.addObject("listEmp", listEmp);
        model.setViewName("index");

        return model;
        }*/


@Controller
@RequestMapping("/students")
public class StudentController {

    //форма для добавления сначала
    //потом
    //начать с группы
    //кратко инициалы сделать - сборка из фамилии имени и отчества
    // Иванов И.И.



    @Autowired
    private StudentService studentService;//private

    //Вывод списка студентов
    //без ретурна?
    @GetMapping("/list")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";//вьюшка students.jsp
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        model.addAttribute(new Student());//без имени аттрибута?
        return "students/show-student-form";
    }

    @PostMapping("/processform")
    public String processStudentForm(Model model, @ModelAttribute("student") Student newStudent){

        if (newStudent.getId()==null)
            studentService.add(newStudent);
        else
            studentService.update(newStudent);

        model.addAttribute("students",studentService.getStudentsList());

        return "students/list-student";//вью

    }





    @GetMapping("/update/{Id}")
    public String updateStudent(Model model,@PathVariable Long Id) {

        //сделать короче
        Student student = studentService.findById(Id);

        //избавиться
        model.addAttribute("student", student);

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //        return "redirect:/viewemp";//will redirect to viewemp request mapping
        //        return "forward:/" - проброс
        return "students/show-student-form";

        //какая разница между
        //"redirect:" + "/list"
        //и вызовом метода, если убрать считывание группы

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        studentService.delete(Id);
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";

    }


}
