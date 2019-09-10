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
    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)//, fetch=FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList<>();

    //group - из другой таблицы
    //мапедбай - переменная из другого класса
    @OneToMany(mappedBy = "gruppa", targetEntity = Student.class, fetch = FetchType.EAGER)//-fetch
    private List<Student> students = new ArrayList<>();

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


    //добавляем студента в список
    public void addStudent(Student student){
        //если список не содержит студента, то добавляем его
        if (!(students.contains(student))){
            students.add(student);
        }
    }


    //удаляем студента из группы
    public void deleteStudent(Student student){
        if (students.contains(student)) {
            students.remove(student);
        }
    }

        //добавляем преподавателя в список
        public void addTeacher(Teacher teacher){
        //если список не содержит, то добавляем его
        if (!(teachers.contains(teacher))){
            teachers.add(teacher);
            }
        }

        //удаляем преподавателя из списка
        public void deleteTeacher(Teacher teacher){
            if (teachers.contains(teacher)){
                teachers.remove(teacher);
            }
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