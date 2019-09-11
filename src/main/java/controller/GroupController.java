package controller;

import entities.Group;
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
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    //Нарушает правила ограничаения внешнего ключа

    @GetMapping("/")//"","/"
    public String listGroups(Model model){
        model.addAttribute("groups",groupService.getGroupsList());
        return "groups/list-groups";
    }

    @GetMapping("/add")
    public String addGroup(Model model){
        //добавить список групп
        //Просто создаем пустой экземпляр, а потом пост его обрабатывает
        model.addAttribute("group", new Group());


        model.addAttribute("students", Collections.EMPTY_LIST);


        return "groups/show-group-form";
    }


    @PostMapping("/processform")//valid
    //- request param
    // @RequestParam("students") List<Student> listStudents,
    public String processGroupForm(Model model, @ModelAttribute("group") Group newGroup, BindingResult result){

        if (result.hasErrors()){

            System.out.println(result.getAllErrors());

            model.addAttribute("group", newGroup);
            return "groups/show-group-form";

            //по ID или по группе
            //
        } else {

            if (newGroup.getId() == null){

                groupService.add(newGroup);
            }
            else {


                /*
                  List<User> users = userList.getUsers();
    for(User user : users) {
        System.out.println("First Name- " + user.getFirstName());
    }                */

                groupService.update(newGroup);

                /*
                * Как быть с нулами? Как обрабатывать?
                * Брать студента по айди
                * контроллер, который удаляет студента из группы?
                * */


            }


            //добавили группу в студента и студента в группу







            //группа стала нуллом

        /*    List<Student> listStudent = newGroup.getStudents();




            model.addAttribute("groups", groupService.getGroupsList());
*/



            return "redirect:/groups/";//Редирект чтобы не открывался сам процессформ
        }
    }


    @GetMapping("/update/{Id}")
    public String updateGroup(Model model,@PathVariable Long Id){

        Group group = groupService.findById(Id);

        model.addAttribute("group", group);

        //Избавиться и в jsp юзать
        model.addAttribute("update", true);

        //добавляем группы
        //
        model.addAttribute("groups", groupService.getGroupsList());

        //Добавляем студентов

     //   List<Student> studentList = group.getStudents();

     //   model.addAttribute("students", studentList);


        return "groups/show-group-form";

    }

    @GetMapping("/delete/{Id}")
    public String deleteGroup(Model model,@PathVariable Long Id) {
        //удаляем группу по ID
        groupService.delete(Id);
        model.addAttribute("groups",groupService.getGroupsList());
        return "redirect:/groups/";
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);//false;
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        //поле которое отвечает за группу
        dataBinder.registerCustomEditor(Group.class, new GroupEditor(groupService));


    }

}