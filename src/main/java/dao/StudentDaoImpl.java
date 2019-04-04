package dao;

import entities.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Student> getStudentsList(){
        return new ArrayList<Student>();
    }

    @Override
    public void add(Student student) {
        em.persist(student);
    }
}
