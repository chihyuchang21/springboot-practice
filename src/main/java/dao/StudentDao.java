package dao;

import model.Student;

import java.util.List;

public interface StudentDao {

    void insert(Student student);

    void batchInsert(List<Student> studentList);

    void deleteById(Integer studentId);

    Student getById(Integer studentId);


}
