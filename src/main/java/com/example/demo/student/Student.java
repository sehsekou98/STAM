package com.example.demo.student;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(
       name = "student",
        uniqueConstraints = {
               @UniqueConstraint(
                       name = "student_idNumber_unique",
                       columnNames = "idnumber"

               ),

@UniqueConstraint(
        name = "student_email_unique",
        columnNames = "email"
)
        }

)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_id_seq",
            sequenceName = "student_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_seq"
    )
   private Integer id;

    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false
    )
    private String department;

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(
            nullable = false
    )
    private String email;

    @Column(
            nullable = false
    )
    private long idNumber;

    public Student() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }


    public Student( String name, String department, String email, long idNumber) {
        this.name = name;
        this.department = department;
        this.idNumber = idNumber;
        this.email = email;
    }


    public void add(Student student) {
    }
}
