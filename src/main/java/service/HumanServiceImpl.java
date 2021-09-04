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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


@Service
public class HumanServiceImpl implements HumanService {

    @Autowired
    private EntitiesService<Group> groupService;

    @Autowired
    private EntitiesService<Student> studentService;

    @Autowired
    private EntitiesService<Teacher> teacherService;


    List<String> names = importLinesFromFile("male-names.txt");
    List<String> female_names = importLinesFromFile("female-names.txt");
    List<String> fams = importLinesFromFile("fams.txt");
    List<String> patronyms = importLinesFromFile("patronyms.txt");


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













    public void initDB() {

        List<Group> groupList =
        Stream.of("111", "211", "147", "148", "217", "227")
                .map(title -> {
                    Group group = new Group(title);
                    groupService.add(group);
                    return group;
                }).collect(toList());


            int humansCount = 25;

            for (int i = 0; i < humansCount; i++) {

                studentService.add(generateStudent(groupList));//todo группу не передавать, сделать статичным?

                teacherService.add(generateTeacher(groupList));
            }

    }

    private String getRandomString(List<String> list){
        return list.get(new Random().nextInt(list.size()-1));
    }

    private String getRandomPhoneNumber(){
        Random random = new Random();        //8(963) 145-8916
        int i1 = random.nextInt(89) + 10;
        int i2 = random.nextInt(899) + 100;
        int i3 = random.nextInt(8999) + 1000;
        return String.format("8(9%d) %d-%d", i1, i2, i3);
    }

    private Date getRandomDate() throws ParseException {
       return new Date(ThreadLocalRandom.current()
                .nextLong(new SimpleDateFormat("dd.mm.YYYY").parse("01.01.1970").getTime(),
                        new GregorianCalendar(2002, Calendar.JANUARY, 1).getTime().getTime()));
    }

    private List<String> importLinesFromFile(String filename) {

        try {
            File resource = new ClassPathResource(filename).getFile();
            return Files.readAllLines(resource.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //generateHuman+type Teacher or Student
    private Student generateStudent(List<Group> groupList){
        //передаем t или s

        // Object o = new Object();
        //   (Student) o

        /*try {
            Object k = Class.forName(humanClass.getName()).getConstructor(String.class).newInstance();

            Class.forName(humanClass.getName()).cast(k);

        } catch (Exception e){

            e.printStackTrace();
        }
*/

        Student student = null;

        //0 - мужчина, 1 - женщина
        int gender = new Random().nextInt(2);

        try {

            if (gender==0) {
                student =  new Student(
                        getRandomString(fams),
                        getRandomString(names),
                        getRandomString(patronyms),
                        getRandomDate(),
                        getRandomPhoneNumber(),
                        groupList.get(new Random().nextInt(groupList.size() - 1)));
            } else {
                student =  new Student(
                        getRandomString(fams) + "а",
                        getRandomString(female_names),
                        getRandomString(patronyms).replace("вич","вна"),
                        getRandomDate(),
                        getRandomPhoneNumber(),
                        null);
            }

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

      return student;
    }


    private Teacher generateTeacher(List<Group> groupList){

        Teacher teacher = null;

        //0 - мужчина, 1 - женщина
        int gender = new Random().nextInt(2);

        try {
            if (gender==0) {
                teacher =  new Teacher(
                        getRandomString(fams),
                        getRandomString(names),
                        getRandomString(patronyms),
                        getRandomDate(),
                        getRandomPhoneNumber(),
                        groupList);
            } else {
                teacher =  new Teacher(
                        getRandomString(fams) + "а",
                        getRandomString(female_names),
                        getRandomString(patronyms).replace("вич","вна"),
                        getRandomDate(),
                        getRandomPhoneNumber(),
                        groupList);
            }

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        return teacher;
    }

}
