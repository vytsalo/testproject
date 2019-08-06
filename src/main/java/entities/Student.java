package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="student")
@SuppressWarnings("unused")//--
public class Student extends Human implements Serializable {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //Группа, в которой учится студент
    @ManyToOne(fetch = FetchType.EAGER)//-fetch ЛЕЗИ?
    @JoinColumn(name="fk_groups")//имя - любое присоединяемое
    private Group gruppa;

    //new PersistentList()
    @SuppressWarnings("unused")
    public Student() {}

    public Student(String fam, String name, String otch, Date date_of_birth, String phone_number, Group gruppa) {
        super(fam, name, otch, date_of_birth, phone_number);
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

}