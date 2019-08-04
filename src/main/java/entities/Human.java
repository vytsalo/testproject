package entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@SuppressWarnings("unused")
public abstract class Human{


    //На процессформ чтобы нельзя было перейти напрямую, а только с формы

    //Валидация не работает
    @Column
    //в отдельную кастомную аннотацию сделать?


    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String fam;

    //@NotNullMin2Max35
    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String name;

    @Column
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String otch;

    @Column//в дату?

    //Определенное ко-вол сомволов
    @NotNull(message = "Поле не может быть NULL")//имеет ли смысл
    @Size(min = 10, max = 10, message = "Длина поля должна быть 10 символов")
    //@Past
    //дата
    private String date_of_birth;

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

    @Override
    public String toString() {
        return  " fam=" + this.getFam() +
                " name=" + this.getName() +
                " otch=" + this.getOtch() +
                " date_of_birth=" + this.getDate_of_birth() +
                " phone_number=" + this.getPhone_number();
    }

}