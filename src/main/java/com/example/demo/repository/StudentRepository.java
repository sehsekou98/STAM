package com.example.demo.repository;

import com.example.demo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;




public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsStudentById(Integer id);

    boolean existsStudentByEmail(String email);

    boolean existsStudentByIdNumber(long idNumber);
}
