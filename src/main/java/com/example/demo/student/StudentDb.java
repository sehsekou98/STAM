package com.example.demo.student;

import java.util.List;
import java.util.Optional;

public interface StudentDb {
     List<Student> selectAllStudents();
    Optional<Student> selectStudentById(Integer id);

    boolean existsPersonWithIdNumber(long idNumber);

    void insertStudent(Student student);

    boolean existsPersonWithEmail(String email);

    boolean existsPersonWithId(Integer id);

    void deleteStudentById(Integer studentId);

    void updateStudent(Student update);
}
