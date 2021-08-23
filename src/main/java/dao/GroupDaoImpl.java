package dao;

import entities.Group;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GroupDaoImpl implements EntitiesDao<Group> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Group group) {
        em.persist(group);
    }

    @Override
    public List<Group> getList() {
        CriteriaQuery<Group> criteriaQuery = em.getCriteriaBuilder().createQuery(Group.class);
        criteriaQuery.from(Group.class);
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
        if (group != null) { em.remove(group);}
        else throw new EntityNotFoundException("Группа с ID = " + groupId + " не найдена");
    }

    //Поиск по тайтлу группы
    @Override
    public List<Group> searchByTitle(String str) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> groupRoot = criteriaQuery.from(Group.class);

        Predicate predicateForTitle
                = criteriaBuilder.like(criteriaBuilder.lower(groupRoot.get("title")), "%" + str.toLowerCase() + "%");

        criteriaQuery.where(predicateForTitle);
        criteriaQuery.orderBy(criteriaBuilder.desc(groupRoot.get("id")));
        criteriaQuery.distinct(true);

        return em.createQuery(criteriaQuery).getResultList();

    }

}
