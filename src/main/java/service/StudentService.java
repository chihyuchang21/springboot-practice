package com.example.demo.service;


import model.Student;

import java.util.List;

public interface StudentService {

    //define these 4 methods
    void insert(Student student);

    void batchInsert(List<Student> studentList);

    void deleteById(Integer studentId);

    Student getById(Integer studentId);
}
