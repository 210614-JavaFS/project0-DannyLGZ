package utils;


import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ConnectionUtilTest {

	
	@Test
	void testGetConnection() throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		System.out.println(conn);
	}

}
