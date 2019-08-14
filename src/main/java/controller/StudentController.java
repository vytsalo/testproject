package controller;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.StudentService;

import javax.validation.Valid;

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
    @GetMapping("/")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";//вьюшка students.jsp
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        model.addAttribute("student", new Student());//без имени аттрибута?
        return "students/show-student-form";
    }

    @PostMapping("/processform")
    public String processStudentForm(Model model, @Valid @ModelAttribute("student") Student newStudent, BindingResult result){

        //выводить как ошибки?






        if (result.hasErrors()){


                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(result.getAllErrors());
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    if (newStudent.getId() == null)
                        return "redirect:/students/add";
                    else {
                        model.addAttribute("student", newStudent);
                        return "redirect:/students/update/" + newStudent.getId();
                    }
                    //дата - инпут тайп дейта и минут регулярка

                } else {

                        if (newStudent.getId() == null)
                            studentService.add(newStudent);
                        else
                            studentService.update(newStudent);

                    model.addAttribute("students", studentService.getStudentsList());

                    return "students/list-students";//вью
                }
    }




    //Обработка исключений не найдена страница
    @GetMapping("/update/{Id}")
    public String updateStudent(Model model,@PathVariable Long Id){


        //if (Id == null) throw new StudentNotFoundException();
        //if (Id == null) throw new StudentNotFoundException();

        Student student = studentService.findById(Id);

        model.addAttribute("student", student);
        //лучше так
        //if (studentService.findById(Id) == nul
        // l) throw new StudentNotFoundException();

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
