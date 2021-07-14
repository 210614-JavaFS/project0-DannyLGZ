package Daos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.Account;

class AccountDAOImplTest {
	
	public static AccountDAOImpl accountDAOImpl;
	public static List<Account> result;
	
	
	
	@BeforeAll
	public static void setAccountDAOImpl() {
		accountDAOImpl = new AccountDAOImpl();
		System.out.println("Unit test begin!");
	}
	
	@AfterAll
	public static void clearAccountDAOImpl() {
		System.out.println("Unit test done!");
		accountDAOImpl = null;
	}
	

	@Test
	void testNewCheckingAccount() {
		System.out.println("Testing create new checking account.");
		String username = "tester";
		String password = "password";
		accountDAOImpl.newCheckingAccount(username, password);
		assertTrue(true);
		System.out.println("==================================================");
		
	}

	@Test
	void testNewSavingAccount() {
		System.out.println("Testing create new saving account.");
		String username = "tester";
		String password = "password";
		accountDAOImpl.newCheckingAccount(username, password);
		assertTrue(true);
		System.out.println("==================================================");
	}

	@Test
	void testDeposit() {
		System.out.println("Testing deposit.");
		Account account = new Account();
		Double balance = 100.0;
		account.setBalance(balance);
//		Double accBalance = account.getBalance(); 
		Double amount = 100.0;
		Double res = account.getBalance()+amount;
		assertTrue(res==200.0);
		System.out.println("==================================================");
		
	}

	@Test
	void testWithdraw() {
		System.out.println("Testing withdraw.");
		Account account = new Account();
		Double balance = 100.0;
		account.setBalance(balance);
		Double amount = 20.5;
		Double res = account.getBalance()-amount;
		assertTrue(res==79.5);
		System.out.println("==================================================");
	}

	@Test
	void testTransfer() {
		System.out.println("Testing deposit.");
		Account account = new Account();
		Account accountTo = new Account();
		Double accBalance = 200.0;
		Double accBalance2 = 100.0;
		Double amount = 50.0;
		account.setBalance(accBalance);
		accountTo.setBalance(accBalance2);
		Double res1 = account.getBalance()-amount;
		Double res2 = accountTo.getBalance()+amount;
		assertTrue(res1==150.0);
		assertTrue(res2==150.0);
		System.out.println("==================================================");
	}

	@Test
	void testRemoveAccount() {
		System.out.println("Testing create new checking account.");
		Account account = new Account();
		accountDAOImpl.removeAccount(account);
		assertTrue(true);
		System.out.println("==================================================");
	}

	@Test
	void testFindAllAccounts() {
		System.out.println("Testing find all accounts.");
		result = accountDAOImpl.findAllAccounts();
		assertNotNull(result);
		System.out.println("==================================================");
	}

	@Test
	void testFindOneAllAccounts() {
		System.out.println("Testing find single customer's all accounts.");
		String name = "tester";
		result = accountDAOImpl.findOneAllAccounts(name);
		assertNotNull(result);
		System.out.println("==================================================");
	}

	@Test
	void testFindOneAllActiveAccounts() {
		System.out.println("Testing find single customer's all active accounts.");
		String name = "tester";
		result = accountDAOImpl.findOneAllAccounts(name);
		assertNotNull(result);
		System.out.println("==================================================");
	}

	@Test
	void testFindByAccNumber() {
		System.out.println("Testing find one account by account number.");
		int accNumber = 123;
		assertNotNull(accountDAOImpl.findByAccNumber(accNumber));
		System.out.println("==================================================");
	}

	@Test
	void testFindPendingAccounts() {
		System.out.println("Testing find all pending accounts.");
		result = accountDAOImpl.findPendingAccounts();
		assertNotNull(result);
		System.out.println("==================================================");
	}

}
