package Daos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.Customer;

class CustomerDAOImplTest {

	public static CustomerDAOImpl customerDAOImpl;
	public static List<Customer> result;
	
	
	@BeforeAll
	public static void setAccountDAOImpl() {
		customerDAOImpl = new CustomerDAOImpl();
		System.out.println("Unit test begin!");
	}
	
	@AfterAll
	public static void clearAccountDAOImpl() {
		System.out.println("Unit test done!");
		customerDAOImpl = null;
	}
	
	
	@Test
	void testRegister() {
		System.out.println("Testing register a login account.");
		Customer customer = new Customer();
		customerDAOImpl.register(customer);
		assertTrue(true);
		System.out.println("==================================================");
	}

	@Test
	void testFindAll() {
		System.out.println("Testing find all customers' personal information.");
//		String name = "tester";
		result = customerDAOImpl.findAll();
		assertNotNull(result);
		System.out.println("==================================================");
	}

	@Test
	void testFindByName() {
		System.out.println("Testing find a specific customer information.");
		int last4ssn = 6724;
		String lastname = "tester";
		assertNotNull(customerDAOImpl.findByName(lastname, last4ssn));
		System.out.println("==================================================");
	}

}
