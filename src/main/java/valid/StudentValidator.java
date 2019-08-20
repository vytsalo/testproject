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




    /* @Override
   public void validate(Object obj, Errors err) {

      ValidationUtils.rejectIfEmpty(err, "name", "user.name.empty");
      ValidationUtils.rejectIfEmpty(err, "email", "user.email.empty");
      ValidationUtils.rejectIfEmpty(err, "gender", "user.gender.empty");
      ValidationUtils.rejectIfEmpty(err, "languages", "user.languages.empty");

      User user = (User) obj;

      Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
      if (!(pattern.matcher(user.getEmail()).matches())) {
         err.rejectValue("email", "user.email.invalid");
      }

   }*/


}