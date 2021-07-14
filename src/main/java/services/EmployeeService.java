package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Daos.EmployeeDAO;
import Daos.EmployeeDAOImpl;
import models.Account;
import models.Employee;
import utils.ConnectionUtil;

public class EmployeeService {
	
	
	private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//	private static EmployeeController employeeController = new EmployeeController();
	private static Scanner scan = new Scanner(System.in);
	
	
	public void accApproval(Employee employee) {
		System.out.println("Please enter account number to review.");
		int accNumber = Integer.parseInt(scan.nextLine());
		try(Connection conn = ConnectionUtil.getConnection()){
			String checkAccNumber = "SELECT * FROM accounts WHERE acc_number = ?;";
			PreparedStatement statement3 = conn.prepareStatement(checkAccNumber);
			statement3.setInt(1, accNumber);
			ResultSet result = statement3.executeQuery();
			if(result.next()) {
				Account account = new Account();
				account.setAccNumber(result.getInt("acc_number"));
				account.setAccType(result.getString("acc_type"));
				employeeDAO.approveAccount(employee,account);
			}
			else {
				System.out.println("Error 404, account not found! Please try again.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
