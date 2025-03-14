package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
			createInstructor(appDAO);

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

			deleteCourse(appDAO);


		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=1;

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
