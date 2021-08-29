package service;

import java.util.List;

public interface EntitiesService<T> {
    void add(T entity);

    List<T> getList();

    void update(T entity);

    T findById(Long entityId);

    void delete(Long entityId);

    List<T> findByParam(String str);

    List<T> getListWithPagination(int page, int size);
}