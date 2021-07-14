package Daos;

import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import models.Customer;
import utils.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO{
	
//	private static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	protected double accBalance;


	@Override
	public boolean register(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO customer (user_id,user_password,first_name,last_name,address,email,phone_number,last4_ssn)"
					+ " VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int index = 0;
			statement.setString(++index,customer.getUserId());
			statement.setString(++index,customer.getUserPassword());
			statement.setString(++index,customer.getFirstName());
			statement.setString(++index,customer.getLastName());
			statement.setString(++index,customer.getAddress());
			statement.setString(++index,customer.getEmail());
			statement.setString(++index,customer.getPhoneNum());
			statement.setInt(++index,customer.getLast4SSN());
			statement.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Customer> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customer;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Customer> listCustomer = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes.
			while(result.next()) {
				Customer customer = new Customer();
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
				customer.setAddress(result.getString("address"));
				customer.setEmail(result.getString("email"));
				customer.setPhoneNum(result.getString("phone_number"));
				customer.setLast4SSN(result.getInt("last4_ssn"));
				listCustomer.add(customer);
			}
			return listCustomer;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Customer findByName(String lastName, int last4SSN) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customer WHERE last_name = ? AND last4_ssn = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, lastName);
			statement.setInt(2, last4SSN);
			//Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery();
			Customer customer = new Customer();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes.
			while(result.next()) {
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
				customer.setAddress(result.getString("address"));
				customer.setEmail(result.getString("email"));
				customer.setPhoneNum(result.getString("phone_number"));
				customer.setLast4SSN(result.getInt("last4_ssn"));
			}
			return customer;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}