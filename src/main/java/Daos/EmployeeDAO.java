package Daos;


import models.Account;
import models.Employee;

public interface EmployeeDAO {

	public boolean approveAccount(Employee employee, Account account);
	
}
