package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.hibernate.annotations.Formula;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;


//сделать экстенд одних и тех же сущностей?
@Repository
public class TeacherDaoImlp implements EntitiesDao<Teacher> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Teacher teacher){
        em.persist(teacher);
    }

    @Override
    public List<Teacher> getList(){
        CriteriaQuery<Teacher> criteriaQuery = em.getCriteriaBuilder().createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        return  em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void update(Teacher teacher) {
   		em.merge(teacher);
    }

    @Override
    public Teacher findById(Long teacherId) {
        Teacher teacher = em.find(Teacher.class,teacherId);
        if (teacher==null)
            throw new EntityNotFoundException("Преподаватель с ID = " + teacherId + " не найден");
        return teacher;
    }

    @Override
    public void delete(Long teacherId) {
       Teacher teacher = em.find(Teacher.class, teacherId);
       if (teacher != null) em.remove(teacher);
       else throw new EntityNotFoundException("Преподаватель с ID = " + teacherId + " не найден");
    }

    //http://jquery.malsup.com/form/
//https://stackoverflow.com/questions/16611904/ignorecase-in-criteria-builder-in-jpa
    //adding where like
    //https://stackoverflow.com/questions/4635777/hibernate-jpa-criteriabuilder-ignore-case-queries
    //https://www.baeldung.com/jpa-and-or-criteria-predicates
// + SQL EXAMPLE



    //поиск по нескольким параметрам сразу, например, фамилию и имя
/*
WHERE id = 3 or id = 4
Or the equivalent in:

WHERE id in (3,4)

 */


    /**
     * как описывается документация
     * @param str = "Иванов Сергей Петрович"
     * @return
     */
    //Синтаксис PostgreSQL

    //TODO
    //закинуть в отдельный файл аджаксовые
    //перекинуть в контроллер и форму
    //сделать для студентов и групп

    @Override
    public List<Teacher> searchByString(String str) {

        //добавить разделители между словами " "
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = criteriaQuery.from(Teacher.class);

        Expression concatForOtch = criteriaBuilder.concat(criteriaBuilder.lower(teacherRoot.get("otch"))," ");

        Expression concatForName = criteriaBuilder.concat(criteriaBuilder.lower(teacherRoot.get("name"))," ");

        Expression concatForFam = criteriaBuilder.concat(criteriaBuilder.lower(teacherRoot.get("fam"))," ");

        Expression almostFinalExpression = criteriaBuilder.concat(concatForName,concatForOtch);

        Expression finalExpression = criteriaBuilder.concat(concatForFam,almostFinalExpression);

        Expression fullExpression = criteriaBuilder.concat("%", finalExpression);

        //"%fam% %name%  %otch%"
        Predicate pr = criteriaBuilder.like(fullExpression,"%" + str.toLowerCase() + "%");

        //вхере
        criteriaQuery.where(pr);
        //Сортируем по убыванию по полю ID
        criteriaQuery.orderBy(criteriaBuilder.desc(teacherRoot.get("id")));

        //убираем повторки
        criteriaQuery.distinct(true);

        return em.createQuery(criteriaQuery).getResultList();

    }


    /*
     List cats = session.createCriteria(Cat.class)
     .add( Restrictions.like("name", "Iz%") )
     .add( Restrictions.gt( "weight", new Float(minWeight) ) )
     .addOrder( Order.asc("age") )
     .list();
     */






    //и в SQL сделать
      /*  return em.createQuery(
                "SELECT c FROM Teacher c WHERE to_char(c." + "dateOfBirth, 'yyyy-mm-dd'" + ") LIKE ?1 or " + "lower(c." + "fam" + ") LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();*/




}
