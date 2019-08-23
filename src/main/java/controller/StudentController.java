package controller;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.StudentService;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Вьюхи:
    Главная: список всех групп
    Группы:
        Группа 1 - список преподавателей и студентов
        Группа 2 - список преподавателей и студентов
        Группа 3 - список преподавателей и студентов
*/

@Controller
@RequestMapping("/students")
public class StudentController {

    //форма для добавления сначала
    //потом
    //начать с группы
    //кратко инициалы сделать - сборка из фамилии имени и отчества
    // Иванов И.И.

    @Autowired
    private StudentService studentService;

    //Вывод списка студентов
    //без ретурна?
    @GetMapping("/")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";//вьюшка students.jsp
    }

    @GetMapping("/add")
    public String addStudent(Model model){

        model.addAttribute("student", new Student());

        return "students/show-student-form";
    }

    //todo phone input text to tel to get ride of js masks
    //todo javadoc maven dependency
    //todo spring security database user accounts

    @PostMapping("/processform")//valid
    public String processStudentForm(Model model, @Valid @ModelAttribute("student") Student newStudent, BindingResult result){

        //https://developer.mozilla.org/ru/docs/Web/HTML/Element/Input/tel
        //http://programmerbook.ru/html/input/type/tel/
        //todo use or not html5 features like tel field

        if (result.hasErrors()){

                    System.out.println(result.getAllErrors());

                    if (newStudent.getId() == null) {

                        model.addAttribute("student", newStudent);

                        return "students/show-student-form";

                    }
                    else {
                        model.addAttribute("student", newStudent);


                        return "students/show-student-form";

                    }

                } else {

                        if (newStudent.getId() == null)
                            studentService.add(newStudent);
                        else
                            studentService.update(newStudent);

                    model.addAttribute("students", studentService.getStudentsList());

                    return "redirect:/students/";//Редирект чтобы не открывался сам процессформ
                }
    }

    //todo try this beautiful jsp
    //https://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
    //Обработка исключений не найдена страница
    @GetMapping("/update/{Id}")
    public String updateStudent(Model model,@PathVariable Long Id){

        Student student = studentService.findById(Id);

        model.addAttribute("student", student);

        //if (studentService.findById(Id) == null) throw new StudentNotFoundException();

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //        return "redirect:/viewemp";//will redirect to viewemp request mapping
        //        return "forward:/" - проброс
        return "students/show-student-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        studentService.delete(Id);
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";

    }

    //todo make a ModelandView name (difference between)

    @InitBinder//(value="date_of_birth")
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //строгий формат - false, нестрогий, который будет разбирать - true
        dateFormat.setLenient(true);//false;
        /*
        1) создаем поле в сущности с определенным классом - тип поля
        2) как называется поле
        3) формат-даты
        */
        dataBinder.registerCustomEditor(Date.class, "date_of_birth", new CustomDateEditor(dateFormat, true));
    }

    //todo remove useless commentaries

}