package com.example.demo.service;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDb;
import com.example.demo.student.StudentRegistrationRequest;
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

    public void addStudent (StudentRegistrationRequest studentRegistrationRequest){
        // check is email exist.
        Long idNumber = Long.valueOf(studentRegistrationRequest.idNumber());
        if (studentDb.existsPersonWithIdNumber(idNumber)) {
            throw new RuntimeException("Id Numbers taken"
            );
        }

        // add student

        Student student = new Student(
                studentRegistrationRequest.name(),
                studentRegistrationRequest.department(),
                studentRegistrationRequest.email(),
                studentRegistrationRequest.idNumber()
        );
        studentDb.insertStudent(student);

    }

}

