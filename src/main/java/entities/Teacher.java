package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teacher")
@SuppressWarnings("unused")
public class Teacher extends Human implements Serializable {

    @Id
    @Column(name="teacher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Group.class,fetch=FetchType.EAGER)
    @JoinTable(name = "teacher_groups",
            //имя колонки id с этой таблицы
            joinColumns = { @JoinColumn(name = "teacher_id") },
            //имя колонки id со второй таблицы (внешний ключ)
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<Group> groups = new ArrayList();

    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public Teacher(String fam, String name, String otch, String date_of_birth, String phone_number, ArrayList<Group> groups) {
        super(fam, name, otch, date_of_birth, phone_number);
        this.groups = groups;
    }

    @SuppressWarnings("unused")
    public Teacher(String fam, String name, String otch, String date_of_birth, String phone_number) {
        super(fam, name, otch, date_of_birth, phone_number);
    }

    //тичер вызывает группу, а в группе есть тичер
    @Override
    public String toString() {
        return "Teacher{"  + id + " "+
                "groups=" + groups +
                "} " + super.toString();
    }

/*    public static String toStrung(List<Teacher> teachers) {
        String str="";
        for (int i = 0; i < teachers.size(); i++) {
            str+= teachers.get(i).getFam() + " " +
                  teachers.get(i).getName() + " " +
                  teachers.get(i).getOtch() + " " +
                  teachers.get(i).getDate_of_birth() + " " +
                  teachers.get(i).getPhone_number() + "\n";
        }
        return str;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Teacher(){}
}