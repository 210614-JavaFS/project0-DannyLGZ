package Daos;

import java.util.List;

import models.Customer;

public interface CustomerDAO {
	
	public List<Customer> findAll();
	public Customer findByName(String lastName, int last4SSN);
	public boolean register(Customer customer);
}
