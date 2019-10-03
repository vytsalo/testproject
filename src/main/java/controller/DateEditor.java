package controller;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateEditor extends PropertyEditorSupport {

    //Из какого формата конвертирует в бд(принимает)
    @Override
    public void setAsText(String value) {
        try {
            setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
        } catch (Exception ex) {
            setValue(null);
        }
    }

    //в какой формат переводит(для вывода)
    @Override
    //не работает
    public String getAsText() {

        String sdf = "";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            dateFormat.setLenient(true);//-

            //какая переменная должна быть
            Date date = dateFormat.parse((String)getValue());
            sdf = new SimpleDateFormat("dd.MM.yyyy").format(date);

            System.out.println(sdf);
        } catch (ParseException p) {p.printStackTrace();}

        return sdf;
    }

}
