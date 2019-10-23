package service;

import java.util.List;

/**
 * Created by vasilevvs on 23.10.2019.
 */

public interface EntitiesService<T> {
    void add(T entity);
    List<T> getList();
    void update(T entity);
    T findById(Long entityId);
    void delete(Long entityId);
    List<T> findByParam(String str);
}