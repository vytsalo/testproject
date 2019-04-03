package entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class Human{

  /*  @Id
    @Column
    @GeneratedValue
    private long id;*/

    @Column
    private String fam;

    @Column
    private String name;

    @Column
    private String otch;

    @Column
    private String date_of_birth;

    @Column
    private String phone_number;

   /* public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
*/
    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public Human() {}

    public Human(String fam, String name, String otch, String date_of_birth, String phone_number) {
        this.fam = fam;
        this.name = name;
        this.otch = otch;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
    }
}