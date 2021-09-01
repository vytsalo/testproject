package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="student")
public class Student extends Human implements Serializable {

    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //Группа, в которой учится студент

    /* LazyInitializationException could not initialize proxy - no Session
    Когда фетч тайп лези и хибернейт пытается вызвать объект, а вместо него прокси.
    todo прочитать про прокси объекты

    * 1) fetchType = eager
    * распространяется на все запросы
    *
    * 2) <beans:prop key="hibernate.enable_lazy_load_no_trans">true</beans:prop>
    * Будет запускать новую транзакцию при обращении к связанным сущностям. Не рекомендуется - сильно нагружает систему
    *
    * 3) criteria.setFetchMode("roles", FetchMode.EAGER);
    * best - настраивается по запросу(в критерии). Загружает дочерние сущности в том же запросе
     *  */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    //@JoinColumn(name="fk_groups")//имя - любое присоединяемое todo БЫЛО
    //главное управляющее поле, все делается через него, а потом уже через группу
    @JoinTable(name = "student_groups",
            //имя колонки id с этой таблицы
            joinColumns = { @JoinColumn(name = "student_id") },
            //имя колонки id со второй таблицы (внешний ключ)
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private Group group;

    public Student() {}

    public Student(String fam, String name, String otch, Date dateOfBirth, String phoneNumber, Group group) {
        super(fam, name, otch, dateOfBirth, phoneNumber);
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id = " +  id +
                " gruppa = " + group +
                 super.toString() + "}\n";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group gruppa) {
        this.group = gruppa;
    }

}