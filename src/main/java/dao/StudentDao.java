package dao;
import entities.Student;
import java.util.List;
//STUDENTS CRUD
public interface StudentDao {
    void add(Student student);
    List<Student> getStudentsList();
    void update(Student student);
    Student findById(Long studentId);
    void delete(Long studentId);
}
