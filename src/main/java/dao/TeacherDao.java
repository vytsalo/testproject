package dao;

import entities.Teacher;

import java.util.List;

//CRUD
public interface TeacherDao {

    //boolean void
    void add(Teacher teacher);
    void read(Teacher teacher);
    void delete(Teacher teacher);

    //список всех тичеров, имеющихся в бд
    List<Teacher> listTeachers();


}
