package dao;
        import entities.Group;
        import java.util.List;


        //Object
//public interface GroupDao <T>{
public interface GroupDao {
    void add(Group group);
    List<Group> getGroupsList();
    void update(Group group);
    Group findById(Long groupId);
    void delete(Long groupId);
}