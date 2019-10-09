package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="student", schema = "public")//, schema = "public"
public class Student extends Human implements Serializable {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //Группа, в которой учится студент
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name="fk_groups")//имя - любое присоединяемое
    //главное управляющее поле, все делается через него, а потом уже через группу
    private Group gruppa;

    public Student() {}

    public Student(String fam, String name, String otch, Date dateOfBirth, String phoneNumber, Group gruppa) {
        super(fam, name, otch, dateOfBirth, phoneNumber);
        this.gruppa = gruppa;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id = " +  id +
                " gruppa = " + gruppa +
                 super.toString() + "}\n";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGruppa() {
        return gruppa;
    }

    @SuppressWarnings("unused")
    public void setGruppa(Group gruppa) {
        this.gruppa = gruppa;
    }

    //сравнивает по id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return this.id == that.id;
    }

}