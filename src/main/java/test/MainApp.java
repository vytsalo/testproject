package test;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.GroupService;
import service.StudentService;
import service.TeacherService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class MainApp {

   // Logger logger = new Logger().getLogger(MainApp.class);

    final static Logger logger = Logger.getLogger(MainApp.class);

    public static void main(String[] args) {

        //Список групп
        ArrayList<Group> groupsList = new ArrayList();

        groupsList.add(new Group("442"));
        groupsList.add(new Group("522"));
        groupsList.add(new Group("251"));
        groupsList.add(new Group("332"));

        //Список преподавателей
        List<Teacher> tslist= new ArrayList();
        tslist.add(new Teacher("Ivanov","Ivan","Ivanovich","13.11.1980","79534527778"));
        tslist.add(new Teacher("Antonov","Anton","Antonovich","13.11.1979","79534547778"));
        tslist.add(new Teacher("Sidorov","Sidr","Sidorovich","27.02.1950","79531527778"));
        tslist.add(new Teacher("Petrov","Petr","Petrovich","25.12.1965","79534457778"));

        //Список студентов
        List<Student> stdlist= new ArrayList();
        stdlist.add(new Student("Vasiliev","Vasiliy","Vasilievich","02.04.1990","79051453382", groupsList.get(0)));
        stdlist.add(new Student("Vitaliyev","Vitaliy","Vitalievich","02.05.1995","9115484545",  groupsList.get(1)));
        stdlist.add(new Student("Sergeev","Sergey","Sergeevich","02.12.1980","79114658955",  groupsList.get(2)));

        //Циклом адекватно
        //Передаем преподавателей в группу
        groupsList.get(0).setTeachers(tslist);
        groupsList.get(1).setTeachers(tslist);
        groupsList.get(2).setTeachers(tslist);
        groupsList.get(3).setTeachers(tslist);

        //Передаем студентов в группу
        groupsList.get(0).setStudents(stdlist);
        groupsList.get(1).setStudents(stdlist);
        groupsList.get(2).setStudents(stdlist);
        groupsList.get(3).setStudents(stdlist);


        /*-----------------------------Контексты------------------------------------------------------------------*/

      /*  AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(config.AppConfig.class);  */

        //ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");


        String configXmlLink = "C:\\Users\\vasilevvs\\Downloads\\НАДО ОТКРЫТЬ\\ембедеды с пожо\\testproject\\src\\main\\webapp\\WEB-INF\\spring-servlet.xml";

        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(configXmlLink);

        context.start();
      /*  AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(configXmlLink);*/



        /*ApplicationContext context =
                new ClassPathXmlApplicationContext(configXmlLink);
        */
        File f = new File(configXmlLink);
        if(f.exists() && !f.isDirectory()) {
            logger.info("Файл загружен");

            // do something
        } else logger.error("Файла нет");

        /*------------------------------------------------------------------------------------*/
        /*----------------------Группа--------------------------------------------------------*/


        /*fix object references an unsaved transient instance - save the transient instance
        beforeQuery flushing : entities.Student.gruppa -> entities.Group]*/
        GroupService groupService = context.getBean(GroupService.class);

        groupService.add(groupsList.get(0));
        groupService.add(groupsList.get(1));
        groupService.add(groupsList.get(2));
        groupService.add(groupsList.get(3));

        //GroupService
        List <Group> groupList = groupService.getGroupsList();


        for (Group group: groupList)
            System.out.println(group.toString());

        /*------------------------------------------------------------------------------------*/
        /*----------------------Студенты--------------------------------------------------------*/

        StudentService studentService = context.getBean(StudentService.class);
        //без группы добавить?
        studentService.add(new Student("Vasiliev","Vasiliy","Vasilievich","02.04.1990","79051453382", groupsList.get(0)));
        studentService.add(new Student("Vitaliyev","Vitaliy","Vitalievich","02.05.1995","9115484545",  groupsList.get(1)));
        studentService.add(new Student("Sergeev","Sergey","Sergeevich","02.12.1980","79114658955",  groupsList.get(2)));

        /*------------------------------------------------------------------------------------*/
        /*----------------------Студенты--------------------------------------------------------*/

        TeacherService teacherService = context.getBean(TeacherService.class);

        for (int i = 0; i < tslist.size(); i++)
            teacherService.add(tslist.get(i));



        /*------------------------------------------------------------------------------------*/
        /*----------------------Проверки------------------------------------------------------*/



        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");


        //Переименовываем Ивана во Владимира
        tslist.get(0).setName("Владимир");
        teacherService.update(tslist.get(0));

        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");

        //удаляем препода с Id=10
        teacherService.delete(new Long(10));

        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");

        //выводим препода с id = 9
        System.out.println(teacherService.findById(new Long(9)).toString());

        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");



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

    }
}