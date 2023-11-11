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
        var sql = """
                SELECT count(id)
                FROM student
                WHERE idNumber = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idNumber);
        return count != null && count > 0;
    }

    @Override
    public void insertStudent(Student student) {
        var sql = """
                INSERT INTO student(name, email, department, idnumber)
                VALUES (?, ?, ?, ?)
                """;
        int result = jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getIdNumber()
        );
        System.out.println("jdbcTemplate.update = " + result);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        var sql = """
                SELECT count(id)
                FROM student
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);

        return count != null && count > 0;
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        var sql = """
                SELECT count(id)
                FROM student
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,id);
        return count != null && count > 0;
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        var sql = """
                DELETE
                FROM student
                WHERE id = ?
                """;
        int result = jdbcTemplate.update(sql, studentId);
        System.out.println("deleteStudentById result = " + result);

    }

    @Override
    public void updateStudent(Student update) {
    if (update.getName() != null) {
        String sql = "UPDATE student SET name = ? WHERE id = ?";
        int result = jdbcTemplate.update(
                sql,
                update.getName(),
                update.getId()
        );
        System.out.println("update student name result = " + result);
    }
        if (update.getEmail() != null) {
            String sql = "UPDATE student SET email = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getEmail(),
                    update.getId()
            );
            System.out.println("update student email result = " + result);
        }
        if (update.getDepartment() != null) {
            String sql = "UPDATE student SET department = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    sql,
                    update.getDepartment(),
                    update.getId()
            );
            System.out.println("update student department result = " + result);
        }
    }
}
