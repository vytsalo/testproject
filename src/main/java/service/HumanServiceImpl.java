package service;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;


@Service
public class HumanServiceImpl implements HumanService {

    @Autowired
    private EntitiesService<Group> groupService;

    @Autowired
    private EntitiesService<Student> studentService;

    @Autowired
    private EntitiesService<Teacher> teacherService;



    //все вкладки заполняются
    //лист сотрудники
    public void exportToExcel() throws IOException {
//имя файла + дата
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Студенты");

        //List<Student> studentList = studentService.getList();

        int rownum = 0;
        //todo заголовки

        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        //todo метод для заголовков, передаем массив названий и применяем стили
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Порядковый номер");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ФИО"); //все вместе


        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Дата рождения");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Номер телефона");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Группа");

        //дата //номер телефона//группа
        //cell.setCellStyle(style);





/*

        for (Student student: studentList){



        }*/




/*

        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }
*/


        try (FileOutputStream outputStream = new
                FileOutputStream("Books.xlsx")) {
            workbook.write(outputStream);
        }

        workbook.close();


        //file
       // return workbook;
    }













    public void initDB() throws ParseException {

        List<String> groupTitles = new ArrayList<>(List.of("111", "211", "147", "148", "217", "227"));

        List<Group> groupList = new ArrayList<>();

        groupTitles.forEach(title -> {
            Group group = new Group(title);
            groupService.add(group);
            groupList.add(group);
        });


        Integer studentsCount = 10;

        //todo женский пол добавлять вручную
        List<String> names = new ArrayList<>(List.of("Иван", "Сергей", "Виталий", "Антон", "Дмитрий", "Александр"));
        List<String> fams = new ArrayList<>(List.of("Харламов", "Амелин", "Васильев", "Абросимов", "Булаев", "Тарасов"));
        List<String> patronyms = new ArrayList<>(List.of("Сергеевич", "Викторович", "Артемович", "Денисович", "Алексеевич", "Александрович"));
/*

        Date date = new Date(); // Or where ever you get it from
        Date daysAgo = new DateTime(date).minusDays(300).toDate();
*/


        for (int i = 0; i < studentsCount; i++) {

            Student student = new Student(
                    getRandomString(fams),
                    getRandomString(names),
                    getRandomString(patronyms),
                    getRandomDate(),
                    getRandomPhoneNumber(),
                    null);
            //student.setId((long) (1000 + i));
            studentService.add(student);
            student.setGroup(groupList.get(new Random().nextInt(groupList.size()-1)));
            studentService.update(student);

        }

        /*IntStream.rangeClosed(1, studentsCount)
                .forEach(


                );*/






        /*Group group1 = new Group("147");
        groupService.add(group1);

        Group group2 = new Group("148");
        groupService.add(group2);

        ArrayList<Group> groupsList = new ArrayList(Arrays.asList(group1, group2));

        Student student1 = new Student("Булаев","Александр", "Николаевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("12.08.1995"), "89061453385", null);
        studentService.add(student1);
        student1.setGroup(group1);
        studentService.update(student1);


        Student student2 = new Student("Вечтомов","Дмитрий", "Викторович",
                new SimpleDateFormat("dd.mm.YYYY").parse("22.03.1995"), "89061453385", null);
        studentService.add(student2);
        student2.setGroup(group1);
        studentService.update(student2);


        Student student3 = new Student("Васильев","Виталий", "Сергеевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1994"), "89056984585", null);
        studentService.add(student3);
        student3.setGroup(group1);
        studentService.update(student3);


        Student student4 = new Student("Харламов","Александр", "Алексеевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("22.08.1995"), "89056984585", null);
        studentService.add(student4);
        student4.setGroup(group1);
        studentService.update(student4);



        Student student5 = new Student("Тарасов","Максим", "Алексеевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("23.04.1997"), "89056984585", null);
        studentService.add(student5);
        student5.setGroup(group1);
        studentService.update(student5);



        Teacher teacher1 = new Teacher("Блинков","Юрий", "Анатольевич",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1970"), "89056984585", null);
        teacherService.add(teacher1);
        teacher1.setGroups(groupsList);
        teacherService.update(teacher1);

        Teacher teacher2 = new Teacher("Дудов","Сергей", "Иванович",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1950"), "89056984585", null);
        teacherService.add(teacher2);
        teacher2.setGroups(groupsList);
        teacherService.update(teacher2);


        Teacher teacher3 = new Teacher("Бессонов","Леонид", "Валентинович",
                new SimpleDateFormat("dd.mm.YYYY").parse("08.11.1965"), "89056984585", null);
        teacherService.add(teacher3);
        teacher3.setGroups(groupsList);
        teacherService.update(teacher3);*/


    }


    private String getRandomString(List<String> list){
        return list.get(new Random().nextInt(list.size()-1 - 0) + 0);
    }

    private String getRandomPhoneNumber(){
        Random r = new Random();
        //8(963) 145-8916
        //int i1 = 8; // returns random number between 0 and 7
        int i3 = r.nextInt(89) + 10;
        int i4 = r.nextInt(899) + 100; // returns random number between 0 and 741
        int i5 = r.nextInt(8999) + 1000; // returns random number between 0 and 9999

        String phoneNumber = String.format("8(9%d) %d-%d", i3, i4, i5);
        return phoneNumber;
    }

    private Date getRandomDate() throws ParseException {
       return new Date(ThreadLocalRandom.current()
                .nextLong(new SimpleDateFormat("dd.mm.YYYY").parse("01.01.1970").getTime(),
                        new GregorianCalendar(2002, Calendar.JANUARY, 1).getTime().getTime()));
    }

}
