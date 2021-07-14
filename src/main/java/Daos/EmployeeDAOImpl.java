package Daos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Account;
import models.Employee;
import utils.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	
	 private static Scanner scan = new Scanner(System.in);
	 private static Logger log = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	

	@Override
	public boolean approveAccount(Employee employee, Account account) {
		System.out.println("Please enter review result. approve/deny");
		String option = scan.nextLine();
		try(Connection conn = ConnectionUtil.getConnection()){
			if(option.toLowerCase().equals("approve")) {
				String update = "UPDATE accounts SET acc_status = ?, employee_id = ?"
						+ "WHERE acc_number = ?;";
				account.setAccStatus(true);
				account.setEmployeeID(employee.getIndexId());
				PreparedStatement statement = conn.prepareStatement(update);

				statement.setBoolean(1, account.isAccStatus());
				statement.setInt(2, account.getEmployeeID());
				statement.setInt(3, account.getAccNumber());
				statement.execute();
				System.out.println("The account application has been approved.");
				log.info(employee.getUserId()+" approved "+account.getAccType()+" "+account.getAccNumber());
			}else if(option.toLowerCase().equals("deny")) {
				String newAccount = "DELETE FROM accounts WHERE acc_number = ?;";

				PreparedStatement statement = conn.prepareStatement(newAccount);
				statement.setInt(1, account.getAccNumber());
				statement.execute();
				System.out.println("The application has been rejected.");
				log.info(employee.getUserId()+" rejected "+account.getAccType()+" "+account.getAccNumber());
			}
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
