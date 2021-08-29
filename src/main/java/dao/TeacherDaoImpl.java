package dao;

import entities.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class TeacherDaoImpl implements EntitiesDao<Teacher> {

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
/*
        Group group = student.getGroup();
        student.setGroup(null);
        ArrayList<Student> students = new ArrayList<>(group.getStudents());
        students.remove(student);
        group.setStudents(students);*/




       if (teacher != null) em.remove(teacher);
       else throw new EntityNotFoundException("Преподаватель с ID = " + teacherId + " не найден");
    }

    @Override
    public List<Teacher> searchByTitle(String str) {

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

    @Override
    public List<Teacher> getListWithPagination(int page, int size) {
        return null;
    }
}