package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args)
	{
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository) {
		return args -> {
			Student mary = new Student(

					"John Flomo",
					"SMIA",
					"john@gmail.com",
					12345L
			);
			Student sekou = new Student(

					"Mary John",
					"SMIA",
					"mary@gmail.com",
					12345L
			);
			List<Student> students = List.of(mary, sekou);
			//studentRepository.saveAll(students);
		};
	}

}
