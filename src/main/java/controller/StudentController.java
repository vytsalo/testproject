package controller;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.StudentService;
import valid.StudentValidator;
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

    //todo phone input text to tel
    //todo date format validation client
    @PostMapping("/processform")//valid
    public String processStudentForm(Model model, @Valid @ModelAttribute("student") Student newStudent, BindingResult result){

        //возврат страницы с данными+
        //валидация с префиксом форм?
        //Ошибки связанные с ID(обработка исключений)
        //Куда мелкие баги записывать
        //todo datefix
        //todo studentErrorHandler
        //todo 500 error handler (getErrorMessage)
        //todo css class errors
        //todo replace post url to post java method
        //todo class controller requestmapping
        //todo mainclass controller override method
        //при срабатывании исключения в контроллере ретурнить страницу с тем, что студент не найден

        if (result.hasErrors()){

                    System.out.println(result.getAllErrors());

                    if (newStudent.getId() == null) {

                        model.addAttribute("student", newStudent);

                        return "students/show-student-form";//"redirect:/students/add";//return

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

                    return "students/list-students";
                }
    }

    //Обработка исключений не найдена страница
    @GetMapping("/update/{Id}")
    public String updateStudent(Model model,@PathVariable Long Id){

        Student student = studentService.findById(Id);

        model.addAttribute("student", student);
        //лучше так
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

    //валидатор класса
    @Autowired
    private StudentValidator studentValidator;

    /*
    @Autowired
    private TeacherValidator studentValidator;
    */

    //todo fix css bug with error
    //todo -name attribute in jsp file
    //todo form - hidden
    //todo -Student validator class
    //todo - validation package

  /*  @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(studentValidator);//нескольок валидаторов добавлять моджно
        //в каком формате будет дата
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //строгий формат - false, нестрогий, который будет разбирать - true
        dateFormat.setLenient(true);
        //dateFormat.setLenient(false);

        //создаем поле в сущности
        //с определенным классом - тип поля
        //как называется поле
        //формат
        //из примера взять
        binder.registerCustomEditor(Date.class, "date_of_birth", new CustomDateEditor(dateFormat, true));
    }*/

  //todo try value = date_of_birth
    //todo date text to date field
    @InitBinder//(value="date_of_birth")
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //строгий формат - false, нестрогий, который будет разбирать - true
        dateFormat.setLenient(true);
        //dateFormat.setLenient(false);
        //создаем поле в сущности
        //с определенным классом - тип поля
        //как называется поле
        //формат
        //из примера взять
        dataBinder.registerCustomEditor(Date.class, "date_of_birth", new CustomDateEditor(dateFormat, true));
        //todo edit css class for input type date
    }

    //todo try this initbinder
    //source: https://stackoverflow.com/questions/11324622/how-to-handle-different-date-formats-in-spring-mvc-controller
//    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", this.getLocale(context));

    /*
    @InitBinder
protected void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
            dateFormat, false));
}*/

    //https://stackoverflow.com/questions/34858989/wrong-date-format-when-submit-spring-form
/*@InitBinder
private void dateBinder(WebDataBinder binder) {
    //The date format to parse or output your dates
    SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat());
    //Create a new CustomDateEditor
    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
    //Register it as custom editor for the Date type
    binder.registerCustomEditor(Date.class, editor);
}*/



}