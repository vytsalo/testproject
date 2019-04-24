package dao;
import entities.Student;
import java.util.List;

public interface StudentDao {

    void add(Student student);
    //список всех студентов, имеющихся в бд
    List<Student> getStudentsList();
}
