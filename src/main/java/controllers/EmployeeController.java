package controllers;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Daos.CustomerDAOImpl;
import main.Main;
//import Daos.EmployeeDAO;
//import Daos.EmployeeDAOImpl;
//import models.Account;
import models.Employee;
import services.AccountService;
import services.CustomerService;
import services.EmployeeService;
//import utils.ConnectionUtil;

public class EmployeeController {
	
	private static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	private static Scanner scan = new Scanner(System.in);
	private static EmployeeService employeeService = new EmployeeService();
//	private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private AccountService accountService = new AccountService();
//	private static Main main = new Main();
	private CustomerService customerService = new CustomerService();
	
	public void employeeMenu(Employee employee) {
		
		System.out.println("Please select: \n"
				+ "1) Account Application \n"
				+ "2) Account Information \n"
				+ "3) Customer Information \n"
				+ "4) Logout");
		String choice1 = scan.nextLine();
		
		switch (choice1) {
		case "1":
			accountService.showPendingAccounts();
			employeeService.accApproval(employee);
			System.out.println("==================================================");
			employeeMenu(employee);
			break;
		case "2":
			accountService.showAllAccounts();
			System.out.println("Do you want to check specific customer's account(s)? yes/no");
			String answer1 = scan.nextLine();
			if (answer1.toLowerCase().equals("yes")) {
				System.out.println("Please enter the user_id to inspect.");
				String user_id = scan.nextLine();
				accountService.showOneAllAccounts(user_id);
				System.out.println("==================================================");
				employeeMenu(employee);
			}else if(answer1.toLowerCase().equals("no")) {
				employeeMenu(employee);
			}
			System.out.println("==================================================");
			employeeMenu(employee);
			break;
		case "3":
			customerService.showAllCustomers();
			System.out.println("Do you want to inspect specific customer? yes/no");
			String answer2 = scan.nextLine();
			if (answer2.toLowerCase().equals("yes")) {
				customerService.showOneCustomer();
				System.out.println("==================================================");
				employeeMenu(employee);
			}else if(answer2.toLowerCase().equals("no")) {
				employeeMenu(employee);
			}
			break;
		case "4":
			System.out.println("Thank you. You've been successfully logged out!");
			Main.mainMenu();
			break;
		default:
			System.out.println("Invalid entry. Please try again.");
			employeeMenu(employee);
			break;
		}
	}

	
	
	public void adminMenu(Employee employee) {
		
		System.out.println("Please select: \n"
				+ "1) Account Application \n"
				+ "2) Account Information \n"
				+ "3) Customer Information \n"
				+ "4) Account Operation \n"
				+ "5) Log Out");
		String choice2 = scan.nextLine();
		
		switch (choice2) {
		case "1":
			accountService.showPendingAccounts();
			employeeService.accApproval(employee);
			System.out.println("==================================================");
			adminMenu(employee);
			break;
		case "2":
			accountService.showAllAccounts();
			System.out.println("Do you want to check specific customer's account(s)? yes/no");
			String answer3 = scan.nextLine();
			if (answer3.toLowerCase().equals("yes")) {
				System.out.println("Please enter the user_id to inspect.");
				String user_id = scan.nextLine();
				accountService.showOneAllAccounts(user_id);
				System.out.println("==================================================");
				adminMenu(employee);
			}else if(answer3.toLowerCase().equals("no")) {
				adminMenu(employee);
			}
			System.out.println("==================================================");
			adminMenu(employee);
			break;
		case "3":
			customerService.showAllCustomers();
			System.out.println("Do you want to inspect specific customer? yes/no");
			String answer4 = scan.nextLine();
			if (answer4.toLowerCase().equals("yes")) {
				customerService.showOneCustomer();
				System.out.println("==================================================");
				adminMenu(employee);
			}else if(answer4.toLowerCase().equals("no")) {
				adminMenu(employee);
			}
			break;
		case "4":
			opsMenu(employee);
			break;
		case "5":
			System.out.println("You've been successfully logged out!");
			log.info("Employee "+employee.getUserId()+" logout.");
			Main.mainMenu();
			break;
		default:
			System.out.println("Invalid entry. Please try again.");
			adminMenu(employee);
		}
	}
		
	public void opsMenu(Employee employee) {
		System.out.println("Please select: \n"
				+ "1) Deposit \n"
				+ "2) Withdraw \n"
				+ "3) Transfer \n"
				+ "4) Closing \n"
				+ "5) Go Back");
		String choice3 = scan.nextLine();
		switch (choice3) {
		case "1":
			accountService.deposit();
			System.out.println("===========================================================");
			opsMenu(employee);
			break;
		case "2":
			accountService.withdraw();
			System.out.println("===========================================================");
			opsMenu(employee);
			break;
		case "3":
			accountService.transfer();
			break;
		case "4":
			accountService.cancelAccount();
			System.out.println("===========================================================");
			opsMenu(employee);
			break;
		case "5":
			adminMenu(employee);
			break;
		default:
			System.out.println("Invalid Input.");
			System.out.println("===========================================================");
			opsMenu(employee);
		}
	}

		
	
	
	
	
//	public void accApproval(Employee employee) {
//		System.out.println("Please enter account number to review.");
//		int accNumber = Integer.parseInt(scan.nextLine());
//		try(Connection conn = ConnectionUtil.getConnection()){
//			String checkAccNumber = "SELECT * FROM customers WHERE acc_number = ?;";
//			PreparedStatement statement3 = conn.prepareStatement(checkAccNumber);
//			statement3.setInt(1, accNumber);
//			ResultSet result = statement3.executeQuery();
//			if(result.next()) {
//				Account account = new Account();
//				account.setAccNumber(result.getInt("acc_number"));
//				employeeDAO.approveAccount(employee,account);
//				employeeMenu(employee);
//			}
//			else {
//				System.out.println("Error 404, account not found! Please try again.");
//				employeeMenu(employee);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	
	
	
	
}
