package service;

import entities.Student;

import java.util.List;

public interface StudentService {
    void add(Student person);
    List<Student> getStudentslist();
}
