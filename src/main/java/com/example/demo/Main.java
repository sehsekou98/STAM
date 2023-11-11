package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Main {

	public static void main(String[] args)
	{
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository) {
		return args -> {
			var faker = new Faker();
			Random random = new Random();
			Student student = new Student(
					faker.name().fullName(),
					faker.internet().safeEmailAddress(),
					faker.commerce().department(),
					random.nextLong()
			);


			studentRepository.save(student);
		};
	}

}
