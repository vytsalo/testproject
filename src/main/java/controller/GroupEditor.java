package controller;

import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import service.GroupService;
import java.beans.PropertyEditorSupport;

//Проперти эдитор для определенного поля
@Component
public class GroupEditor extends PropertyEditorSupport {

    @Autowired
    GroupService groupService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (StringUtils.isEmpty(text)) {
            setValue(null);

        }   else {

            //создаем студента
            Student newStudent = new Student();

            //Создаем группу, и находим по тому айди который есть на форме
            //Group newGroup = groupService.findById(Long.parseLong(text));

            newStudent.setGruppa(groupService.findById(Long.parseLong(text)));

            setValue(newStudent);

        }
    }

    public GroupEditor(GroupService groupService) {
        this.groupService = groupService;
    }

}
