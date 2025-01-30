package com.example.springbootapp;

import com.example.springbootapp.dao.StudentDAO;
import com.example.springbootapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {

		//create student
		System.out.println("creating new student object...");
		Student tempStudent = new Student("paul","doe","paul@gmail.com");

		//save student
		System.out.println("saving student...");
		studentDAO.save(tempStudent);

		//display id
		System.out.println("saved student. generated id: "+tempStudent.getId());


	}

}
