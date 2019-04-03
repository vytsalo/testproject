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
    void delete(String krit);

    //
    void update(List<Teacher> teachers);

    //поиск
    Teacher findById(String krit);

    //список всех тичеров, имеющихся в бд
    List<Teacher> getTeachersList();

}
