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
                "doe@gmail.com",
                12345L
        );
        Student john = new Student(

                "John Flomo",
                "SMIA",
                "john@gmail.com",
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

    @Override
    public boolean existsPersonWithIdNumber(long idNumber) {
        return students.stream().anyMatch(
                s -> s.getIdNumber()
                        .equals(idNumber)
        );
    }

    public void insertStudent(Student student) {
        student.add(student);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return students.stream().anyMatch(
                s-> s.getEmail().equals(email)
        );
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return students.stream()
                .anyMatch( s->s.getId().equals(id)

        );
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        students.stream()
                .filter(s->s.getId()
                        .equals(studentId))
                .findFirst()
                .ifPresent(students::remove);
    }


}
