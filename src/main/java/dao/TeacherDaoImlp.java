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


    //Поиск по всем полям по строке
    @Override
    public List<Teacher> searchByQuery(String query) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Teacher.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("fam", "name", "otch", "phoneNumber")
                .matching(query)
                .createQuery();

        Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Teacher.class);
        List result = jpaQuery.getResultList();

        return result;
    }


    @Override
    public List<Teacher> searchByString(String str) {
        //http://jquery.malsup.com/form/



        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> TeacherRoot = criteriaQuery.from(Teacher.class);


//https://stackoverflow.com/questions/16611904/ignorecase-in-criteria-builder-in-jpa
        Predicate predicateForBlueColor
                = criteriaBuilder.equal(TeacherRoot.get("color"), "blue");
        Predicate predicateForRedColor
                = criteriaBuilder.equal(TeacherRoot.get("color"), "red");
        Predicate predicateForColor
                = criteriaBuilder.or(
                        predicateForBlueColor,
                        predicateForRedColor,
                        criteriaBuilder.equal(criteriaBuilder.lower(TeacherRoot.get("color")), str.toLowerCase()));

        //adding where like
        //https://stackoverflow.com/questions/4635777/hibernate-jpa-criteriabuilder-ignore-case-queries
        //https://www.baeldung.com/jpa-and-or-criteria-predicates

        //predicateForFam



/*
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Person> personCriteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = personCriteriaQuery.from(Person.class);

        personCriteriaQuery.select(personRoot);
        personCriteriaQuery.where(criteriaBuilder.like(personRoot.get(Person_.description), "%"+filter.getDescription().toUpperCase()+"%"));
        List<Person> pageResults = entityManager.createQuery(personCriteriaQuery).getResultList();
        */





        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = cb.createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        criteriaQuery.select(root);
        criteriaQuery
                    .where(cb.equal(root.get("fam"), str))
                    .where(cb.equal(root.get("phoneNumber"),str));// toUpperCase() in the end where
        return em.createQuery(criteriaQuery)
                .getResultList();

//https://www.baeldung.com/jpa-and-or-criteria-predicates


    //и в SQL сделать
      /*  return em.createQuery(
                "SELECT c FROM Teacher c WHERE to_char(c." + "dateOfBirth, 'yyyy-mm-dd'" + ") LIKE ?1 or " + "lower(c." + "fam" + ") LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();*/





    }


    /*

    select * from teacher where(fam like ? && name like ?)


    */


/*
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
    Root<Post> root = criteria.from(Post.class);

criteria.where(
        builder.equal(root.get("owner"), "Vlad")
        );

    List<Post> posts = entityManager
            .createQuery(criteria)
            .getResultList();

    assertEquals(1, posts.size());
    */


}
