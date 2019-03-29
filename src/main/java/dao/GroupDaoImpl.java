package dao;

import entities.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {

    @Override
    public List<Group> getGroupsList(){
        return new ArrayList<Group>();
    }
}
