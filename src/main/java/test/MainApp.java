package test;

import entities.Group;
import entities.Student;
import entities.Teacher;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.GroupService;
import service.StudentService;
import service.TeacherService;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Ignore
public class MainApp {

    final static Logger logger = Logger.getLogger(MainApp.class);

    public static void main(String[] args) throws ParseException {

        //todo сделать рабочую конфигурацию и хдбллауто поменять на апдейт
        //Удалить ненужные зависимости

        //Сделать добавление всех групп и настроить логирования для методов

        //создать и проверить на соответствие JSP, чтобы были одинаковыми в методах

        //Список групп
        ArrayList<Group> groupsList = new ArrayList();

        groupsList.add(new Group("442"));
        groupsList.add(new Group("522"));
        groupsList.add(new Group("251"));
        groupsList.add(new Group("332"));

        //Список преподавателей
        List<Teacher> tslist= new ArrayList();
        tslist.add(new Teacher("Ivanov","Ivan","Ivanovich",new SimpleDateFormat("dd.MM.yyyy").parse("13.11.1980"),"79534527778"));
        tslist.add(new Teacher("Antonov","Anton","Antonovich",new SimpleDateFormat("dd.MM.yyyy").parse("13.11.1979"),"79534547778"));
        tslist.add(new Teacher("Sidorov","Sidr","Sidorovich",new SimpleDateFormat("dd.MM.yyyy").parse("27.02.1950"),"79531527778"));
        tslist.add(new Teacher("Petrov","Petr","Petrovich",new SimpleDateFormat("dd.MM.yyyy").parse("25.12.1965"),"79534457778"));

        //Список студентов
        List<Student> stdlist= new ArrayList();
        stdlist.add(new Student("Vasiliev","Vasiliy","Vasilievich",new SimpleDateFormat("dd.MM.yyyy").parse("02.04.1990"),"79051453382", groupsList.get(0)));
        stdlist.add(new Student("Vitaliyev","Vitaliy","Vitalievich", new SimpleDateFormat("dd.MM.yyyy").parse("02.05.1995"),"9115484545",  groupsList.get(1)));
        stdlist.add(new Student("Sergeev","Sergey","Sergeevich", new SimpleDateFormat("dd.MM.yyyy").parse("02.12.1980"),"79114658955",  groupsList.get(2)));

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

        String xmlConfigLink = "applicationContext.xml";

        //2 ШТ -1
        ConfigurableApplicationContext context
                = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");

        //context.start();

      /*  File f = new File(xmlConfigLink);
        if(f.exists() && !f.isDirectory()) {
            logger.info("Файл загружен");

            // do something
        } else logger.error("Файла нет");*/

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
        studentService.add(new Student("Vasiliev","Vasiliy","Vasilievich",new SimpleDateFormat("dd.MM.yyyy").parse("02.04.1990"),"79051453382", groupsList.get(0)));
        studentService.add(new Student("Vitaliyev","Vitaliy","Vitalievich",new SimpleDateFormat("dd.MM.yyyy").parse("02.05.1995"),"9115484545",  groupsList.get(1)));
        studentService.add(new Student("Sergeev","Sergey","Sergeevich", new SimpleDateFormat("dd.MM.yyyy").parse("02.12.1980"),"79114658955",  groupsList.get(2)));

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
     //   tslist.get(0).setName("Владимир");
    //    teacherService.update(tslist.get(0));

        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");

        //удаляем препода с Id=10
   //     teacherService.delete(new Long(10));

        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");

        //выводим препода с id = 9
      //  System.out.println(teacherService.findById(new Long(9)).toString());

        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");
        System.out.println("________________________________");



        System.out.println("______________________________");
        System.out.println("_______Студенты_______________");
        System.out.println("______________________________");

   //     List <Student> studentsList = studentService.getStudentsList();

   //     for (Student student: studentsList)
    //        System.out.println(student.toString());
        
        System.out.println("______________________________");
        System.out.println("_______Преподаватели__________");
        System.out.println("______________________________");


   //     List <Teacher> teacherList = teacherService.getTeachersList();

 //       for (Teacher teacher: teacherList)
 //          System.out.println(teacher.toString());

       context.close();

    }
}