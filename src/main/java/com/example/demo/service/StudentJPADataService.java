package com.example.demo.service;


import com.example.demo.repository.StudentRepository;
import com.example.demo.student.Student;
import com.example.demo.student.StudentDb;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class StudentJPADataService implements StudentDb {

    private final StudentRepository studentRepository;

    public StudentJPADataService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> selectAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> selectStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean existsPersonWithIdNumber(long idNumber) {
        return studentRepository.existsStudentByIdNumber(idNumber);
    }

    @Override
    public void insertStudent(Student student) {
        studentRepository.save(student);

    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return studentRepository.existsStudentByEmail(email);
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return studentRepository.existsStudentById(id);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }
}
