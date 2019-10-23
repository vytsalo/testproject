package dao;

import entities.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudentDaoImpl implements EntitiesDao<Student>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Student student) {
        em.persist(student);
    }

    @Override
    public List<Student> getList(){
        CriteriaQuery<Student> criteriaQuery = em.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void update(Student student){
        em.merge(student);
    }

    @Override
    public Student findById(Long studentId){
        Student student=em.find(Student.class, studentId);
        if (student==null)
            throw new EntityNotFoundException("Студент с ID = " + studentId + " не найден");
        return student;
    }

    @Override
    public void delete(Long studentId){
        Student student = em.find(Student.class, studentId);
        if (student != null) em.remove(student);
        else throw new EntityNotFoundException("Студент с ID = " + studentId + " не найден");
    }

}