package dao;

import entities.Teacher;
import java.util.List;

//CRUD
public interface TeacherDao {

    //добавление
    void add(Teacher teacher);

    //список всех тичеров, имеющихся в бд
    List<Teacher> getTeachersList();

    //обновление
    void update(Teacher teacher);

    //поиск объекта по айди
    Teacher findById(Long teacherId);

    //удаление по объекту
    void delete(Long teacherId);

}
