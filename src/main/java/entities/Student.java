package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="student")
public class Student extends Human implements Serializable {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //todo два раза записываются - поэтому дублируются?
    //Группа, в которой учится студент
    @ManyToOne(fetch = FetchType.EAGER)
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

    public Group getGroup() {
        return gruppa;
    }

    public void setGroup(Group gruppa) {
        this.gruppa = gruppa;
    }

}