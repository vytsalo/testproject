package dao;
import entities.Student;
import java.util.List;

public interface StudentDao {

    //список всех студентов, имеющихся в бд
    List<Student> getStudentsList();
}
