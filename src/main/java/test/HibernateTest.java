package test;

import entities.Group;
import entities.Student;
import entities.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

//мапу аккаунты

        ArrayList<Teacher> teachersList= new ArrayList();

        ArrayList<Student> studentsList= new ArrayList();

        studentsList.add(new Student("Vasiliev","Vasiliy","Vasilievich","02.04.1990","79051453382", new Group("281")));
        studentsList.add(new Student("Vitaliyev","Vitaliy","Vitalievich","02.05.1995","9115484545", new Group("328")));
        studentsList.add(new Student("Sergeev","Sergey","Sergeevich","02.12.1980","79114658955", new Group("148")));

        ArrayList<Group> groupsList = new ArrayList();

        groupsList.add(new Group("442"));
        groupsList.add(new Group("522"));
        groupsList.add(new Group("251"));
        groupsList.add(new Group("332"));
        groupsList.add(new Group("342"));
        groupsList.add(new Group("242"));
        groupsList.add(new Group("212"));
        groupsList.add(new Group("404"));

        teachersList.add(new Teacher("Ivanov","Ivan","Ivanovich","13.11.1980","79534527778", groupsList));
        teachersList.add(new Teacher("Antonov","Anton","Antonovich","13.11.1979","79534547778", groupsList));
        teachersList.add(new Teacher("Sidorov","Sidr","Sidorovich","27.02.1950","79531527778", groupsList));
        teachersList.add(new Teacher("Petrov","Petr","Petrovich","25.12.1965","79534457778", groupsList));

        System.out.println(teachersList.get(0).toString());

        SessionFactory session = HibernateUtil.getSessionFactory();
        try {
            Session cr = session.getCurrentSession();
            Transaction tr = cr.beginTransaction();


            //Добавляю студентов
          for (int i = 0; i < studentsList.size(); i++) {
                cr.saveOrUpdate(studentsList.get(i));
            }


            //Добавляю тичерсов
            for (int i = 0; i < teachersList.size(); i++) {
                cr.saveOrUpdate(teachersList.get(i));
            }
            //добавляю группы
            for (int i = 0; i < groupsList.size(); i++) {
                cr.saveOrUpdate(groupsList.get(i));
            }


            //Создаем критерию
            CriteriaBuilder builder = cr.getCriteriaBuilder();
            CriteriaQuery<Student> query = builder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);
//from и тд
            query.select(root);//тут

//Селект с 2х таблиц

//Найти в классе поле равное 3(id)
            query.where(builder.equal(root.get("fam"), "Sergeev"));

            Query q=cr.createQuery(query);
            List<Student> list = q.getResultList();

            System.out.println("_______________________");
            System.out.println("_______________________");

            if (list.size()==0) System.out.println("Ничего не найдено");
            else
                for (int i = 0; i < list.size(); i++)
                    System.out.println(list.get(i).toString() + "\n");



            cr.flush();

            tr.commit();

            System.out.println("It's all over");

        }
        catch(Exception ex)
        {
            System.out.println("Ошибка " + ex);
        }
        finally {
            //закрываем сессию
            session.close();
        }

    }

}
