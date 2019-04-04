package service;

import dao.StudentDao;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//почему именно так
//из сервисов вызывается ДАО

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao st_dao;

    @Transactional
    @Override
    public void add(Student student){
        st_dao.add(student);
    }

    //зачем дополнительно прописывать ридонли, если оно и так ридонли?
    @Transactional(readOnly = true)
    @Override
    public List<Student> getStudentslist(){
        return st_dao.getStudentsList();
    }

}


/*
@Service
public class PersonServiceImp implements PersonService {

   @Autowired
   private PersonDao userDao;

   @Transactional
   @Override
   public void add(Person person) {
      userDao.add(person);
   }

   @Transactional(readOnly = true)
   @Override
   public List<Person> listPersons() {
      return userDao.listPersons();
   }
*/
