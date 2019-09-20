package entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private List<Group> groups = new ArrayList();//<>

    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    //добавляем группу в список
    public void addGroup(Group group){
        //по id сравнивать?
        //просто добавить, а проверять по факту
        //if (!(this.getGroups().contains(group))){
            if (!(this.contains(group)))
            groups.add(group);
      //}

    }

    //удаляем группу из списка
    //сделать по id
    public void removeGroup(Group group){
        //если содержит группу, то удаляем ее
        //if (groups.contains(group))

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equals(group.getId()))
                groups.remove(i);//groupservice.findById(id)
        }
        //+тичера из группы
    }


    public Teacher(String fam, String name, String otch, Date date_of_birth, String phone_number, ArrayList<Group> groups) {
        super(fam, name, otch, date_of_birth, phone_number);
        this.groups = groups;
    }

    @SuppressWarnings("unused")
    public Teacher(String fam, String name, String otch, Date date_of_birth, String phone_number) {
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



    //equal не поломает ли все
    //сравниваем только id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher that = (Teacher) o;
        return this.getId().equals(that.getId());
    }


    public boolean contains(Group group){
        List<Group> temp = this.getGroups();

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getId().equals(group.getId())){

                return true;

            }
        }
        return false;
    }
}