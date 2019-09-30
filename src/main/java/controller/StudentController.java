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

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/")//"","/"
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getStudentsList());
        return "students/list-students";//вьюшка list-students.jsp
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("student", new Student());
        //добавляем группы
        model.addAttribute("groups", groupService.getGroupsList());
        return "students/show-student-form";
    }

    @PostMapping("/processform")
    public String processStudentForm(Model model, @Valid @ModelAttribute("student") Student newStudent, BindingResult result){

        //Если есть ошибки при валидации
        if (result.hasErrors()){

                    //выводим все ошибки
                    System.out.println(result.getAllErrors());

                        //добавляем то, что есть, и перезагружаем форму

                        model.addAttribute("student", newStudent);
                        return "students/show-student-form";

                } else {

                        if (newStudent.getId() == null){
                            //добавляем нового студента

                            Group tempGroup = groupService.findById(newStudent.getGruppa().getId());

                            newStudent.setGruppa(null);

                            studentService.add(newStudent);


                            tempGroup.addStudent(newStudent);

                            newStudent.setGruppa(tempGroup);

                            studentService.update(newStudent);

                        }
                        else
                            studentService.update(newStudent);

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

        //находим студента по ID
        Student student = studentService.findById(Id);

        model.addAttribute("student", student);

        model.addAttribute("update", true);

        //добавляем группы
        model.addAttribute("groups", groupService.getGroupsList());

        return "students/show-student-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        studentService.delete(Id);
        model.addAttribute("students",studentService.getStudentsList());
        return "redirect:/students/";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //строгий формат - false, нестрогий, который будет разбирать - true
        dateFormat.setLenient(true);
        /*
        1) создаем поле в сущности с определенным классом - тип поля
        2) как называется поле
        3) формат-даты
        */
        //, "date_of_birth", - field
        //без указания поля будет обрабатывать все поля типа Date
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));
    }

}