package entities;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//груп переименовать - зарезервированное и отовсюду убрать
//зарезервированное в таблице
//сделать рефактор gruppa - group
@Table(name="gruppa")
//@Proxy(lazy=false)
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

    @Id
    @Column(name="group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Название группы
    @Column
    private String title;

    //Список преподавателей в этой группе
    //Мапедбай - связь с другим классом. - переменная групс, которая тоже замаплена как менитумени
    //Ленивую инициализацию сделать в каком-то из классов, чтобы не было бесконечной инициализации
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "groups", fetch = FetchType.LAZY)//, fetch=FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList();

    //group - из другой таблицы
    //мапедбай - переменная из другого класса
    @OneToMany(mappedBy = "gruppa", targetEntity = Student.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)//-fetch
    private List<Student> students = new ArrayList();

   /* @Override
    public String toString() {
        return  "__________________________\n" +
                "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                "\n teachers:{\n" + Teacher.toStrung(this.getTeachers()) + "\n}" +
                "\n students:{'\n" + Student.toStrung(this.getStudents()) + "\n}" +
                "}\n" +
                "__________________________\n";
    }
*/

   //контекст закрыт, а метод вызывается
    @Override
    //@Transactional
    public String toString() {
       /*
        Hibernate.initialize(Group.class);
        Hibernate.initialize(Student.class);
        Hibernate.initialize(Teacher.class);
     */

     /*  Group group = new Group("ыыы");
       group.getChilds().size();*/
       return "Group{" +
                "id=" + id +
                ", title='" + title + '}';
   }

    //мапедбай.гетКоммент инитиалайз.

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

    public long getId() {
        return id;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @SuppressWarnings("unused")
    public Group() {}

    public Group(long id, String title, List<Teacher> teachers, List<Student> students) {
        this.id = id;
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

}
