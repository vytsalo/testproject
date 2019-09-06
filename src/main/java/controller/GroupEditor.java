package controller;

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

        //нул поинтер убрать, чтобы можно было группу добавлять
        if (StringUtils.isEmpty(text)) {

            setValue(null);

        }   else {

            //Создаем группу, и находим по тому айди который есть на форме
            //Group newGroup = groupService.findById(Long.parseLong(text));

            //добавляем студента в группу
            //groupService.findById(Long.parseLong(text)).setStudents(newStudent);

            setValue(groupService.findById(Long.parseLong(text)));

        }
    }


    public GroupEditor(GroupService groupService) {
        this.groupService = groupService;
    }

}
