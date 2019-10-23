package dao;

import java.util.List;

/**
 * Created by vasilevvs on 23.10.2019.
 * interface for all Entities
 */
public interface EntitiesDao<T> {
    //Добавление объекта
    void add(T entity);
    //получить список объектов
    List<T> getList();
    //обновление
    void update(T entity);
    //поиск сущности по ID
    T findById(Long entityId);
    //удаление сущности по ID
    void delete(Long entityId);
    //поиск списка сущностей по ID
    List<T> searchByString(String str);
}





