package controller;

import entities.Group;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.GroupService;
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


//todo add custom security login page
//todo сортировка таблицы по id при загрузке страницы
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

    @Autowired
    private GroupService groupService;

    @GetMapping("/")//"","/"
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";//вьюшка students.jsp
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        //добавить список групп
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("student", new Student());

        //добавляем группы
        model.addAttribute("groups", groupService.getGroupsList());

        return "students/show-student-form";

    }

    /*

    форматирование даты. при выводе всей инфы - переводится в нормальный формат,
    а как сделать в сеттерах - геттерах
    разница между ModelAndView и String - контроллером
    в каком формате данные передаются
    */



    //todo phone input text to tel to get ride of js masks
    //todo spring security database user accounts

    /**
     *
     * @param model
     * @param newStudent
     * @param result
     * @return
     */
    @PostMapping("/processform")//valid
    public String processStudentForm(Model model, @Valid @ModelAttribute("student") Student newStudent, BindingResult result){

        //https://developer.mozilla.org/ru/docs/Web/HTML/Element/Input/tel
        //http://programmerbook.ru/html/input/type/tel/
        //todo use or not html5 features like tel field

        if (result.hasErrors()){

            //в один метод упрятать
                    System.out.println(result.getAllErrors());

                    //Если есть ошибки - ничего не делать
                        //Если есть ошибки - добавляем то, что есть
                        //и перезагружаем форму

                        model.addAttribute("student", newStudent);
                        return "students/show-student-form";

                } else {

                        if (newStudent.getId() == null){
                            //добавляем нового студента
                            studentService.add(newStudent);
                        }
                        else
                            studentService.update(newStudent);

                        //todo white errors loading while loading logs


            //todo баг при редактировании не отображается кнопка удалить, если есть группа
            //todo как сделать несколько записей?
            /*
             * какие компоненты использовать в интерфейса
             * закрытие джейкуерри модал
             * валидация группы нужна ли?
             *
             * */



            /* STUDENT TO GROUP*/


            Group currentGroup = newStudent.getGruppa();

            //если у студента есть группа
            if (currentGroup!=null) {
                //добавили студента в группу
                currentGroup.addStudent(newStudent);
                //обновили группу
                groupService.update(currentGroup);

            }
            /* /STUDENT TO GROUP*/

            model.addAttribute("students", studentService.getStudentsList());

                    return "redirect:/students/";//Редирект чтобы не открывался сам процессформ
                }
    }


    //Обработка исключений не найдена страница
    @GetMapping("/update/{Id}")
    public String updateStudent(Model model,@PathVariable Long Id){

        Student student = studentService.findById(Id);

        model.addAttribute("student", student);

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //добавляем группы
        model.addAttribute("groups", groupService.getGroupsList());

        //        return "redirect:/viewemp";//will redirect to viewemp request mapping
        //        return "forward:/" - проброс
        return "students/show-student-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        studentService.delete(Id);
        model.addAttribute("students",studentService.getStudentsList());
        return "redirect:/students/";
    }

    //todo make a ModelandView name (difference between)
    @InitBinder
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

        dataBinder.registerCustomEditor(Group.class, "gruppa", new GroupEditor(groupService));

    }

    //todo remove useless commentaries
}