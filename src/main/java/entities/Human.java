package entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@SuppressWarnings("unused")
public abstract class Human{


    //На процессформ чтобы нельзя было перейти напрямую, а только с формы



    /*

    hibernate validation example

    @NotBlank применяется только к строкам и проверяет, что строка не пуста.
@NotNull применяется к CharSequence, Collection, Map или Array и проверяет, что объект не равен null. Но при этом он может быть пуст.
@NotEmpty применяется к CharSequence, Collection, Map или Array и проверяет, что он не null имеет размер больше 0.

Аннотация @Size(min=6) пропустит строку состоящую из 6 пробелов и/или символов переноса строки, а @NotBlank не пропустит.


только это


<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.13.Final</version>
</dependency>






   <dependency>
   <groupId>org.hibernate</groupId>
   <artifactId>hibernate-validator</artifactId>
   <version>6.0.17.Final</version>
</dependency>
<dependency>
   <groupId>org.glassfish</groupId>
   <artifactId>javax.el</artifactId>
   <version>3.0.1-b09</version>
</dependency>



    */





    //Валидация не работает
    @Column
    @NotNull//в отдельную кастомную аннотацию сделать?
    @Size(min = 2, max = 3, message = "Длина поля должна быть не менее 2, и не более 3 символов")
    private String fam;


    //@NotNullMin2Max35
    @Column
    @NotNull
    @Size(min = 2, max = 35, message = "Длина поля должна быть не менее 2, и не более 35 символов")
    private String name;

    @Column
    @NotNull//не объект же. какой нафиг нулл?
    @Size(min = 2, max = 35, message = "Длина поля должна быть не менее 2, и не более 35 символов")
    private String otch;

    @Column//в дату?
    @NotNull
    //@Past
    //дата
    private String date_of_birth;

    @Column
    @NotNull
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