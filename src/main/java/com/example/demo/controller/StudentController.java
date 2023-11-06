package com.example.demo.controller;

import com.example.demo.student.Student;
import com.example.demo.service.StudentService;
import com.example.demo.student.StudentRegistrationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{studentId}")
    public Student getStudent(
        @PathVariable("studentId") Integer studentId ) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public void registerStudent(
            @RequestBody StudentRegistrationRequest request) {
        studentService.addStudent(request);
    }



}
