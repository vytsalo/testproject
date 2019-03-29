package dao;
import entities.Group;

import java.util.List;

public interface GroupDao {

    //список всех групп
    List<Group> getGroupsList();
}
