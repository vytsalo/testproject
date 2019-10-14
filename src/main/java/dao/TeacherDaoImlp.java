package dao;

import entities.Teacher;
import org.hibernate.Criteria;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
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
                .onFields("fam", "name", "otch", "dateOfBirth", "phoneNumber")
                .matching(query)
                .createQuery();

        Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Teacher.class);
        List result = jpaQuery.getResultList();

        return result;
    }


    @Override
    public List<Teacher> searchByString(String str) {

//set parameter - как переменная в запросе

        // Работает только по 1 полю
/*
        return em.createQuery(
                "SELECT c FROM Teacher c WHERE lower(c.fam) LIKE lower(?1)")
                .setParameter(1, "%" + str + "%")
                .getResultList();
*/

//Сделать для каждого поля селект и юнион


        List<Teacher> q1= em.createQuery(
                "SELECT c FROM Teacher c WHERE lower(c.fam) LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();

        List<Teacher> q2= em.createQuery(
                "SELECT c FROM Teacher c WHERE lower(c.name) LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();

        List<Teacher> q3= em.createQuery(
                "SELECT c FROM Teacher c WHERE lower(c.otch) LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();

/* List<Teacher> q4= em.createQuery(
                "SELECT c FROM Teacher c WHERE c.dateOfBirth LIKE ?1")
                .setParameter("1", "%" + str + "%")
                .getResultList();*/

        List<Teacher> q5= em.createQuery(
                "SELECT c FROM Teacher c WHERE lower(c.phoneNumber) LIKE lower(?1)")
                .setParameter("1", "%" + str + "%")
                .getResultList();


        List<Teacher> results = new ArrayList<>();
        results.addAll(q1);
        results.addAll(q2);
        results.addAll(q3);
        //results.addAll(q4);
        results.addAll(q5);

        return results;
 /* return em.createQuery(
                "SELECT c FROM Teacher c WHERE lower(c.fam) LIKE lower(:str)" +
                        " UNION " +
                        "SELECT c FROM Teacher c WHERE lower(c.name) LIKE lower(:str)" +
                        " UNION " +
                        "SELECT c FROM Teacher c WHERE lower(c.otch) LIKE lower(:str)" +
                        " UNION " +
                        "SELECT c FROM Teacher c WHERE lower(c.phoneNumber) LIKE lower(:str)" +
                        " UNION " +
                        "SELECT c FROM Teacher c WHERE lower(c.dateOfBirth) LIKE lower(:str)")
                .setParameter("str", "%" + str + "%")
                .getResultList();*/



/*
 100% working
    return em.createQuery(
                "SELECT c FROM Teacher c WHERE c.fam LIKE ?1")
                .setParameter(1, "%" + str + "%")
                .getResultList();
        almost working
        return em.createQuery("from " + Teacher.class.getName()).setParameter("fam",str).getResultList();
*/


       /*
        //where like
        TypedQuery<Teacher> query =
                em.createQuery("FROM Teacher where " +
                        "fam=:" + str, Teacher.class);
        query.setParameter(1, "fam");
        return query.getResultList();
        Query query=em.createQuery("SELECT e FROM Employee e WHERE e.empId = ? and  e.empDepartment = ?");
        query.setParameter(1, employeId);
        query.setParameter(2, empDepartment);
*/

    }


}
