package dao;

import entities.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements EntitiesDao<Student>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Student student) {
        em.persist(student);
    }

    @Override
    public List<Student> getList(){
        CriteriaQuery<Student> criteriaQuery = em.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void update(Student student){
        em.merge(student);
    }

    @Override
    public Student findById(Long studentId){
        Student student=em.find(Student.class, studentId);
        if (student==null)
            throw new EntityNotFoundException("Студент с ID = " + studentId + " не найден");
        return student;
    }

    @Override
    public void delete(Long studentId){
        Student student = em.find(Student.class, studentId);
        if (student != null) em.remove(student);
        else throw new EntityNotFoundException("Студент с ID = " + studentId + " не найден");
    }


    //Поиск по тайтлу группы
    @Override
    public List<Student> searchByString(String str) {

        //добавить разделители между словами " "
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        Expression concatForOtch = criteriaBuilder.concat(criteriaBuilder.lower(studentRoot.get("otch"))," ");

        Expression concatForName = criteriaBuilder.concat(criteriaBuilder.lower(studentRoot.get("name"))," ");

        Expression concatForFam = criteriaBuilder.concat(criteriaBuilder.lower(studentRoot.get("fam"))," ");

        Expression almostFinalExpression = criteriaBuilder.concat(concatForName,concatForOtch);

        Expression finalExpression = criteriaBuilder.concat(concatForFam,almostFinalExpression);

        Expression fullExpression = criteriaBuilder.concat("%", finalExpression);

        //"%fam% %name%  %otch%"
        Predicate pr = criteriaBuilder.like(fullExpression,"%" + str.toLowerCase() + "%");

        //вхере
        criteriaQuery.where(pr);
        //Сортируем по убыванию по полю ID
        criteriaQuery.orderBy(criteriaBuilder.desc(studentRoot.get("id")));

        //убираем повторки
        criteriaQuery.distinct(true);

        return em.createQuery(criteriaQuery).getResultList();

    }
    
    
}