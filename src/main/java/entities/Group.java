package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//груп переименовать - зарезервированное и отовсюду убрать
//зарезервированное в таблице
//сделать рефактор gruppa - group
@Table(name="gruppa")
@SuppressWarnings("unused")
public class Group implements Serializable  {

    public Group(String title, ArrayList<Teacher> teachers, ArrayList<Student> students) {
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

    public Group(String title) {
        this.title = title;
    }

    //объектный тип ID сделать
    @Id
    @Column(name="group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Название группы
    @Column
    private String title;

    //Список преподавателей в этой группе
    //Мапедбай - связь с другим классом. - переменная групс, которая тоже замаплена как менитумени
    //Ленивую инициализацию сделать в каком-то из классов, чтобы не было бесконечной инициализации
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)//, fetch=FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList();

    //group - из другой таблицы
    //мапедбай - переменная из другого класса
    @OneToMany(mappedBy = "gruppa", targetEntity = Student.class, fetch = FetchType.LAZY)//-fetch
    private List<Student> students = new ArrayList();

    @Override
    public String toString() {
       return "Group{" +
                "id=" + id +
                ", title='" + title + '}';
   }

    @SuppressWarnings("unused")
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

    public Long getId() {
        return id;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @SuppressWarnings("unused")
    public Group() {}

    public Group(Long id, String title, List<Teacher> teachers, List<Student> students) {
        this.id = id;
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

}
