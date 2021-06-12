package service;

import dao.EntitiesDao;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements EntitiesService<Teacher> {

    @Autowired
    //Экземпляр интерфейса
    private EntitiesDao<Teacher> teach_dao;

    @Transactional
    @Override
    public void add(Teacher teacher){
        teach_dao.add(teacher);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getList(){
        return teach_dao.getList();
    }

    @Override
    @Transactional
    public void update(Teacher teacher) {
        teach_dao.update(teacher);
    }

    @Override
    @Transactional
    public Teacher findById(Long teacherId) {
        return teach_dao.findById(teacherId);
    }

    @Override
    @Transactional
    public void delete(Long teacherId) {
        teach_dao.delete(teacherId);
    }

    @Override
    @Transactional
    public List<Teacher> findByParam(String str) {
        return teach_dao.searchByString(str);
    }


}