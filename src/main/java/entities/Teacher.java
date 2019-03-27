package entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="teacher")
public class Teacher extends Human{

    //список групп, в которых преподает
    @ManyToMany
    public ArrayList<Group> groups = new ArrayList();

    @ManyToMany
    public ArrayList<Group> getGroups() {
        return groups;
    }


    //не связано
    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public Teacher(String fam, String name, String otch, String date_of_birth, String phone_number, ArrayList<Group> groups) {
        super(fam, name, otch, date_of_birth, phone_number);
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "groups=" + groups +
                "} " + super.toString();
    }
}