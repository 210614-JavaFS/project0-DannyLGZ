package services;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Daos.AccountDAO;
import Daos.AccountDAOImpl;
import Daos.CustomerDAO;
import Daos.CustomerDAOImpl;
//import controllers.CustomerController;
import models.Customer;
import utils.ConnectionUtil;

public class CustomerService {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	private static CustomerDAO customerDAO = new CustomerDAOImpl();
	private static AccountDAO accountDAO = new AccountDAOImpl();
//	private static CustomerController customerController = new CustomerController();
	public static Random rd = new Random();
	
	
	public void register() {
		System.out.println("Please enter your User ID");
		String userId = scan.nextLine();
		try (Connection conn = ConnectionUtil.getConnection()){
			String checkUserName = "SELECT * FROM customer WHERE user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(checkUserName);
			statement.setString(1, userId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				System.out.println("The User Id has been taken, please try another one.");
				register();
			}else{
				System.out.println("Please enter your password.");
				String userPassword = scan.nextLine();
				System.out.println("Please re-enter your password to confirm.");
				String pw = scan.nextLine();
				if (userPassword.equals(pw)) {
					System.out.println("Please continue and set up your account profile.");
					System.out.println("Please enter you first name.");
					String firstName = scan.nextLine();
					System.out.println("Please enter you last name.");
					String lastName = scan.nextLine();
					System.out.println("Please enter you address.");
					String add = scan.nextLine();
					System.out.println("Please enter you phone number.");
					String phoneNum = scan.nextLine();
					System.out.println("Please enter you email.");
					String eMail = scan.nextLine();
					System.out.println("Please enter you last 4 SSN.");
					int last4SSN = Integer.parseInt(scan.nextLine());
					Customer customer = new Customer(userId,userPassword,firstName,lastName,add,eMail,phoneNum,last4SSN);
					if(customerDAO.register(customer)) {
						System.out.println("Congratulations! You have registered a login account.");
						log.info(customer.getFirstName()+" "+customer.getLastName()+" registered an login account.");
						System.out.println("You don't have any account yet, do you want to open an account? \n"
								+ "1) Open a Checking account. \n"
								+ "2) Open a Saving account.");
						String choice = scan.nextLine();
						switch(choice) {
						case "1":
							if(accountDAO.newCheckingAccount(userId, userPassword)) {
								System.out.println("Thank you for applying a checking account, your application is now under review.");
							}else {
								System.out.println("Something went wrong. please try again.");
							}
							break;
						case "2":
							if(accountDAO.newSavingAccount(userId, userPassword)) {
								System.out.println("Thank you for applying a saving account, your application is now under review.");
							}else {
								System.out.println("Something went wrong. please try again.");
							}
							break;
						default:
							System.out.println("Invalid input. Please try again.");
							break;
						}
					
					}else {
						System.out.println("Something went wrong, please try again.");
						register();
					}
				}else {
					System.out.println("The password you enter is not match, please try again.");
					register();
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
					
	
	public void showAllCustomers() {
		List<Customer> customers = customerDAO.findAll();
		for (Customer c:customers) {
			System.out.println(c);
		}
	}
	
	
	public void showOneCustomer() {
		System.out.println("Please enter the last name of the customer to review.");
		String lastN = scan.nextLine();
		System.out.println("Please enter the last 4 digits of SSN of the customer to confirm.");
		int lastS = Integer.parseInt(scan.nextLine());
		Customer customer = customerDAO.findByName(lastN, lastS);
		if(customer!=null) {
			System.out.println(customer);
		}else {
			System.out.println("Error 404, customer not found. Please try again.");
			showOneCustomer();
		}
	}
}

