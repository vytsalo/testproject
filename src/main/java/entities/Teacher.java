package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teacher")
public class Teacher extends Human{

    //порядок связи?
    //список групп, в которых преподает


    //Уникальные, правила именования
    @Id
    @Column
    private long id;


    //Почему нельзя арей лист сразу. почему с листа начинать?
    //ерей лист - коллекция
    //связь мени ту мени
    @ManyToMany
    //новая таблица - связи группы и тичера
    @JoinTable(name="teacher_group",
            joinColumns=
                    @JoinColumn (name="id_teacher"),//имена свои или существующие
            inverseJoinColumns=
                    @JoinColumn(name="id_group"))
    private List<Group> groups = new ArrayList();



    public List<Group> getGroups() {
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

    public Teacher(){}
}