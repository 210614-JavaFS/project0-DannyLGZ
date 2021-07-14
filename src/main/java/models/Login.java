package models;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import Daos.CustomerDAOImpl;
import controllers.CustomerController;
import controllers.EmployeeController;
import services.AccountService;

//import controllers.CustomerController;

//import javax.xml.transform.Result;

import utils.ConnectionUtil;

public class Login {
	
	private Scanner scan = new Scanner(System.in);
	private CustomerController customerController = new CustomerController();
	private EmployeeController employeeController = new EmployeeController();
	private AccountService accountService = new AccountService();
	private static Logger log = LoggerFactory.getLogger(Login.class);
	
	public Account customerLogin() {
		System.out.println("Please enter your UserID.");
		String userId = scan.nextLine();
		System.out.println("Please enter your password.");
		String password = scan.nextLine();
		try(Connection conn = ConnectionUtil.getConnection()){
//			String CHECK_USER = "SELECT * FROM customers WHERE user_id = '"+userId+"' AND user_password = '"+password+"';";
			String check_userName = "SELECT * FROM accounts WHERE user_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(check_userName);
			statement.setString(1, userId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String userPassword = result.getString("user_password");
				if (password.equals(userPassword)) {
					System.out.println("Login Success!");
					log.info(userId+" login.");
					System.out.println("Below are your account(s) detail.");
					System.out.println("====================================================");
					accountService.showOneAllActiveAccounts(userId);
					Account account = new Account(userId, password);
					customerController.customerMenu(account);
					
				}else {
					System.out.println("Invalid Password. Please try again.");
					customerLogin();
				}
			}else {
				System.out.println("Username Not Found, Please try again");
				customerLogin();
			}		
		}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	public Employee employeeLogin() {
		System.out.println("Please enter your Employee ID.");
		String empId = scan.nextLine();
		System.out.println("Please enter your password.");
		String password = scan.nextLine();
		try(Connection conn = ConnectionUtil.getConnection()){
			String check_empName = "SELECT * FROM employees WHERE employee_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(check_empName);
			statement.setString(1, empId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String empPassword = result.getString("employee_password");
				if (password.equals(empPassword)) {
					System.out.println("Login Success!");
					log.info("Employee "+empId+" login.");
					Employee employee = new Employee();
					employee.setIndexId(result.getInt("index_id"));
					employee.setEmployeeRole(result.getString("employee_role"));
					//return employee;
					if(employee.getEmployeeRole().equals("employee")) {
						employeeController.employeeMenu(employee);
					}else if(employee.getEmployeeRole().equals("admin")) {
						employeeController.adminMenu(employee);
					}
//					employeeController.employeeMenu();
				}else {
					System.out.println("Invalid Password. Please try again.");
					employeeLogin();
				}
			}else {
				System.out.println("Employee ID Not Found, Please try again");
				employeeLogin();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
