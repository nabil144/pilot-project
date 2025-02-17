package com.example.crud_mappings_demo;

import com.example.crud_mappings_demo.dao.AppDAO;
import com.example.crud_mappings_demo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudMappingsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudMappingsDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);

			//createCourseAndReview(appDAO);
			//retrieveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);

			createCourseAndStudents(appDAO);
		};
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		//create course
		Course tempCourse = new Course("pacman");
		//create students
		Student tempStudent1 = new Student("john","doe","john@luv2code.com");
		Student tempStudent2 = new Student("mary","public","mary@luv2code.com");
		//add students to course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		//save course and students
		System.out.println("saving course"+tempCourse);
		System.out.println("with students"+tempCourse.getStudents());
		appDAO.save(tempCourse);
		System.out.println("done");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 3;
		System.out.println("deleting course with id "+ theId);
		appDAO.deleteCourseById(theId);
		System.out.println("done");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 3;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
	}

	private void createCourseAndReview(AppDAO appDAO) {
		Course tempCourse = new Course("pacman");
		tempCourse.addReview(new Review("great course"));
		tempCourse.addReview(new Review("good"));
		tempCourse.addReview(new Review("really bad course"));
		appDAO.save(tempCourse);
		System.out.println("saved course "+tempCourse);
		System.out.println(tempCourse.getReviews());
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 1;
		System.out.println("deleting course with id "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("done");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 1;
		System.out.println("finding course id "+theId);
		Course tempCourse = appDAO.findCourseById(theId);
		System.out.println("updating course");
		tempCourse.setTitle("new course name");
		appDAO.update(tempCourse);
		System.out.println("done");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("finding instructor id "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("updating");
		tempInstructor.setLastName("tester");
		appDAO.update(tempInstructor);
		System.out.println("done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 5;
		System.out.println("finding instructor id "+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("temp "+tempInstructor);
		System.out.println("courses "+tempInstructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 5;
		System.out.println("finding instructor id "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("temp "+tempInstructor);
		//finding courses
		System.out.println("finding courses for id"+theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);
		System.out.println("associated courses "+tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 5;
		System.out.println("finding instructor id "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("temp "+tempInstructor);
		System.out.println("courses "+tempInstructor.getCourses());
		System.out.println("done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//		// create the instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com",
						"video games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create courses
		Course tempCourse1 = new Course("air guitar guide");
		Course tempCourse2 = new Course("pinball class");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		System.out.println("saving instructor "+tempInstructor);
		System.out.println("saving courses "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("deleting instructor with id "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("deleted");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get instructor detail obj
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		//print detail
		System.out.println(tempInstructorDetail);
		//print associated instructor
		System.out.println(tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 5;
		System.out.println("deleting instructor with id "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("deleted");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("finding instructor with id "+ theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("temp instructor"+tempInstructor);
		System.out.println("the associate instructor detail only "+tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");


//		// create the instructor
//		Instructor tempInstructor =
//				new Instructor("Madhu", "Patel", "madhu@luv2code.com");
//
//		// create the instructor detail
//		InstructorDetail tempInstructorDetail =
//				new InstructorDetail(
//						"http://www.luv2code.com/youtube",
//						"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}


}
