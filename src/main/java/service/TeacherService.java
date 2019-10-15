package service;

import entities.Teacher;
import java.util.List;

public interface TeacherService {
    void add(Teacher teacher);
    List<Teacher> getTeachersList();
    void update(Teacher teacher);
    Teacher findById(Long teacherId);
    void delete(Long teacherId);

    List<Teacher> getTeachersByParam(String str);


}
