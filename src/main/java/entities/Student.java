package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="student")
public class Student extends Human implements Serializable {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Группа, в которой учится студент
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_groups")//имя - любое присоединяемое
    private Group gruppa;

    @SuppressWarnings("unused")
    public Student() {}

    public Student(String fam, String name, String otch, String date_of_birth, String phone_number, Group gruppa) {
        super(fam, name, otch, date_of_birth, phone_number);
        this.gruppa = gruppa;
    }

    public long getId() {
        return id;
    }
/*
    @Override
    public String toString() {
        return "____________________________________\nStudent{" +
                "id = " +  this.getId() +
                "gruppa = " + this.getGruppa().toString() +
                "} " + super.toString();
    }*/

    public void setId(long id) {
        this.id = id;
    }

    public Group getGruppa() {
        return gruppa;
    }

    @SuppressWarnings("unused")
    public void setGruppa(Group gruppa) {
        this.gruppa = gruppa;
    }
}