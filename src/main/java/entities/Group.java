package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//груп переименовать - зарезервированное и отовсюду убрать

//не существует?
@Table(name="gruppa", schema = "myschema")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Название группы
    @Column
    private String title;

    //Список преподавателей в этой группе

    //Мапедбай - связь с другим классом. - переменная групс, которая тоже замаплена как менитумени
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "groups")
    private List<Teacher> teachers = new ArrayList();

    //group - из другой таблицы
    //мапедбай - переменная из другого класса
    @OneToMany(mappedBy = "gruppa", targetEntity = Student.class, cascade=CascadeType.ALL)
    private List<Student> students = new ArrayList();

    @Override
    public String toString() {
        return "Group{" +
                "title='" + title + '\'' +
                ", teachers=" + teachers +
                ", students=" + students +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Group() {}
}
