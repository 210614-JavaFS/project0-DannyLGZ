package main;

import java.util.Scanner;

import controllers.CustomerController;
import models.Login;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	private static CustomerController customerController = new CustomerController();
//	private static EmployeeController employeeController = new EmployeeController();
//	private static AdminController adminController = new AdminController();
	private static Login login = new Login();
	
	public static void main(String[] args) {
		
		mainMenu();
	}
	
	
	public static void mainMenu() {
		System.out.println("Welcome to the Bank.");
		System.out.println("Please select from the menu. \n"
				+ "1) Customer \n"
				+ "2) Employee \n"
				+ "3) Exit");
		String choice = scan.nextLine();
		switch(choice) {
		case "1":
			customerController.createLoginMenu();
			break;
		case "2":
			System.out.println("Welcome back. Please login.");
			login.employeeLogin();
//			employeeController.employeeMenu();
			break;
		case "3":
			System.out.println("Thank you, have a nice day!");
			break;
		default:
			System.out.println("The is not a valid entry, please try again.");
			mainMenu();
		}
	}
	

}
