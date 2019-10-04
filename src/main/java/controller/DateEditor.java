package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.TeacherService;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



//TODO addTeacherServices


//почему то вызывается только при открытии формы
@Component
public class DateEditor extends PropertyEditorSupport {


    @Autowired
    TeacherService teacherService;



    //Из какого формата конвертирует в бд(принимает)
    @Override
    public void setAsText(String value) {
        try {
            setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
        } catch (Exception ex) {
            setValue(null);
        }
    }

    //не заходит
    //в какой формат переводит(для вывода)
    @Override
    public String getAsText() {

        //dd.MM.yyyy
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = (Date) getValue();

        String gg = dateFormat.format(date);

        //new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());


        return gg;

        /*

    String sdf = "";

        String val = getValue().toString() ;



        if (val!=null){
        try {
            //Формат в бд
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");





            //dateFormat.setLenient(true);//-

            System.out.println(val);
            //какая переменная должна быть
            Date date = dateFormat.parse(val);
            //Формат, который нужен
            sdf = new SimpleDateFormat("dd.MM.yyyy").format(date);

            System.out.println(sdf);
            } catch (ParseException p) {p.printStackTrace();}


                    }
        return sdf;*/
    }

    public DateEditor() {
    }

    public DateEditor(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
