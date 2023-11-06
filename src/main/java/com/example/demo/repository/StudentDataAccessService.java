package com.example.demo.repository;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDb;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class StudentDataAccessService implements StudentDb {

    static final List<Student> students;

    static {
        students = new ArrayList<>();
        Student mary = new Student(

                "John Doe",
                "SMIA",
                12345L
        );
        Student sekou = new Student(

                "John Flomo",
                "SMIA",
                12345L
        );


    }


    @Override
    public List<Student> selectAllStudents() {
        return students;
    }

    @Override
    public Optional<Student> selectStudentById(Integer id) {
        return students.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

    }



}
