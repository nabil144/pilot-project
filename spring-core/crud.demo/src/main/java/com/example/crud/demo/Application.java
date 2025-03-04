package com.example.crud.demo;

import com.example.crud.demo.dao.StudentDAO;
import com.example.crud.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner-> {

			//createStudent(studentDAO);
			createMultiplesStudents(studentDAO);
			//readstudent(studentDAO);
			//queryForStudent(studentDAO);
			//queryForStudentByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deletStudent(studentDAO);
			//deletallstudents(studentDAO);



		};
	}

	private void deletallstudents(StudentDAO studentDAO) {
		System.out.println("delet all students");
		int numRowsDeleted = studentDAO.deletAll();
		System.out.println("the num of deleted row:"+numRowsDeleted);

	}

	private void deletStudent(StudentDAO studentDAO) {


		int IDdeleted=2;
		//Student theStudent = studentDAO.findById(IDdeleted);

		studentDAO.delete(IDdeleted);


 		//display the
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on the id :primary key
		int studentId =1;
		System.out.println("getting student with id :"+studentId);
		Student myStudent =studentDAO.findById(studentId);

 		//change first name to "Scooby"
		myStudent.setFirstName("Scooby");

		//update student
		studentDAO.update(myStudent);

 		//display the update student
		System.out.println("updating student :"+myStudent);


	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {

		//get a list of student
		List<Student> theStudents = studentDAO.findbyLastname("Duck");
		//display list of student
		for (Student tempStudent :theStudents){
			System.out.println(tempStudent);
		}
	}
	private void queryForStudent(StudentDAO studentDAO) {

		//get a list of student
		List<Student> thestudents= studentDAO.findALL();

		//display list of student
		for (Student tempStudent :thestudents){
			System.out.println(tempStudent);
		}
	}

	private void readstudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("creating new student object ...");
		Student s1 = new Student("daffy","duck","daffy@cdchbdch");

 		// save the student
		System.out.println("saiving the student :");
		studentDAO.save(s1);

 		//display id of the saved student
		int theid = s1.getId();
		System.out.println("saved student  with id :"+theid);


 		//retrieve student based on the id :primary key
		System.out.println("retrieve student with id :"+theid);
		Student mystudent =studentDAO.findById(theid);

		//display student
		System.out.println("found the student :"+mystudent);
	}

	private void createMultiplesStudents(StudentDAO studentDAO) {
		//create multiples student

		System.out.println("Creating 3 student object...");
		Student student1 = new Student("SAMI","HINNAWI","bbbl@gmail.com");
		Student student2 = new Student("RAMI","hinnawi","Ddffde@gmail.com");
		Student student3 = new Student("JAD","hinnawi","Ddfsoe@gmail.com");
		//save a student
		System.out.println("save the students");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);


	}

	private void createStudent(StudentDAO studentDAO) {

		//create the student object
		System.out.println("Creating a new student object...");
		Student student1 = new Student("Paul","Doe","Paul@gmail.com");

 		//save the student object
		System.out.println("Saving the student...");
		studentDAO.save(student1);

 		//display id of the saved student
		System.out.println("save student. Genrated id : "+student1.getId());



	}

}
