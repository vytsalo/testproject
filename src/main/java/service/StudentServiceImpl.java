package service;

import dao.EntitiesDao;
import dao.StudentDao;
import entities.Student;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class StudentServiceImpl implements StudentService {

    //TODO сделать одинаковыми стили
    @Autowired
    private EntitiesDao<Student> st_dao;

    @Transactional
    @Override
    public void add(Student student){
        st_dao.add(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getStudentsList(){
        return st_dao.getList();
    }

    @Transactional
    @Override
    public void update(Student student){
        st_dao.update(student);
    }

    @Transactional
    @Override
    public Student findById(Long studentId){
        return (Student) st_dao.findById(studentId);
    }

    @Transactional
    @Override
    public void delete(Long teacherId){
        st_dao.delete(teacherId);
    }

}