package dao;

import java.util.List;

/**
 * Created by vasilevvs on 23.10.2019.
 * interface for all Entities
 */
public interface EntitiesDao<T> {
    void add(T entity);
    List<T> getList();
    void update(T entity);
    T findById(Long entityId);
    void delete(Long entityId);
}
