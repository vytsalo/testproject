package service;

import dao.TeacherDao;
import entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    //Экземпляр интерфейса
    private TeacherDao teach_dao;

    @Transactional
    @Override
    public void add(Teacher teacher){
        teach_dao.add(teacher);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getTeachersList(){
        return teach_dao.getTeachersList();
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
    public List<Teacher> getTeachersByParamList(String query) {
        return teach_dao.searchByQuery(query);
    }

    @Override
    @Transactional
    public List<Teacher> getTeachersBySome(String str) {
        return teach_dao.searchByString(str);
    }


}