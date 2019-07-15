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

    @Autowired
    private StudentService studentService;//private

    //Вывод списка студентов
    //без ретурна?
    @GetMapping("/list")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getStudentsList());
        //test
        model.addAttribute("g",10);
        return "list-students";//вьюшка students.jsp
    }

    //POST? откуда данные будут браться?
    //Могут ли быть воид?
    //добавление
    @GetMapping("/add")
    //String
    public String addStudent(Model model){
        System.out.println("in addStudent");
        //или он добавляет пустого а потом редактировать надо?
        Student student = new Student();
        model.addAttribute(student);//без имени аттрибута?
        //без ретурна?
        return "show-student-form";
    }

    //ШТО?
    @PostMapping("/processform")
    public String processStudentForm(Model model, @ModelAttribute("student") Student newStudent){

        studentService.add(newStudent);

        //обновление
        //model.addAttribute("students",studentService.get);

        System.out.println("in process form");

        return "list-student";//вью

    }


    //обновление
    @GetMapping("/update")
    //studentId - или реальный ID
    public String updateStudent(Model model, @RequestParam("studentId") Long id ){

       /* Student student = studentService.findById(id);
          model.addAttribute("student", studentService.update(student));
        */


       //getStudentsList?
        model.addAttribute("student", studentService.findById(id));

        return "show-student-form";

    }

    //удаление
    @GetMapping("/delete")
    public String deleteStudent(Model model, @RequestParam("studentsId") Long id){
        studentService.delete(id);

        model.addAttribute("students", studentService.getStudentsList());

        System.out.println("in process form");

        return "list-students";


    }


}
