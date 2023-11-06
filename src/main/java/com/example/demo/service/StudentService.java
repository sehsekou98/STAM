package com.example.demo.service;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDb;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentDb studentDb;


    public StudentService(@Qualifier("jpa") StudentDb studentDb) {
        this.studentDb = studentDb;
    }

    public List<Student> getAllStudents() {
        return studentDb.selectAllStudents();
    }

    public Student getStudent(Integer id) {
        return studentDb.selectStudentById(id).orElseThrow(
                () -> new RuntimeException("Student with id [%s] not fount"
                        .formatted(id))
        );
    }
}

