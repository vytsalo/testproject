package dao;

import entities.Teacher;

import java.util.List;

//CRUD
public interface TeacherDao {

    //CREATE READ UPDATE DELETE

    //boolean void

    //добавление
    void add(Teacher teacher);

    //удаление
    void deleteById(int id);

    //String Query
    void updateById(int id);

    //поиск
    Teacher findById(int id);

    //список всех тичеров, имеющихся в бд
    List<Teacher> getTeachersList();

}
