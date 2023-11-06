package com.example.demo.student;

import java.util.List;
import java.util.Optional;

public interface StudentDb {
     List<Student> selectAllStudents();
    Optional<Student> selectStudentById(Integer id);

}
