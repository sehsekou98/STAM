package com.example.demo.student;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_id_sequence",
            sequenceName = "student_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_sequence"
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


    public Student( String name, String department, long idNumber) {
        this.name = name;
        this.department = department;
        this.idNumber = idNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(department, student.department) && Objects.equals(idNumber, student.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department, idNumber);
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", idNumber=" + idNumber +
                '}';
    }

}
