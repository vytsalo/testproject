package dao;

import entities.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    EntityManager em;

    
    @Transactional//!!!
    @Override
    public void add(Student student) {
        em.persist(student);
    }


    @Override
    @Transactional
    public List<Student> getStudentsList(){
        CriteriaQuery<Student> criteriaQuery = em.getCriteriaBuilder().createQuery(Student.class);
        //@SuppressWarnings("unused")
        Root<Student> root = criteriaQuery.from(Student.class);
        return em.createQuery(criteriaQuery).getResultList();
    }



}