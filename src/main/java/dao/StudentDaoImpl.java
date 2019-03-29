package dao;

import entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao{

    @Override
    public List<Student> getStudentsList(){
        return new ArrayList<Student>();
    }


}
