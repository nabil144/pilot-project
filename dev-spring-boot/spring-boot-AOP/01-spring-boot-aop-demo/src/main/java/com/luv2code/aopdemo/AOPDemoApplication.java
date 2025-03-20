package com.luv2code.aopdemo;

import com.luv2code.aopdemo.DAO.AccountDAO;
import com.luv2code.aopdemo.DAO.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AOPDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AOPDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){
		return runner ->{

			//System.out.println("Hello World");

			DemoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
		};

	}
	private void DemoTheBeforeAdvice(AccountDAO theAccountDAO,MembershipDAO theMembershipDAO) {

		// call the business method
		Account theAccount = new Account();
		theAccountDAO.addAccount(theAccount,true);
		theAccountDAO.doWork();


		//call the membership business method
		theMembershipDAO.addBorhane();
		theMembershipDAO.goToSleep();


	}


}
