package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO, TrafficFortuneService theTrafficFortuneService){
		return runner -> {
			//demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);
			demoTheAroundAdvice(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nmain program demoTheAroundAdvice");
		System.out.println("calling getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("fortune is "+ data);
		System.out.println("done");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try{
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}catch(Exception exc){
			System.out.println("\n\nmain program: .... caught exception "+exc);
		}
		System.out.println("\n\nmain program demoAfterTheAfterAdvice");
		System.out.println(theAccounts+"\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try{
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}catch(Exception exc){
			System.out.println("\n\nmain program: .... caught exception "+exc);
		}
		System.out.println("\n\nmain program demoAfterTheAfterThrowingAdvice");
		System.out.println(theAccounts+"\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = theAccountDAO.findAccounts();
		System.out.println("\n\nmain program demoAfterTheReturningAdvice");
		System.out.println(theAccounts+"\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();

		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();

		theMembershipDAO.addMember();
		theMembershipDAO.goToSleep();
	}

}
