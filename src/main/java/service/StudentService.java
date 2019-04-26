package service;

import entities.Student;
import java.util.List;

public interface StudentService {
    void add(Student student);
    List<Student> getStudentsList();
    void update(Student student);
    Student findById(Long studentId);
    void delete(Long studentId);
}
