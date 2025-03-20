package com.luv2code.aopdemo;

import com.luv2code.aopdemo.DAO.AccountDAO;
import com.luv2code.aopdemo.DAO.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AOPDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AOPDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
											   MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficFortuneService){
		return runner ->{

			//System.out.println("Hello World");

			//DemoTheBeforeAdvice(theAccountDAO,theMembershipDAO);

			//demoTheAfterReturningAdvice(theAccountDAO);

			//demoTheAfterThrowingAdvice(theAccountDAO);

			//demoTheAfterAdvice(theAccountDAO);

			//demoTheAroundAdvice(theTrafficFortuneService);

			//demoTheAroundAdviceHandleException(theTrafficFortuneService);

			demoTheAroundAdviceRethrowException();

		};

	}

	private void demoTheAroundAdviceRethrowException() {

		System.out.println("\n Main Program: demoTheAroundAdviceRethrowException");

		System.out.println("Calling getFortune()");

		boolean tripwire =true;
		TrafficFortuneService theTrafficFortuneService = null;
		String data = theTrafficFortuneService.getFortune(tripwire);

		System.out.println("\nMy fortune is:"+data);
		System.out.println("Finishing");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\n Main Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripwire =true;
		String data = theTrafficFortuneService.getFortune(tripwire);

		System.out.println("\nMy fortune is:"+data);
		System.out.println("Finishing");

	}
	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\n Main Program: demoTheAroundAdvice");
		System.out.println("Calling getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("\nMy fortune is:"+data);
		System.out.println("Finishing");

	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {

		List <Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = false;
			theAccounts=theAccountDAO.findAccounts(tripWire);

		}catch (Exception exc){
			System.out.println("\n\nMain Program:... caught exception:" +exc);
		}

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("-----");
		System.out.println(theAccounts);
		System.out.println("\n");

	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

		// call method to find the accounts
		List <Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = true;
			theAccounts=theAccountDAO.findAccounts(tripWire);

		}catch (Exception exc){
			System.out.println("\n\nMain Program:... caught exception:" +exc);
		}

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("-----");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

		// call method to find the accounts
		List <Account> theAccounts = theAccountDAO.findAccounts();

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("-----");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void DemoTheBeforeAdvice(AccountDAO theAccountDAO,MembershipDAO theMembershipDAO) {

		// call the business method
		Account myAccount = new Account();
		myAccount.setName("BOB");
		myAccount.setLevel("Platinum");

		theAccountDAO.addAccount(myAccount,true);
		theAccountDAO.doWork();

		// call the accountDAO getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();


		//call the membership business method
		theMembershipDAO.addBorhane();
		theMembershipDAO.goToSleep();


	}


}
