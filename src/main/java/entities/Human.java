package entities;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
//-LONG PATH

//                        @Parameter(name = "language", value = "English")
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Russian")
                })
        })
/*
* куча ограничений
* ищет только точное совпадение
* язык прописывать надо
* куча настроек
* */
@org.hibernate.search.annotations.Indexed
public abstract class Human{

    @Column
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotNull(message = "Поле не может быть NULL")
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String fam;

    @Column
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)

    @NotNull(message = "Поле не может быть NULL")
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String name;

    @Column
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)

    @NotNull(message = "Поле не может быть NULL")
    @Size(min = 2, max = 15, message = "Длина поля должна быть не менее 2, и не более 15 символов")
    private String otch;

    @Column
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotNull(message = "Поле не может быть NULL")
    @Past(message = "Время должно быть в прошлом")
    @Temporal(value = TemporalType.DATE)//@Type(type = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @Column
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotNull(message = "Поле не может быть NULL")
    @Size(min = 15, max = 15, message = "Длина поля должна быть 15 символов")
    //8(963) 145-8916
    private String phoneNumber;

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

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Human() {}

    public Human(String fam, String name, String otch, Date dateOfBirth, String phoneNumber) {
        this.fam = fam;
        this.name = name;
        this.otch = otch;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  " fam=" + this.getFam() +
                " name=" + this.getName() +
                " otch=" + this.getOtch() +
                " dateOfBirth=" + this.getDateOfBirth().toString() +
                " phoneNumber=" + this.getPhoneNumber();
    }

}