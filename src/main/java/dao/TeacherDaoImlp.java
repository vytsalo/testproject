package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

//Как отслеживать изменения в бд (тригер хук)
//обязательно все должен реализовывать
public class TeacherDaoImlp implements TeacherDao {

    @PersistenceContext
    private EntityManager em;

    //добавление записи в конец бд
   @Transactional
    @Override
    public void add(Teacher teacher){
        em.persist(teacher);
    }


    //зачем?
    @Transactional
    @Override
    public Teacher find(String krit){
        //находим
        return new Teacher();
    }

    @Transactional
    @Override
    public void delete(String krit){
        //удаляем из бд все записи по критерию
    }

    //Дроп и апдейт
    //изменение значения поля или целой записи
    public void update(List<Teacher> teachers){
        //че-то делает
    }

   // @Transactional//чтение
    @Override
    public List<Teacher> getTeachersList(){
        CriteriaQuery<Teacher> criteriaQuery = em.getCriteriaBuilder().createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        return  em.createQuery(criteriaQuery).getResultList();
    }

}
