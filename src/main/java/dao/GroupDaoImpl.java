package dao;

import entities.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import test.HibernateUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {

    SessionFactory session = HibernateUtil.getSessionFactory();
    Session cr = session.getCurrentSession();

    //чо?!
    @Transactional
    @Override
    public void setGroupsList(List<Group> p){
        //фори
        for (int i = 0; i < p.size(); i++)
            cr.saveOrUpdate(p.get(i));
    }
}
