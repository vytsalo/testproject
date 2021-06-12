package controller;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
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

    //открывается при вызове у поля toString() метода
    @Override
    public String getAsText() {

        if (getValue() == null) {
            return "";
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = (Date) getValue();
            String gg = dateFormat.format(date);
            return gg;
        }
    }

    public DateEditor() {}

}
