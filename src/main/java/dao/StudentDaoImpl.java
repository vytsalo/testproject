package dao;

import entities.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext//(type=PersistenceContextType.TRANSACTION, unitName = "src.main.entities.Student")
    EntityManager em;//=javax.persistence.EntityManager.class.newInstance().getEntityManagerFactory();

    /*@PersistenceContext
    EntityManager em= getEntityManagerFactory().createEntityManager();

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    */

    @Override
    public List<Student> getStudentsList(){
        CriteriaQuery<Student> criteriaQuery = em.getCriteriaBuilder().createQuery(Student.class);
        //@SuppressWarnings("unused")
        Root<Student> root = criteriaQuery.from(Student.class);
        return em.createQuery(criteriaQuery).getResultList();
    }


    //из-за id
    @Override
    public void add(Student student) {
        //персисг генерирует id а мердж надо добавлять с генератором
       // if (!student.equals(null))
        em.persist(student);
    }

}