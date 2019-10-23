package dao;

import entities.Group;

import java.util.List;

public interface GroupDao {
    void add(Group group);
    List<Group> getGroupsList();
    void update(Group group);
    Group findById(Long groupId);
    void delete(Long groupId);
}