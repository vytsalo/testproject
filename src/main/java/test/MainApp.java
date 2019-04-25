package test;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.StudentService;
import service.TeacherService;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        //Список групп
        ArrayList<Group> groupsList = new ArrayList();

        groupsList.add(new Group("442"));
        groupsList.add(new Group("522"));
        groupsList.add(new Group("251"));
        groupsList.add(new Group("332"));



        /*---------------------------------------------------------------------------------------------------------*/
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(config.AppConfig.class);//config.appconfig.class



        //Студенты


        //.class
        StudentService studentService = context.getBean(StudentService.class);

        studentService.add(new Student("Vasiliev","Vasiliy","Vasilievich","02.04.1990","79051453382", groupsList.get(0)));
        studentService.add(new Student("Vitaliyev","Vitaliy","Vitalievich","02.05.1995","9115484545",  groupsList.get(1)));
        studentService.add(new Student("Sergeev","Sergey","Sergeevich","02.12.1980","79114658955",  groupsList.get(2)));

        //В разных контекстах делать?

        //Преподаватели

        TeacherService teacherService = context.getBean(TeacherService.class);

        //костыль с нуловыми группами
        teacherService.add(new Teacher("Ivanov","Ivan","Ivanovich","13.11.1980","79534527778"));
        teacherService.add(new Teacher("Antonov","Anton","Antonovich","13.11.1979","79534547778"));
        teacherService.add(new Teacher("Sidorov","Sidr","Sidorovich","27.02.1950","79531527778"));
        teacherService.add(new Teacher("Petrov","Petr","Petrovich","25.12.1965","79534457778"));

        System.out.println("______________________________");
        System.out.println("_______Студенты_______________");
        System.out.println("______________________________");

        List <Student> studentsList = studentService.getStudentsList();


        for (Student student: studentsList)
            System.out.println(student.toString());



        System.out.println("______________________________");
        System.out.println("_______Преподаватели__________");
        System.out.println("______________________________");


        List <Teacher> teacherList = teacherService.getTeachersList();


        for (Teacher teacher: teacherList)
            System.out.println(teacher.toString());

        context.close();



        List<Teacher> tslist= new ArrayList();
        tslist.add(new Teacher("Ivanov","Ivan","Ivanovich","13.11.1980","79534527778"));
        tslist.add(new Teacher("Antonov","Anton","Antonovich","13.11.1979","79534547778"));
        tslist.add(new Teacher("Sidorov","Sidr","Sidorovich","27.02.1950","79531527778"));
        tslist.add(new Teacher("Petrov","Petr","Petrovich","25.12.1965","79534457778"));


        groupsList.get(0).setTeachers(tslist);
        groupsList.get(1).setTeachers(tslist);
        groupsList.get(2).setTeachers(tslist);
        groupsList.get(3).setTeachers(tslist);


        List<Student> stdlist= new ArrayList();

        stdlist.add(new Student("Vasiliev","Vasiliy","Vasilievich","02.04.1990","79051453382", groupsList.get(0)));
        stdlist.add(new Student("Vitaliyev","Vitaliy","Vitalievich","02.05.1995","9115484545",  groupsList.get(1)));
        stdlist.add(new Student("Sergeev","Sergey","Sergeevich","02.12.1980","79114658955",  groupsList.get(2)));

        groupsList.get(0).setStudents(stdlist);
        groupsList.get(1).setStudents(stdlist);
        groupsList.get(2).setStudents(stdlist);
        groupsList.get(3).setStudents(stdlist);


        for (Group group: groupsList)
            System.out.println(group.toString());


    }

}