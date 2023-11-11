package com.example.demo.service;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDb;
import com.example.demo.student.StudentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("jdbc")
public class JBDCStudentService implements StudentDb {
    private final JdbcTemplate jdbcTemplate;
    private final StudentRowMapper studentRowMapper;


    public JBDCStudentService(JdbcTemplate jdbcTemplate, StudentRowMapper studentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRowMapper = studentRowMapper;

    }

    @Override
    public List selectAllStudents() {
     var sql = """
             SELECT id, name, email, department, idnumber
             FROM student
             """;
     return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public Optional selectStudentById(Integer id) {
        var sql = """
                SELECT id, name, email, department, idnumber
                FROM student
                WHERE id = ? 
                """;
        return jdbcTemplate.query(sql, studentRowMapper, id)
                .stream().findFirst();
    }

    @Override
    public boolean existsPersonWithIdNumber(long idNumber) {
        return false;
    }

    @Override
    public void insertStudent(Student student) {

    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return false;
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return false;
    }

    @Override
    public void deleteStudentById(Integer studentId) {

    }

    @Override
    public void updateStudent(Student update) {

    }
}
