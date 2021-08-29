package service;

import dao.EntitiesDao;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/*
@Inject is a standard annotation for dependency injection and @Autowired is spring specific.
* */
//параметр - имя бина, необходимо для того, чтобы распознать к какой сущности применяется интерфейс
@Service("studentService")
public class StudentServiceImpl implements EntitiesService<Student> {

    //TODO сделать одинаковыми стили
    @Autowired
    //почему нельзя сделать класс StudentDao
    //почему делаем реализацию через интерфейс и поиск соответствующего класса реализации
    //пишем интерфейс и класс <Student>, для которого ищем реализацию
    private EntitiesDao<Student> studentDao;

    @Transactional
    @Override
    public void add(Student student){
        studentDao.add(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getList(){
        return studentDao.getList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Student> getListWithPagination(int page, int size){
        return studentDao.getListWithPagination(page,size);
    }


    @Transactional
    @Override
    public void update(Student student){
        studentDao.update(student);
    }

    @Transactional
    @Override
    public Student findById(Long studentId){
        return studentDao.findById(studentId);
    }

    @Transactional
    @Override
    public void delete(Long teacherId){
        studentDao.delete(teacherId);
    }

    @Override
    @Transactional
    public List<Student> findByParam(String str) {
        return studentDao.searchByTitle(str);
    }


}