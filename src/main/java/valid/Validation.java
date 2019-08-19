package valid;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

//валидатор для каждого класса в отдельности?
//зачем тогда валидация над полями
public class Validation {

    //Отдельный класс ModelValidation
    public static Boolean isValid(Object object) {

        //Прямо в метод вбить?
        ValidatorFactory vf = javax.validation.Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        int errorSize = constraintViolations.size();

        if (errorSize != 0) {

            //Надо ли в окно выводить или как?
            System.out.println(object.getClass().getName());//"Объект класса " + //get id + имя класса

            System.out.println(String.format("Кол-во ошибок: %d", errorSize));

            for (ConstraintViolation<Object> cv : constraintViolations)
                System.out.println(String.format(
                        "Ошибка при вводе свойства \"%s\"! Значение: \"%s\". %s\n",
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));

            return false;
        } else {
            System.out.println("Объект прошел валидацию");
            return true;
        }
    }

}
