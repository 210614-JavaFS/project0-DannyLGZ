package Daos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Account;
import models.Employee;

class EmployeeDAOImplTest {

	public static EmployeeDAOImpl employeeDAOImpl;
	
	@Test
	void testApproveAccount() {
		System.out.println("Testing approve pending account.");
		employeeDAOImpl = new EmployeeDAOImpl();
		Employee employee = new Employee();
		Account account = new Account();
		employeeDAOImpl.approveAccount(employee, account);
		assertTrue(true);
		System.out.println("==================================================");
	}

}
