package entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="student")
@SuppressWarnings("unused")
public class Student extends Human implements Serializable {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Группа, в которой учится студент
    @ManyToOne(fetch = FetchType.EAGER)//-fetch ЛЕЗИ?
    @JoinColumn(name="fk_groups")//имя - любое присоединяемое
    private Group gruppa;

    //new PersistentList()
    @SuppressWarnings("unused")
    public Student() {}

    public Student(String fam, String name, String otch, String date_of_birth, String phone_number, Group gruppa) {
        super(fam, name, otch, date_of_birth, phone_number);
        this.gruppa = gruppa;
    }

    public long getId() {
        return id;
    }

    @Override
    //@Transactional
    //gruppa.getStudents().toString();
    //gruppa.getTitle();
    //gruppa.get...
    public String toString() {
        return "Student{" +
                "id = " +  id +
                " gruppa = " + gruppa.toString() +
                 super.toString() + "}\n";
    }/*
    public String toString() {
        return "Student{" +
                "id = " +  id +
                " gruppa = " +
                gruppa.getTitle() +
                gruppa.getStudents().toString() +
                gruppa.getTeachers().toString() +
                 super.toString() + "}\n";
    }
*/


    //гетерами конструктор новый
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

 /*   public static String toStrung(List<Student> students) {
        String str="";
        for (int i = 0; i < students.size(); i++) {
            str+= students.get(i).getFam() + " " +
                    students.get(i).getName() + " " +
                    students.get(i).getOtch() + " " +
                    students.get(i).getDate_of_birth() + " " +
                    students.get(i).getPhone_number() + "\n";
        }
        return str;
    }
*/

}