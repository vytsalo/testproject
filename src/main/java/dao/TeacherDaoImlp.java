package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

//CREATE READ UPDATE DELETE
@Repository
public class TeacherDaoImlp implements TeacherDao {

    @PersistenceContext
    EntityManager em;

    //добавление записи в конец бд
    @Transactional
    @Override
    public void add(Teacher teacher){
        em.persist(teacher);
    }

    @Override
    @Transactional
    public List<Teacher> getTeachersList(){
        CriteriaQuery<Teacher> criteriaQuery = em.getCriteriaBuilder().createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        return  em.createQuery(criteriaQuery).getResultList();
    }

}
