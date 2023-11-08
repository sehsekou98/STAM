package com.example.demo.service;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDb;
import com.example.demo.student.StudentRegistrationRequest;
import com.example.demo.student.StudentUpdateRequest;
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

    //check if email exist


    public void addStudent (StudentRegistrationRequest studentRegistrationRequest) {
        // check if id exist.
        Long idNumber = Long.valueOf(studentRegistrationRequest.idNumber());
        if (studentDb.existsPersonWithIdNumber(idNumber)) {
            throw new RuntimeException("Id Number's taken"
            );

        }

        String email = studentRegistrationRequest.email();
        if (studentDb.existsPersonWithEmail(email)) {
            throw new RuntimeException("Email already taken."
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

        public void deleteStudentById(Integer studentId) {
        if (!studentDb.existsPersonWithId(studentId)) {
            throw new RuntimeException(
                    "Student with id [%s] not found".formatted(studentId)
            );
        }
            studentDb.deleteStudentById(studentId);
        }

        public void updateStudent(Integer studentId, StudentUpdateRequest updateRequest) {
        Student student = getStudent(studentId);

        boolean changes = false;

        if(updateRequest.name() != null && !updateRequest.name().equals(student.getName())) {
            student.setName(updateRequest.name());
            studentDb.insertStudent(student);
            changes = true;
    }

            if(updateRequest.email() != null && !updateRequest.email().equals(student.getEmail())) {
                student.setEmail(updateRequest.email());
                studentDb.insertStudent(student);
                changes = true;
            }

            if(updateRequest.department() != null && !updateRequest.department().equals(student.getDepartment())) {
                student.setDepartment(updateRequest.department());
                studentDb.insertStudent(student);
                changes = true;
            }

            if(updateRequest.idNumber() != null && !updateRequest.idNumber().equals(student.getIdNumber())) {
                student.setIdNumber(Math.toIntExact(updateRequest.idNumber()));
                studentDb.insertStudent(student);
                changes = true;
            }

    }

}

