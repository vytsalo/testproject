package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="group")
public class Group implements Serializable  {

    public Group(String title, ArrayList<Teacher> teachers, ArrayList<Student> students) {
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

    public Group(String title) {
        this.title = title;
    }

    @Id
    @Column(name="group_id")
    @GeneratedValue
    private long id;

    //Название группы
    @Column
    private String title;

    /*
    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Teacher.class)
    @JoinTable(name = "groups_teacher",
            //имя колонки id с этой таблицы
            joinColumns = { @JoinColumn(name = "group_id") },
            //имя колонки id со второй таблицы (внешний ключ)
            inverseJoinColumns = { @JoinColumn(name = "teacher_id") })*/


    //Список преподавателей в этой группе

    //Мапедбай - связь с другим классом. - переменная групс, которая тоже замаплена как менитумени
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "groups")
    private List<Teacher> teachers = new ArrayList();

    //group - из другой таблицы
    //мапедбай - переменная из другого класса
    @OneToMany(mappedBy = "group", targetEntity = Student.class, cascade=CascadeType.ALL)
    private List<Student> students = new ArrayList();

    @Override
    public String toString() {
        return "Group{" +
                "title='" + title + '\'' +
                ", teachers=" + teachers +
                ", students=" + students +
                '}';
    }

    public Group() {}
}
