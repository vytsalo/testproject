package dao;

import entities.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
//Из дао транзакции убрать

@Repository
@SuppressWarnings("unused")
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    EntityManager em;

    
    @Transactional
    @Override
    public void add(Student student) {
        em.persist(student);
    }


    @Override
    @Transactional
    public List<Student> getStudentsList(){
        CriteriaQuery<Student> criteriaQuery = em.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        return em.createQuery(criteriaQuery).getResultList();
    }


    @Override
    @Transactional
    public void update(Student student){
        em.merge(student);
    }

    @Transactional
    @Override
    public Student findById(Long studentId){
        Student student=em.find(Student.class, studentId);
        if (student==null)
            throw new EntityNotFoundException("Студент с Id=" + studentId + " не найден");
        return student;
    }

    @Transactional
    @Override
    public void delete(Long studentId){
        Student student = em.find(Student.class, studentId);
        if (student != null) em.remove(student);
        else throw new EntityNotFoundException("Студент с Id=" + studentId + " не найден");
    }

}