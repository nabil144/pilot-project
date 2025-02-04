package com.example.springbootapp;

import com.example.springbootapp.dao.StudentDAO;
import com.example.springbootapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringbootappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("deleted count "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 11;
		System.out.println("deleting student with id "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve by id
		int studentId = 13;
		System.out.println("getting student with id="+studentId);
		Student myStudent = studentDAO.findById(studentId);
		//change firstname
		System.out.println("updating student");
		myStudent.setFirstName("scooby");
		//update student
		studentDAO.update(myStudent);
		//display
		System.out.println(myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//get list of students
		List<Student> theStudents = studentDAO.findByLastName("Duck");
		//display list
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get list of students
		List<Student> theStudents = studentDAO.findAll();
		//display list
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
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
