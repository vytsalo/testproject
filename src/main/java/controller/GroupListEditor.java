package controller;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import service.GroupService;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Collections;

//надо?
@Component
public class GroupListEditor extends PropertyEditorSupport {
    @Autowired
    GroupService groupService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        //нул поинтер убрать, чтобы можно было группу добавлять
        if (StringUtils.isEmpty(text)) {

            setValue(Collections.EMPTY_LIST);

        }   else {

            //Создаем группу, и находим по тому айди который есть на форме
            //Group newGroup = groupService.findById(Long.parseLong(text));

            //добавляем студента в группу
            //groupService.findById(Long.parseLong(text)).setStudents(newStudent);

            ArrayList<Group> groups = new ArrayList<>();

            //список айдишников передаем
            /*
            for (int i = 0; i < ; i++) {
                groups.add()
            }
            */

            //groupService.findById(Long.parseLong(text))

            /*
            * таблицей отображение, в поле хайден список айди через запятую
            * при удалении из таблицы удалить и из эдита
            *
            *
            * */

            setValue(groups);

        }
    }


    public GroupListEditor(GroupService groupService) {
        this.groupService = groupService;
    }


}
