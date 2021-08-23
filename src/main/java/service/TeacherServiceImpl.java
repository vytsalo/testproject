package service;

import dao.EntitiesDao;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements EntitiesService<Teacher> {

    @Autowired
    //Экземпляр интерфейса
    private EntitiesDao<Teacher> teacherDao;

    @Transactional
    @Override
    public void add(Teacher teacher){
        teacherDao.add(teacher);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getList(){
        return teacherDao.getList();
    }

    @Override
    @Transactional
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
    @Transactional
    public Teacher findById(Long teacherId) {
        return teacherDao.findById(teacherId);
    }

    @Override
    @Transactional
    public void delete(Long teacherId) {
        teacherDao.delete(teacherId);
    }

    @Override
    @Transactional
    public List<Teacher> findByParam(String str) {
        return teacherDao.searchByTitle(str);
    }


}