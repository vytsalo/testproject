package controller;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import service.EntitiesService;

import java.beans.PropertyEditorSupport;

//Проперти эдитор для определенного поля
@Component
public class GroupEditor extends PropertyEditorSupport {

    @Autowired
    EntitiesService<Group> groupService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (StringUtils.isEmpty(text)) {

            setValue(null);

        }   else {

            //устанавливаем группу с определенным ID
            setValue(groupService.findById(Long.parseLong(text)));
        }
    }

    public GroupEditor(EntitiesService<Group> groupService) {
        this.groupService = groupService;
    }

}
