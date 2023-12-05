package com.example.demo.service;

import model.Student;
import dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServicelmpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    //在implement中直接call Dao的四種方法即可
//    @Override
//    public void insert(Student student){
//        return StudentDao.insert(student);
//    }
//
//    @Override
//    public void batchInsert(List<Student> studentList){
//        StudentDao.batchInsert(studentList);
//    }
//
//    @Override
//    public Student getbyId(Integer studentId){
//        return studentDao.getbyId(studentId);
//    }

    @Override
    public Student getbyId(Integer studentId){
        return studentDao.
                getbyId(studentId);
    }

}
