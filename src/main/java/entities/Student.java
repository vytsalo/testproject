package entities;

import javax.persistence.*;

@Entity
@Table(name="human")
public class Student extends Human  {


//id удалить



    @Id
    @Column(name="student_id")
    @GeneratedValue
    private long id;

    //Группа, в которой учится студент
    @ManyToOne
    @JoinColumn(name="fk_groups")//имя - любое присоединяемое
    private Group group;

    public Student() {}

    public Student(String fam, String name, String otch, String date_of_birth, String phone_number, Group group) {
        super(fam, name, otch, date_of_birth, phone_number);
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "group=" + group +
                "} " + super.toString();
    }
}
