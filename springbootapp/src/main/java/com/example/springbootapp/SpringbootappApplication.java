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
			//createMultipleStudents(studentDAO);

			readStudent(studentDAO);
		};
	}

	private void readStudent(StudentDAO studentDAO) {
		//create student object
		System.out.println("creating new student object");
		Student tempStudent = new Student("mickey","mouse","clubhouse@gmail.com");

		//save student
		System.out.println("saving student obj");
		studentDAO.save(tempStudent);

		//display id of saved student
		int theId = tempStudent.getId();
		System.out.println("saved student, generated id: "+theId);

		//retrieve based on id
		System.out.println("retrieving student with id: "+theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("found the student: "+myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO){
		System.out.println("creating 3 student objects...");
		Student tempStudent1 = new Student("john","doe","john@gmail.com");
		Student tempStudent2 = new Student("mary","public","mary@gmail.com");
		Student tempStudent3 = new Student("bonita","applebum","paul@gmail.com");

		System.out.println("saving 3 students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
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
