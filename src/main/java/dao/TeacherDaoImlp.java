package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

//CREATE READ UPDATE DELETE
@Repository
@SuppressWarnings("unused")
public class TeacherDaoImlp implements TeacherDao {

    //почему в примере дао без транзакшоналов, а тут не работает
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

    @Override
    @Transactional
    public void update(Teacher teacher) {
   		em.merge(teacher);
    }


    //не обязательно
    //ретурнит сущность по id
    @Override
    @Transactional
    public Teacher findById(Long teacherId) {
        Teacher teacher = em.find(Teacher.class,teacherId);
        if (teacher==null)
            throw new EntityNotFoundException("Преподаватель с ID =" + teacherId + "не найден");
        return teacher;
    }

    //удаляет сущность по id
    @Override
    @Transactional
    public void delete(Long teacherId) {
       Teacher teacher = em.find(Teacher.class, teacherId);
       if (teacher != null) em.remove(teacher);
       else throw new EntityNotFoundException("Преподаватель с ID =" + teacherId + "не найден");
    }

}
