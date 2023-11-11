package com.example.demo.service;

import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.RequestValidationException;
import com.example.demo.exception.ResourceNotFound;
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
                () -> new ResourceNotFound("Student with id [%s] not fount"
                        .formatted(id))
        );
    }

    //check if email exist


    public void addStudent (StudentRegistrationRequest studentRegistrationRequest) {
        // check if id exist.
        Long idNumber = Long.valueOf(studentRegistrationRequest.idNumber());
        if (studentDb.existsPersonWithIdNumber(idNumber)) {
            throw new DuplicateResourceException("Id Number's taken"
            );

        }

        String email = studentRegistrationRequest.email();
        if (studentDb.existsPersonWithEmail(email)) {
            throw new DuplicateResourceException("Email already taken."
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
            throw new ResourceNotFound(
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
            changes = true;
    }

            if(updateRequest.email() != null && !updateRequest.email().equals(student.getEmail()))
             if (studentDb.existsPersonWithEmail(updateRequest.email())){
                    throw new DuplicateResourceException(
                            "email already taken."
                    );
                }
            student.setEmail(updateRequest.email());
                   changes = true;


            if(updateRequest.department() != null && !updateRequest.department().equals(student.getDepartment())) {
                student.setDepartment(updateRequest.department());
                changes = true;
            }

            if(updateRequest.idNumber() != null && !updateRequest.idNumber().equals(student.getIdNumber()))
              if (studentDb.existsPersonWithIdNumber(updateRequest.idNumber())) {
                  throw new DuplicateResourceException(
                          "id number is taken"
                  );

              }
            student.setIdNumber(Math.toIntExact(updateRequest.idNumber()));
               changes = true;

            if (!changes) {
                throw new RequestValidationException("no data change found");
            }
    }



}

