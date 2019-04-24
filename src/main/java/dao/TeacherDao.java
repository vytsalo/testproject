package dao;

import entities.Teacher;
import java.util.List;

//CRUD
public interface TeacherDao {

    //добавление
    void add(Teacher teacher);

    //список всех тичеров, имеющихся в бд
    List<Teacher> getTeachersList();

    //ретурнить список с удаленным?
}
