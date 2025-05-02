package EmployeeManagement.EmployeeManagementSystem.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import EmployeeManagement.EmployeeManagementSystem.dao.EmployeeDao;
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;
import EmployeeManagement.EmployeeManagementSystem.util.ConnectionHelper;

public class EmployeeDaoImpl implements EmployeeDao {
	static Connection connection;
	static PreparedStatement pst;

	static {
		try {
			connection = new ConnectionHelper().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String GetEmployeeByIdDao(int employeeId) throws SQLException {
		// TODO Auto-generated method stub

		if (searchEmployeeById(employeeId)) {

			return "employeeFound";

		} else {
			return "employee is not found";
		}
	}

	@Override
	public String GetAllEmployees() throws SQLException {
		// TODO Auto-generated method stub

		String query = "select * from Employee";

		pst = connection.prepareStatement(query);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt("EmployeeID") + " | " + rs.getString("FirstName") + " | "
					+ rs.getString("LastName") + " | " + rs.getDate("DateOfBirth") + " | " + rs.getString("Gender")
					+ " | " + rs.getString("Email") + " | " + rs.getString("PhoneNumber") + " | "
					+ rs.getString("Address") + " | " + rs.getString("Position") + " | " + rs.getDate("JoiningDate")
					+ " | " + rs.getDate("TerminationDate") + " | " + rs.getInt("Age"));

			System.out.println(
					"==================================================================================================================================================");
		}
		return "all employee shown ";

	}

	@Override
	public String AddEmployee(Employee employee) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate, Age) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, employee.getEmployeeID());
		ps.setString(2, employee.getFirstName());
		ps.setString(3, employee.getLastName());
		ps.setDate(4, employee.getDateOfBirth());
		ps.setString(5, employee.getGender());
		ps.setString(6, employee.getEmail());
		ps.setString(7, employee.getPhoneNumber());
		ps.setString(8, employee.getAddress());
		ps.setString(9, employee.getPosition());
		ps.setDate(10, employee.getJoiningDate());

		// handle null termination date
		if (employee.getTerminationDate() != null) {
			ps.setDate(11, employee.getTerminationDate());
		} else {
			ps.setNull(11, java.sql.Types.DATE);
		}

		ps.setInt(12, employee.calculateAge(employee.getDateOfBirth().toString()));

		int rows = ps.executeUpdate();
		if (rows > 0) {
			System.out.println("Employee inserted successfully.");
			return "Employee inserted successfully";
		}

		return "Employee not inserted successfully";
	}

	@Override
	public String updateEmployee(Employee employee) throws SQLException {

		if (searchEmployeeById(employee.getEmployeeID())) {

			String sql = "UPDATE Employee SET FirstName=?, LastName=?, DateOfBirth=?, Gender=?, Email=?, PhoneNumber=?, Address=?, Position=?, JoiningDate=?, TerminationDate=?, Age=? WHERE EmployeeID=?";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setDate(3, employee.getDateOfBirth());
			ps.setString(4, employee.getGender());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getPhoneNumber());
			ps.setString(7, employee.getAddress());
			ps.setString(8, employee.getPosition());
			ps.setDate(9, employee.getJoiningDate());

			// handle TerminationDate
			if (employee.getTerminationDate() != null) {
			    ps.setDate(10, employee.getTerminationDate());
			} else {
			    ps.setNull(10, java.sql.Types.DATE);
			}

			ps.setInt(11, employee.calculateAge(employee.getDateOfBirth().toString()));
			ps.setInt(12, employee.getEmployeeID()); // WHERE condition

			int rows = ps.executeUpdate();
			if (rows > 0) {
			    return "Employee updated successfully.";
			}

		}
		return new EmployeeException( "No employee found with that ID.").toString();

	}

	@Override
	public String RemoveEmployee(int employeeId) throws SQLException {		
		
		if(searchEmployeeById(employeeId)) {
			
			String sqlquery="DELETE FROM Employee WHERE EmployeeID = ?";
			
			pst=connection.prepareStatement(sqlquery);
			
			int isDelete=pst.executeUpdate();
			
			if(isDelete>0) {
				return "employee deleted with the id"+employeeId;
			}
			
		}
		return new EmployeeException("employee not found with this employee id"+employeeId).toString(); 
	}

	public boolean searchEmployeeById(int id) throws SQLException {
		String sql = "select * from Employee where EmployeeID=?";

		pst = connection.prepareStatement(sql);
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {

			System.out.println("EmployeeID: " + rs.getInt("EmployeeID"));
			System.out.println("FirstName: " + rs.getString("FirstName"));
			System.out.println("LastName: " + rs.getString("LastName"));
			System.out.println("DateOfBirth: " + rs.getDate("DateOfBirth"));
			System.out.println("Gender: " + rs.getString("Gender"));
			System.out.println("Email: " + rs.getString("Email"));
			System.out.println("PhoneNumber: " + rs.getString("PhoneNumber"));
			System.out.println("Address: " + rs.getString("Address"));
			System.out.println("Position: " + rs.getString("Position"));
			System.out.println("JoiningDate: " + rs.getDate("JoiningDate"));
			System.out.println("TerminationDate: " + rs.getDate("TerminationDate"));
			System.out.println("Age: " + rs.getInt("Age"));
			System.out.println("------------------------------------------");
			return true;
		}

		return false;
	}

}
