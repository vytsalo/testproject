package service;

import dao.EntitiesDao;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("studentService")
@SuppressWarnings("unused")
public class StudentServiceImpl implements EntitiesService<Student> {

    //TODO сделать одинаковыми стили
    @Autowired
    //@Qualifier("teachersDao")
    private EntitiesDao<Student> st_dao;

    @Transactional
    @Override
    public void add(Student student){
        st_dao.add(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getList(){
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

    @Override
    @Transactional
    public List<Student> findByParam(String str) {
        return st_dao.searchByString(str);
    }


}