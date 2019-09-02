package controller;

import entities.Group;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import service.GroupService;
import service.StudentService;
import java.beans.PropertyEditorSupport;

public class StudentEditor extends PropertyEditorSupport {

    @Autowired
    StudentService studentService;

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
            Group newGroup = groupService.findById(Long.parseLong(text));

            newStudent.setGruppa(newGroup);

            setValue(newStudent);

        }
    }
}
