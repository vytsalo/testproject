package test;

import entities.Group;

import java.util.Scanner;
import static valid.Validation.isValid;

public class ValidationTest {

    public static void main(String[] args) {

        System.out.println("Введите данные");

        Scanner t = new Scanner(System.in);
        //Scanner a = new Scanner(System.in);

        String title = t.nextLine();
        //int age = a.nextInt();

        Group group = new Group(title);



        if (isValid(group))
            System.out.println("Объект будет добавлен");

        //версии хибернейта сделать одинаковыми
/*

2019-08-04 22:17:26 DEBUG logging:19 - Logging Provider: org.jboss.logging.Log4jLoggerProvider
2019-08-04 22:17:26 INFO  Version:17 - HV000001: Hibernate Validator 5.2.1.Final
2019-08-04 22:17:26 DEBUG DefaultTraversableResolver:103 - Found javax.persistence.Persistence on classpath containing 'getPersistenceUtil'. Assuming JPA 2 environment. Trying to instantiate JPA aware TraversableResolver
2019-08-04 22:17:26 DEBUG DefaultTraversableResolver:114 - Instantiated JPA aware TraversableResolver of type org.hibernate.validator.internal.engine.resolver.JPATraversableResolver.
2019-08-04 22:17:26 DEBUG ValidationXmlParser:91 - Trying to load META-INF/validation.xml for XML based Validator configuration.
2019-08-04 22:17:26 DEBUG ResourceLoaderHelper:54 - Trying to load META-INF/validation.xml via TCCL
2019-08-04 22:17:26 DEBUG ResourceLoaderHelper:60 - Trying to load META-INF/validation.xml via Hibernate Validator's class loader
2019-08-04 22:17:26 DEBUG ValidationXmlParser:98 - No META-INF/validation.xml found. Using annotation based configuration only.
2019-08-04 22:17:26 DEBUG PlatformResourceBundleLocator:123 - ValidationMessages not found.
2019-08-04 22:17:26 DEBUG PlatformResourceBundleLocator:123 - ContributorValidationMessages not found.
2019-08-04 22:17:26 DEBUG PlatformResourceBundleLocator:120 - org.hibernate.validator.ValidationMessages found.

*/



    }


}
