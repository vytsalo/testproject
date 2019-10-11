package controller;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO addTeacherServices


//почему то вызывается только при открытии формы
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

    //не заходит
    //в какой формат переводит(для вывода)
    @Override
    public String getAsText() {

        if (getValue() == null) {
            return "";
        } else {

            //new SimpleDateFormat("dd.MM.yyyy").format((Date)getValue());
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = (Date) getValue();

            String gg = dateFormat.format(date);

            return gg;// работает, если вызывать метод toString к этому объекту Date
        }
    }

    public DateEditor() {
    }

}
