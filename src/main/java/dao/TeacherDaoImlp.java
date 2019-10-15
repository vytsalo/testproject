package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherDaoImlp implements TeacherDao {

    @PersistenceContext
    private EntityManager em;

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
    @Override
    public List<Teacher> searchByString(String str) {




        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> TeacherRoot = criteriaQuery.from(Teacher.class);

        Predicate predicateForName
                = criteriaBuilder.like(criteriaBuilder.lower(TeacherRoot.get("name")), "%" + str.toLowerCase() + "%");

        Predicate predicateForFam
                = criteriaBuilder.like(criteriaBuilder.lower(TeacherRoot.get("fam")), "%" + str.toLowerCase() + "%");

        Predicate predicateForOtch
                = criteriaBuilder.like(criteriaBuilder.lower(TeacherRoot.get("otch")), "%" + str.toLowerCase() + "%");

        Predicate predicateForPhone
                = criteriaBuilder.like(TeacherRoot.get("phoneNumber"), "%" + str + "%");

        Predicate predicateForDate
                = criteriaBuilder.like(TeacherRoot.get("dateOfBirth").as(String.class), "%" + str + "%");

        Predicate predicateFinal = criteriaBuilder.or(
                predicateForName,
                predicateForFam,
                predicateForOtch,
                predicateForPhone,
                predicateForDate);

        criteriaQuery.where(predicateFinal);

        return em.createQuery(criteriaQuery).getResultList();

    }





    //и в SQL сделать
      /*  return em.createQuery(
                "SELECT c FROM Teacher c WHERE to_char(c." + "dateOfBirth, 'yyyy-mm-dd'" + ") LIKE ?1 or " + "lower(c." + "fam" + ") LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();*/




}
