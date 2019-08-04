package entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//груп переименовать - зарезервированное и отовсюду убрать
//зарезервированное в таблице
//сделать рефактор gruppa - group
@Table(name="gruppa")
@SuppressWarnings("unused")
public class Group implements Serializable {


/*    private static int MIN_CHAR_COUNT=3;
    private static int MAX_CHAR_COUNT=35;*/


 /*   @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 3,message = "Число символов не должно быть меньше 3")
    @Max(value = 35,message = "Число символов не должно быть больше 35")
     @Size(min = 2, max = 14) для стринга

    */

//@Pattern(regex=, flags=)
    //@Length(min=, max=)
    //@Past(дата в дейт)
//Checks whether the annotated date is in the past



    /*
    Дату переводить из стринг в date?
    как узнать какие зависимости не используеются

    https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-declaring-method-constraints

    поменять текстовые поля инпут на дейт емайл и т.д.


    */

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //Название группы
    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 3, max = 35, message = "Длина поля должна быть не менее 3, и не более 35 символов")
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

    //надо ли?
    public void setId(Long id) {
        this.id = id;
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
