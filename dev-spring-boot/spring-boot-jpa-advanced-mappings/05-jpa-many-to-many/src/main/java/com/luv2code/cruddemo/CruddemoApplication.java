package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){


		return runner ->{
			//createCourseAndStudent(appDAO);

			//findCourseAndStudents(appDAO);

			//findStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);





		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId =3;
		System.out.println("Deleting student id :"+theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId=3;
		Student tempstudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create more Courses
		Course tempCourse1= new Course("Rubik's Cube - How to Speed Cube");
		Course tempCourse2= new Course("Atari 2600 - Game Development");

		// add courses to student
		tempstudent.addCourse(tempCourse1);
		tempstudent.addCourse(tempCourse2);

		System.out.println("Saving student:"+tempstudent);
		System.out.println("asssociated courses:"+tempstudent.getCourses());

		appDAO.update(tempstudent);

	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int  theId=3;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded courses:" +tempStudent);
		System.out.println("Student:" +tempStudent.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int  theId=2;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded courses:" +tempCourse);
		System.out.println("Student:" +tempCourse.getStudents());
		System.out.println("Done!");


	}

	private void createCourseAndStudent(AppDAO appDAO) {

		// create a course
		Course tempCourse =new Course("pacman _ How To Score one Million Points");

		// create a student
		Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","Mary@luv2code.com");


		// add student to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and the associated students
		System.out.println("Saving the course :"+tempCourse);
		System.out.println("associated student :" +tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Done");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId=1;

		System.out.println("Deleting course id :"+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		int theId=1;

		//get the course and reviews
		Course tempcourse = appDAO.findCourseAndReviewsByCourseId(theId);

 		//print the course
		System.out.println(tempcourse);
		//print the reviews
		System.out.println(tempcourse.getReviews());
		System.out.println("Done");

	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create course
		Course tempCourse = new Course("Howe to Score one Million points");

		//add some reviews
		tempCourse.addReview(new Review("Great Course"));
		tempCourse.addReview(new Review("cool Course"));
		tempCourse.addReview(new Review("Bad Course"));

		//save the course..and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

	}

	private void deleteCourse(AppDAO appDAO) {

		int theId=4;

		System.out.println("Deleting course id :"+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");

	}

	private void updateCourse(AppDAO appDAO) {

		int theId=1;
		//find the Course
		System.out.println("Find Course id :"+theId);
		Course tempCourse =appDAO.findCourseById(theId);

		//update the course
		System.out.println("Update course id :"+theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);
		System.out.println("Done!");


	}

	private void updateInstructor(AppDAO appDAO) {

			int theId =1;

			//find the instructor
			System.out.println("Finding instructor id :"+theId);
			Instructor tempInstructor =appDAO.findInstructorById(theId);

			// update the Instructor
			System.out.println("Update Instructor id :"+theId);
			tempInstructor.setLastName("TESTER");

			appDAO.update(tempInstructor);

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId=1;
		//find the instructor
		System.out.println("Finding instructor id :"+theId);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor"+tempInstructor);
		System.out.println("the associated courses :"+tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId=1;
		//find instructor
		System.out.println("Finding instructor id :"+theId);
		Instructor temoInstructor =appDAO.findInstructorById(theId);
		System.out.println("tempInstructor :"+temoInstructor);

		//find courses for instructor
		System.out.println("Finding courses for instructor id :"+theId);
		List<Course> courses =appDAO.findCoursesByInstructorId(theId);

		// associate the object
		temoInstructor.setCourses(courses);

		System.out.println("the associated courses :"+temoInstructor.getCourses());

		System.out.println("Done");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId=1;
		System.out.println("Finding instructor id :"+theId);
		Instructor tempInstructor =appDAO.findInstructorById(theId);
		System.out.println("tempInstructor :"+tempInstructor);
		System.out.println("the associated courses :"+tempInstructor.getCourses());
		System.out.println("Done");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the instructor
		Instructor tempInstructor;
		tempInstructor = new Instructor("Susan","public","Susan@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.youtube.com",
						"Vide game!!!");
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses("")
		Course tempCourse1 =new Course("Air Guitar - the ultimate Guide");
		Course tempCourse2 =new Course("the pinball Masterclass");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		//
 		// NOTE:this will Also save the courses
		//because of CascadeType.persist
		//
		System.out.println("Saving instructor :"+tempInstructor);
		System.out.println("the Courses:"+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=3;
		System.out.println("Deleting instructor detail id:"+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		//get the instructor detail object
		int theId =2;
		InstructorDetail tempInstructorDetail =appDAO.findInstructorDetailById(theId);

		//print the instructor detail
		System.out.println("tempInstructorDetail:"+tempInstructorDetail);

		//print the associate instructor
		System.out.println("the associate instructor:"+ tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting instructor id:"+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");

	}

	private void findInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id:"+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor:"+ tempInstructor);
		System.out.println("the associate instructor only :"+ tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		//create the instructor
		Instructor tempInstructor;
		tempInstructor = new Instructor("Rami","Hinnawi","Rami@luv2code.com");

        // create the instructor detail
		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.luve2code.com/youtube",
						"Luv 2 code!!!");
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);


/*
		//create the instructor
		Instructor tempInstructor;
        tempInstructor = new Instructor("chad","Darby","darby@luv2code.com");

        // create the instructor detail
		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.luve2code.com/youtube",
						"Guitar!!!");
*/

		//save the instructor
 		//
 		//NOTE:this will also save the details object
 		//because of CascadeType.All
		//
		System.out.println("Saving instructor :"+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done");

	}

}
