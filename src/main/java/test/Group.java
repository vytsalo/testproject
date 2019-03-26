package test;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name="group")
//дописать
//+связи
public class Group implements Serializable {

    public Group(String title, ArrayList<Teacher> teachers, ArrayList<Student> students) {
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }


    public Group(String title) {
        this.title = title;
    }

    @Id
    @Column
    private long id;


    //Название группы
    @Column
    private String title;

    //Список преподавателей в этой группе
    @ManyToMany
    ArrayList<Teacher> teachers = new ArrayList();


    @OneToMany(mappedBy = "group") //мапедбай - переменная из другого класса
    //Список студентов группы мени ту уан
    ArrayList<Student> students = new ArrayList();

    @Override
    public String toString() {
        return "Group{" +
                "title='" + title + '\'' +
                ", teachers=" + teachers +
                ", students=" + students +
                '}';
    }
}
