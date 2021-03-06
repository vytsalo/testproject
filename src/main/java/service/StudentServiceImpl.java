package service;

import dao.EntitiesDao;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//TODO differences between @Autowired and @Inject




/*
@Inject is a standard annotation for dependency injection and @Autowired is spring specific.
* */
//параметр - имя бина, необходимо для того, чтобы распознать к какой сущности применяется интерфейс
@Service("studentService")
@SuppressWarnings("unused")
public class StudentServiceImpl implements EntitiesService<Student> {

    //TODO сделать одинаковыми стили
    @Autowired
    //почему нельзя сделать класс StudentDao
    //почему делаем реализацию через интерфейс и поиск соответствующего класса реализации
    //пишем интерфейс и класс <Student>, для которого ищем реализацию
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