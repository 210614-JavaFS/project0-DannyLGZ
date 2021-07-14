package controllers;

import java.util.Scanner;

import Daos.AccountDAO;
import Daos.AccountDAOImpl;
import models.Account;
import models.Login;
import services.AccountService;
import services.CustomerService;

public class CustomerController {
	
	private static CustomerService customerService = new CustomerService();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private AccountService accountService = new AccountService(); 
	private static Scanner scan = new Scanner(System.in);
	private static Login login = new Login();
	
	public void createLoginMenu() {
		System.out.println("Are you current customer? \n"
				+ "1) Yes, login \n"
				+ "2) No, register new account. \n"
				+ "3) Exit");
		String choice4 = scan.nextLine();
		switch(choice4) {
		case "1":
			login.customerLogin();
			break;
		case "2":
			customerService.register();
			createLoginMenu();
			break;
		case "3":
			System.out.println("You are now logout.");
			return;
		default:
			System.out.println("Invalid input. Please try again.");
			createLoginMenu();
			break;
		}
	}
	
	
	
	public void customerMenu(Account account) {
		System.out.println("Please select: \n"
				+ "1) Open a Checking account \n"
				+ "2) Open a Saving account \n"
				+ "3) Deposit Fund \n"
				+ "4) Withdraw \n"
				+ "5) Transfer Fund \n"
				+ "6) Check Balance \n"
				+ "7) Log Out");
		String choice5 = scan.nextLine();
		
		switch (choice5) {
		case "1":
			if(accountDAO.newCheckingAccount(account.getUserID(), account.getUserPassword())) {
				System.out.println("Thank you, your account is now under review.");
			}
			System.out.println("==================================================");
			customerMenu(account);
			break;
		case "2":
			if(accountDAO.newSavingAccount(account.getUserID(), account.getUserPassword())) {
				System.out.println("Thank you, your account is now under review.");
			}
			System.out.println("==================================================");
			customerMenu(account);
			break;
		case "3":
			accountService.deposit();
			System.out.println("==================================================");
			customerMenu(account);
			break;
		case "4":
			accountService.withdraw();
			System.out.println("==================================================");
			customerMenu(account);
			break;
		case "5":
			accountService.transfer();
			System.out.println("==================================================");
			customerMenu(account);
			break;
		case "6":
			accountService.showOneAllActiveAccounts(account.getUserID());
			System.out.println("==================================================");
			customerMenu(account);
			break;
		case "7":
			System.out.println("Thank you. You've been successfully logged out!");
			break;
		default:
			System.out.println("Invalid entry. Please try again.");
			customerMenu(account);
		}
	}
}
