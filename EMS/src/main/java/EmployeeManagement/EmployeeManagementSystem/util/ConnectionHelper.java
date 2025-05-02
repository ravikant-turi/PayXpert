package EmployeeManagement.EmployeeManagementSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionHelper {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		
		ResourceBundle rb = ResourceBundle.getBundle("db");
		
		String driver = rb.getString("driver");
		String url = rb.getString("url");
		String user = rb.getString("user");
		String password = rb.getString("password");

		Class.forName(driver);

		Connection connetion = DriverManager.getConnection(url, user, password);
//           System.out.println("connection is "+ connetion);
		return connetion;
	}
}
