package services;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import utils.ConnectionUtil;

class EmployeeServiceTest {

	@Test
	void testAccApproval() throws SQLException{
		Connection con = ConnectionUtil.getConnection();
		System.out.println(con);
	}

}
