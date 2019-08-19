package valid;

import entities.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
//имплементируем интерфейс валидатор
public class StudentValidator implements Validator  {
    @Override
    public boolean supports(Class<?> clazz) {
        //объект какого класса валидируем
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //сущность валидируем
        Student student = (Student) target;
        //Проверяем поля на пустоту
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","Имя пустое");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fam", "", "Фамилия пустая");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "otch", "", "Фамилия пустая");

        //проверки ифами
        if (student.getName().length()<3)
            errors.rejectValue("name","", "Имя пользователя меньше 3х символов");

    }
}