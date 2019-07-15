package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

//CREATE READ UPDATE DELETE
@Repository
@SuppressWarnings("unused")
public class TeacherDaoImlp implements TeacherDao {

    @PersistenceContext
    private EntityManager em;

    //добавление записи в конец бд
    @Override
    public void add(Teacher teacher){
        em.persist(teacher);
    }

    @Override
    public List<Teacher> getTeachersList(){
        CriteriaQuery<Teacher> criteriaQuery = em.getCriteriaBuilder().createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        return  em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void update(Teacher teacher) {
   		em.merge(teacher);
    }

    //ретурнит сущность по id
    @Override
    public Teacher findById(Long teacherId) {
        Teacher teacher = em.find(Teacher.class,teacherId);
        if (teacher==null)
            throw new EntityNotFoundException("Преподаватель с ID = " + teacherId + " не найден");
        return teacher;
    }

    //удаляет сущность по id
    @Override
    public void delete(Long teacherId) {
       Teacher teacher = em.find(Teacher.class, teacherId);
       if (teacher != null) em.remove(teacher);
       else throw new EntityNotFoundException("Преподаватель с ID = " + teacherId + " не найден");
    }

}
