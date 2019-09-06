package dao;

import entities.Group;
import entities.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@SuppressWarnings("unused")
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Group group) {
        em.persist(group);
    }

    @Override
    public List<Group> getGroupsList() {
        CriteriaQuery<Group> criteriaQuery = em.getCriteriaBuilder().createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void update(Group group) {
        em.merge(group);
    }

    @Override
    public Group findById(Long groupId) {
        Group group = em.find(Group.class, groupId);
        if (group == null) {
            throw new EntityNotFoundException("Группа с ID = " + groupId + " не найдена");
        }
        return group;
    }

    //удаляет сущность по id
    @Override
    public void delete(Long groupId) {
        Group group = em.find(Group.class, groupId);
        if (group != null) em.remove(group);
        else throw new EntityNotFoundException("Группа с ID = " + groupId + " не найдена");
    }


    public void addStudent(Student student){

    }

}
