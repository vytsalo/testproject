package controller;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/list")
    public String listgroup(Model model){
        model.addAttribute("groups", groupService.getGroupsList());

        return "list-groups";
    }

    @GetMapping("/add")
    public String addGroup(Model model) {
        System.out.println("in addGroup ");
        Group group=new Group();
        model.addAttribute(group);
        return "show-group-form";
    }

    @PostMapping("/processform")
    public String processForm(Model model,@ModelAttribute("Group") Group newGroup ) {

        groupService.add(newGroup);

        //display
        model.addAttribute("groups",groupService.getGroupsList());

        System.out.println("in process form");

        return "list-groups";
    }


    @GetMapping("/update")
    public String updateGroup(Model model,@RequestParam("GroupId") Long Id ) {

        model.addAttribute("group",groupService.findById(Id));

        return "show-group-form";

    }

    @GetMapping("/delete")
    public String deleteGroup(Model model,@RequestParam("GroupId") Long Id) {

        //adding logic here
        groupService.delete(Id);

        //display
        model.addAttribute("groups",groupService.getGroupsList());

        System.out.println("in process form");
        return "list-groups";

    }
    
    


}
