package entities;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@MappedSuperclass
public abstract class Human{


    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String fam;

    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String name;

    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String otch;

    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Past(message = "Время должно быть в прошлом")
    @Temporal(value = TemporalType.DATE)//@Type(type = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date_of_birth;

    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 15, max = 15, message = "Длина поля должна быть 15 символов")//сколько символов - только из-за шаблона
    //8(963) 145-8916
    private String phone_number;

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

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Human() {}

    public Human(String fam, String name, String otch, Date date_of_birth, String phone_number) {
        this.fam = fam;
        this.name = name;
        this.otch = otch;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return  " fam=" + this.getFam() +
                " name=" + this.getName() +
                " otch=" + this.getOtch() +
                " date_of_birth=" + new SimpleDateFormat("dd.MM.yyyy").format(this.getDate_of_birth()) +
                " phone_number=" + this.getPhone_number();
    }

}