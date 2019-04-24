package service;

import dao.StudentDao;
import dao.StudentDaoImpl;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    //екземпляр интерфейса
    private StudentDao st_dao;//studentdaoimpl

    @Transactional
    @Override
    public void add(Student student){
       // if (!student.equals(null))
        st_dao.add(student);
    }

    //зачем дополнительно прописывать ридонли, если оно и так ридонли?
    @Transactional(readOnly = true)
    @Override
    public List<Student> getStudentsList(){
        return st_dao.getStudentsList();
    }

}