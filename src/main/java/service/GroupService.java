package service;

import entities.Group;

import java.util.List;

public interface GroupService {
    void add(Group group);
    List<Group> getGroupsList();
    void update(Group group);
    Group findById(Long groupId);
    void delete(Long groupId);
}