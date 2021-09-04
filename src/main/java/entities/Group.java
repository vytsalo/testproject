package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="groups")
public class Group implements Serializable {

    public Group(String title, ArrayList<Teacher> teachers, ArrayList<Student> students) {
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

    public Group(String title) {
        this.title = title;
    }

    //объектный тип ID
    @Id
    @Column(name="group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Название группы
    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 3, max = 35, message = "Длина поля должна быть не менее 3, и не более 35 символов")
    private String title;

    //Список преподавателей в этой группе
    //Мапедбай - связь с другим классом. - переменная групс, которая тоже замаплена как менитумени
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();

    //gruppa - из другой таблицы
    //мапедбай - переменная из другого класса
    @OneToMany(mappedBy = "group", targetEntity = Student.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();
/*

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;*/

    @Override
    public String toString() {
       return "Group{" +
                "id=" + id +
                ", title='" + title + '}';
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

    public Long getId() {
        return id;
    }

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

    //добавляем преподавателя в список
    public void addTeacher(Teacher teacher){
        //если список не содержит препода, то добавляем его
        if (!(teachers.contains(teacher))){
            teachers.add(teacher);
        }
    }



    public Group() {}

    public Group(Long id, String title, List<Teacher> teachers, List<Student> students) {
        this.id = id;
        this.title = title;
        this.teachers = teachers;
        this.students = students;
    }

    //EQUALS по ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group that = (Group) o;
        return this.getId().equals(that.getId());
    }

}