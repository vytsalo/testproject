package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="teacher")
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
    private List<Group> groups = new ArrayList();//<>

    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    //добавляем группу в список
    public void addGroup(Group group){

            if (!(this.groups.contains(group))){
                groups.add(group);
            }
    }

    //удаляем группу из списка
    //сделать по id?
    public void removeGroup(Group group){
        //если содержит группу, то удаляем ее
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equals(group.getId()))
                groups.remove(i);
        }
    }

    public Teacher(String fam, String name, String otch, Date dateOfBirth, String phoneNumber, ArrayList<Group> groups) {
        super(fam, name, otch, dateOfBirth, phoneNumber);
        this.groups = groups;
    }

    public Teacher(String fam, String name, String otch, Date dateOfBirth, String phoneNumber) {
        super(fam, name, otch, dateOfBirth, phoneNumber);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher that = (Teacher) o;
        return this.getId().equals(that.getId());
    }
}