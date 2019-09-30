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

        if (StringUtils.isEmpty(text)) {
            //пустая строка - нулл - объект
            setValue(null);

        }   else {

            //устанавливаем группу с определенным ID
            setValue(groupService.findById(Long.parseLong(text)));

        }
    }


    public GroupEditor(GroupService groupService) {
        this.groupService = groupService;
    }

}
