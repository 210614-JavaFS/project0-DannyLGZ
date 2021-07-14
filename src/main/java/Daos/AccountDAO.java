package Daos;

import java.util.List;


import models.Account;

public interface AccountDAO {
	public List<Account> findAllAccounts();
	public List<Account> findOneAllAccounts(String name);
	public List<Account> findOneAllActiveAccounts(String name);
	public List<Account> findPendingAccounts();
	public Account findByAccNumber(int accNumber);
	public boolean newCheckingAccount(String username, String password);
	public boolean newSavingAccount(String username, String password);
	public void deposit(Account account, double amount);
	public void withdraw(Account account, double amount);
	public void transfer(Account account, Account accountTo, double amount);
//	public void showBalance(Account account);
	public boolean removeAccount(Account account);
}
