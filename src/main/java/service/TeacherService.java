package service;

import entities.Teacher;
import java.util.List;

@SuppressWarnings("unused")
public interface TeacherService {
    void add(Teacher teacher);
    List<Teacher> getTeachersList();
    void update(Long teacherId);
    Teacher findById(Long teacherId);
    void delete(Long teacherId);
}
