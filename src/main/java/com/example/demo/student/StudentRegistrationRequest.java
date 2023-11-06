package com.example.demo.student;

public record StudentRegistrationRequest(
        String name,
        String department,
        String email,
        Integer idNumber

) {
}
