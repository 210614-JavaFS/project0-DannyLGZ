package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Daos.AccountDAO;
import Daos.AccountDAOImpl;
import Daos.CustomerDAOImpl;
import models.Account;
import utils.ConnectionUtil;

public class AccountService {
	
	private static AccountDAO accountDAO = new AccountDAOImpl();
	private static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	private Scanner scan = new Scanner(System.in);
	
	
	public void deposit() {
		System.out.println("Please enter account number deposit to.");
		int accNumber = Integer.parseInt(scan.nextLine());
		try(Connection conn = ConnectionUtil.getConnection()){
			String checkAccNumber = "SELECT * FROM accounts WHERE (acc_number = ? AND acc_status=?);";
			PreparedStatement statement = conn.prepareStatement(checkAccNumber);
			statement.setInt(1, accNumber);
			statement.setBoolean(2, true);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				Account account = new Account();
				account.setAccNumber(result.getInt("acc_number"));
				account.setAccType(result.getString("acc_type"));
				account.setBalance(result.getDouble("balance"));
				System.out.println("Please enter amount to deposit.");
				try {
					double amountDep = Double.parseDouble(scan.nextLine());
					if (amountDep>=0) {
						accountDAO.deposit(account, amountDep);
						System.out.println("Deposit Success!");
					}else {
						System.out.println("You have entered invalid input, please try again.");
					}
				} catch(NumberFormatException e) {
					log.warn(account.getAccType()+" "+account.getAccNumber()+" tried to deposit with invalid input.");
				}
			}else {
				System.out.println("Error 404, account not found! Please try again.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void withdraw() {
		System.out.println("Please enter account number withdraw from.");
		int accNumber = Integer.parseInt(scan.nextLine());
		try(Connection conn = ConnectionUtil.getConnection()){
			String checkAccNumber = "SELECT * FROM accounts WHERE (acc_number = ? AND acc_status = ?);";
			PreparedStatement statement = conn.prepareStatement(checkAccNumber);
			statement.setInt(1, accNumber);
			statement.setBoolean(2, true);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				Account account = new Account();
				account.setAccNumber(result.getInt("acc_number"));
				account.setAccType(result.getString("acc_type"));
				account.setBalance(result.getDouble("balance"));
				System.out.println("Please enter amount to withdraw.");
				try {
					double amountWit = Double.parseDouble(scan.nextLine());
					accountDAO.withdraw(account, amountWit);
					System.out.println("Withdraw Success!");
				} catch(NumberFormatException e) {
					log.warn(account.getAccType()+" "+account.getAccNumber()+" tried to withdraw with invalid input.");
//					adminController.opsMenu();
				}
			}else {
				System.out.println("Error 404, account not found! Please try again.");
//				adminController.opsMenu();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void transfer() {
		System.out.println("Please enter account number transfer FROM");
		int fromAccNumber = Integer.parseInt(scan.nextLine());
//		System.out.println("Please enter account number transfer TO");
//		int toAccNumber = Integer.parseInt(scan.nextLine());
//		System.out.println("Please enter the amount to transfer.");
//		int amountTransfer = Integer.parseInt(scan.nextLine());
		try (Connection conn = ConnectionUtil.getConnection()){
			String check_fromAccStatus = "SELECT * FROM accounts WHERE acc_number = ?";
			PreparedStatement statement = conn.prepareStatement(check_fromAccStatus);
			statement.setInt(1, fromAccNumber);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
//				Boolean accStatus = result.getBoolean("acc_status");
				Account account = new Account();
				account.setAccNumber(result.getInt("acc_number"));
				account.setAccType(result.getString("acc_type"));
				account.setBalance(result.getDouble("balance"));
				account.setAccStatus(result.getBoolean("acc_status"));
				
				if (account.isAccStatus()) {
					System.out.println("Please enter account number transfer TO");
					int toAccNumber = Integer.parseInt(scan.nextLine());
					String check_toAccStatus = "SELECT * FROM accounts WHERE acc_number = ?";
					
					PreparedStatement statement2 = conn.prepareStatement(check_toAccStatus);
					statement2.setInt(1, toAccNumber);
					ResultSet result2 = statement2.executeQuery();
					if (result2.next()) {
//						Boolean accStatus2 = result2.getBoolean("acc_status");
						Account accountTo = new Account();
						accountTo.setAccNumber(result2.getInt("acc_number"));
						accountTo.setAccType(result2.getString("acc_type"));
						accountTo.setBalance(result2.getDouble("balance"));
						accountTo.setAccStatus(result2.getBoolean("acc_status"));
						if (accountTo.isAccStatus()) {
							System.out.println("Please enter the amount to transfer.");
							int amountTransfer = Integer.parseInt(scan.nextLine());
							accountDAO.transfer(account, accountTo, amountTransfer);
						}else {
							System.out.println("The account you want to transfer TO is under review.Please try again.");
						}
					}else {
						System.out.println("The account you want to transfer TO is not exist.Please try again.");
					}
			
				}else {
					System.out.println("The account you want to transfer FROM is under review.Please try again.");
				}
			}else {
				System.out.println("The account you want to transfer FROM is not exist.Please try again.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void cancelAccount() {
		System.out.println("Please enter account number to close.");
		int accNumber = Integer.parseInt(scan.nextLine());
		try(Connection conn = ConnectionUtil.getConnection()){
			String checkAccNumber = "SELECT * FROM customers WHERE (acc_number = ? AND acc_status = ?);";
			PreparedStatement statement = conn.prepareStatement(checkAccNumber);
			statement.setInt(1, accNumber);
			statement.setBoolean(2, true);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				Account account = new Account();
				account.setAccNumber(result.getInt("acc_number"));
				account.setAccType(result.getString("acc_type"));
				System.out.println("Closing account is irreversible, are you sure to continue? yes/no");
				String response = scan.nextLine();
				if (response.toLowerCase().equals("yes")) {
					if(accountDAO.removeAccount(account)) {
						System.out.println("The account is now close!");
						log.info(account.getAccType()+" "+account.getAccNumber()+ " is closed.");
						System.out.println("================================================");
					}else {
						System.out.println("Something went wrong, please try again.");
					}
				}else if(response.toLowerCase().equals("no")) {
					return;
				}
				
			}else {
				System.out.println("Error 404, account not found! Please try again.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void showAllAccounts() {
		List<Account> accounts = accountDAO.findAllAccounts();
		for (Account a:accounts) {
			System.out.println(a);
		}
	}
	
	
	public void showOneAllAccounts(String name) {
		List<Account> oneAccounts = accountDAO.findOneAllAccounts(name);
		for (Account oneA:oneAccounts) {
			System.out.println(oneA);
		}
	}

	public void showOneAllActiveAccounts(String name) {
		List<Account> oneAccounts = accountDAO.findOneAllActiveAccounts(name);
		for (Account oneA:oneAccounts) {
			System.out.println(oneA);
		}
	}
	
	public void showPendingAccounts() {
		List<Account> accountsPending = accountDAO.findPendingAccounts();
		for (Account aP:accountsPending) {
			System.out.println(aP);
		}
	}
	
	
	
	public Account getOneAccount(int accNumber){
		return accountDAO.findByAccNumber(accNumber);
	}
	
//	public void showAccountBalance(Account account) {
//		return accountDAO.showBalance(account);
//	}
	
	
}
