package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import service.StudentService;
import java.beans.PropertyEditorSupport;

@Component
@Deprecated
public class StudentEditor extends PropertyEditorSupport {


    @Autowired
    StudentService studentService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (StringUtils.isEmpty(text))
            setValue(null);
        else
            setValue(studentService.findById(Long.parseLong(text)));

    }


}
