package com.example.demo.student;

public record StudentUpdateRequest(
        String name,
        String department,
        String email,
        Long idNumber
) {
}
