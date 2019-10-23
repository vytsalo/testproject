package service;

import dao.EntitiesDao;
import dao.GroupDao;
import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private EntitiesDao<Group> gr_dao;

    @Transactional
    @Override
    public void add(Group group){
        gr_dao.add(group);
    }

    @Transactional
    @Override
    public List<Group> getGroupsList(){
        return gr_dao.getList();
    }

    @Transactional
    @Override
    public void update(Group group){
        gr_dao.update(group);
    }

    @Transactional
    @Override
    public Group findById(Long groupId){
        return (Group)gr_dao.findById(groupId);
    }

    @Transactional
    @Override
    public void delete(Long groupId){
        gr_dao.delete(groupId);
    }

}
