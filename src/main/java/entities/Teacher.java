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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(targetEntity = Group.class,fetch=FetchType.EAGER)
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

    @Override
    public String toString() {
        return "Teacher{"  + id + " "+
                "groups=" + groups +
                "} " + super.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher(){}
}