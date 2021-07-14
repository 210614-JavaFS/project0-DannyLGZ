package Daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.Account;
import utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{
	
	public static Random rd = new Random();
	private static Logger log = LoggerFactory.getLogger(AccountDAOImpl.class);

	@Override
	public boolean newCheckingAccount(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String newAccount = "INSERT INTO accounts (user_id, user_password,acc_type, acc_number,balance,acc_status,employee_id)"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(newAccount);
			int accountNum = 10000+ rd.nextInt(89999);
			int index = 0;
			statement.setString(++index, username);
			statement.setString(++index, password);
			statement.setString(++index, "checking");
			statement.setInt(++index, accountNum);
			statement.setInt(++index, 0);
			statement.setBoolean(++index, false);
			statement.setInt(++index, 0);
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean newSavingAccount(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String newAccount = "INSERT INTO accounts (user_id, user_password,acc_type, acc_number,balance,acc_status,employee_id)"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(newAccount);
			int accountNum = 1000+ rd.nextInt(8999);
			int index = 0;
			statement.setString(++index, username);
			statement.setString(++index, password);
			statement.setString(++index, "saving");
			statement.setInt(++index, accountNum);
			statement.setInt(++index, 0);
			statement.setBoolean(++index, false);
			statement.setInt(++index, 0);
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deposit(Account account, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE accounts SET balance = ? "
					+ "WHERE acc_number = ?;";
//			Statement statement = conn.createStatement();
			PreparedStatement statement = conn.prepareStatement(sql);
			Double newBalance = account.getBalance()+amount;
			account.setBalance(newBalance);
			statement.setDouble(1, newBalance);
			statement.setInt(2, account.getAccNumber());
//			ResultSet result = statement.executeQuery();
			statement.execute();
			System.out.println("You deposit: "+amount+" to your account.");
			System.out.println("Your current account balance is: $"+newBalance);
			log.info(account.getAccType()+" account "+account.getAccNumber()+": Deposit $"+amount);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void withdraw(Account account, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE customers SET balance = ? "
					+ "WHERE acc_number = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			if ((amount >= 0) && (amount<= account.getBalance())) {
				Double newBalance = account.getBalance()-amount;
				account.setBalance(newBalance);
				statement.setDouble(1, newBalance);
				statement.setInt(2, account.getAccNumber());
				statement.execute();
				System.out.println("You withdraw: "+amount+" from your account.");
				System.out.println("Your current account balance is: $"+newBalance);
				log.info(account.getAccType()+" account "+account.getAccNumber()+": withdraw $"+amount);
				
			}else if((amount >=0) && (amount > account.getBalance())){
				System.out.println("You don't have sufficient in you account. Operation denied.");
			}else {
				System.out.println("You have entered invalid input, please try again.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void transfer(Account account, Account accountTo, double amount) {
		try(Connection conn = ConnectionUtil.getConnection()){
			if ((amount >= 0) && (amount<= account.getBalance())) {
				String fromAcc = "UPDATE accounts SET balance = ? WHERE acc_number = ?;";
				String toAcc = "UPDATE accounts SET balance = ? WHERE acc_number = ?;";
				PreparedStatement statementFromAcc = conn.prepareStatement(fromAcc);
				PreparedStatement statementToAcc = conn.prepareStatement(toAcc);
				Double newBalance1 = account.getBalance()-amount;
				Double newBalance2 = accountTo.getBalance()+amount;
				statementFromAcc.setDouble(1, newBalance1);
				statementFromAcc.setInt(2, account.getAccNumber());
				statementToAcc.setDouble(1, newBalance2);
				statementToAcc.setInt(2, accountTo.getAccNumber());
				statementFromAcc.execute();
				statementToAcc.execute();
				System.out.println(account.getAccType()+" "+account.getAccNumber()+" transferred $"+amount+" to "
						+accountTo.getAccType()+" "+accountTo.getAccNumber());
				log.info(account.getAccType()+" "+account.getAccNumber()+" transferred $"+amount+" to "
						+accountTo.getAccType()+" "+accountTo.getAccNumber());
			} else if((amount >=0) && (amount > account.getBalance())) {
				System.out.println("You don't have sufficient money in the account to transfer. Please try again.");
			} else {
				System.out.println("You have entered invalid input, please try again.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public List<Account> showBalance(Account account) {
//		try(Connection conn = ConnectionUtil.getConnection()){
//			String sqlBalance = "SELECT balance FROM accounts WHERE user_id = ?;";
//			Statement getBalance = conn.createStatement();
//			ResultSet allBalance = getBalance.executeQuery(sqlBalance);
//			List<Account> listBalance = new ArrayList<>();
//			while(allBalance.next()) {
//				
//			}
//		
//		
//		System.out.println("Your account current balance is $"+account.getBalance());
//	}catch(SQLException e) {
//		e.printStackTrace();
//		}
//	}
		

	@Override
	public boolean removeAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String newAccount = "DELETE FROM accounts WHERE acc_number = ?;";
			
			PreparedStatement statement = conn.prepareStatement(newAccount);
			statement.setInt(1, account.getAccNumber());
			statement.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Account> findAllAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> listAccount = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes.
			while(result.next()) {
				Account account = new Account();
				account.setUserID(result.getString("user_id"));
				account.setAccType(result.getString("acc_type"));
				account.setAccNumber(result.getInt("acc_number"));
				account.setBalance(result.getInt("balance"));
				account.setAccStatus(result.getBoolean("acc_status"));
				account.setEmployeeID(result.getInt("employee_id"));
				listAccount.add(account);
			}
			return listAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> findOneAllAccounts(String name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts WHERE user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			List<Account> listOneAccount = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes.
			while(result.next()) {
				Account account = new Account();
				account.setUserID(result.getString("user_id"));
				account.setAccType(result.getString("acc_type"));
				account.setAccNumber(result.getInt("acc_number"));
				account.setBalance(result.getInt("balance"));
				account.setAccStatus(result.getBoolean("acc_status"));
				account.setEmployeeID(result.getInt("employee_id"));
				listOneAccount.add(account);
			}
			return listOneAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Account> findOneAllActiveAccounts(String name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts WHERE (user_id = ? AND acc_status = ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setBoolean(2, true);
			
			ResultSet result = statement.executeQuery();
			List<Account> listOneAccount = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes.
			while(result.next()) {
				Account account = new Account();
				account.setUserID(result.getString("user_id"));
				account.setAccType(result.getString("acc_type"));
				account.setAccNumber(result.getInt("acc_number"));
				account.setBalance(result.getInt("balance"));
				account.setAccStatus(result.getBoolean("acc_status"));
				account.setEmployeeID(result.getInt("employee_id"));
				listOneAccount.add(account);
			}
			return listOneAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	@Override
	public Account findByAccNumber(int accNumber) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts WHERE acc_number = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			//This is where SQL injection is checked for
			statement.setInt(1, accNumber);
			//Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery();
			Account account = new Account();
			//ResultSets have a cursor similarly to Scanners or other I/O classes.
			while(result.next()) {
				account.setUserID(result.getString("user_id"));
				account.setAccType(result.getString("acc_type"));
				account.setAccNumber(result.getInt("acc_number"));
				account.setBalance(result.getInt("balance"));
				account.setAccStatus(result.getBoolean("acc_status"));
				account.setEmployeeID(result.getInt("employee_id"));
			}
			return account;
			//opsMenu(account);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> findPendingAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql2 = "SELECT * FROM accounts WHERE acc_status = False;";
			Statement statement2 = conn.createStatement();
			
			ResultSet result2 = statement2.executeQuery(sql2);
			
			List<Account> listPendingAccount = new ArrayList<>();
			
			while (result2.next()) {
				Account accountPending = new Account();
				accountPending.setUserID(result2.getString("user_id"));
				accountPending.setAccType(result2.getString("acc_type"));
				accountPending.setAccNumber(result2.getInt("acc_number"));
				accountPending.setBalance(result2.getInt("balance"));
				accountPending.setAccStatus(result2.getBoolean("acc_status"));
				accountPending.setEmployeeID(result2.getInt("employee_id"));
				listPendingAccount.add(accountPending);
			}
			return listPendingAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
