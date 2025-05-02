package EmployeeManagement.EmployeeManagementSystem.daoimpl;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import EmployeeManagement.EmployeeManagementSystem.dao.IpayrollDao;
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.exception.PayrollException;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
import EmployeeManagement.EmployeeManagementSystem.util.ConnectionHelper;

public class IpayrollDaoImpl implements IpayrollDao {
	static Payroll payroll;
	static Connection connection;
	static PreparedStatement pst;
	static EmployeeDaoImpl employeeImpl;

	static {
		payroll = new Payroll();
		employeeImpl = new EmployeeDaoImpl();

		try {
			connection = new ConnectionHelper().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String GeneratePayrollDao(int employeeId, Date startDate, Date endDate) throws SQLException {

		if (!employeeImpl.searchEmployeeById(employeeId)) {
			return new EmployeeException("employee not found").toString();
		}

		Double basicSalary = 300000.0;

		Double overtimePay = 10000.0;

		Double deductions = 20000.0;

		Double netSalary = basicSalary + overtimePay - deductions;

		String sql = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";

		pst = connection.prepareStatement(sql);
		pst.setInt(1, employeeId);
		pst.setDate(2, startDate);
		pst.setDate(3, endDate);
		pst.setDouble(4, basicSalary);
		pst.setDouble(5, overtimePay);
		pst.setDouble(6, deductions);
		pst.setDouble(7, netSalary);

		int rows = pst.executeUpdate();
		if (rows > 0) {
			return "Payroll generated successfully.";
		}

		return new PayrollException("payroll not generated").toString();
	}

	@Override
	public String GetPayrollByIdDao(int payrollId) throws SQLException {

		String sqlQuery = "SELECT * FROM PAYROLL WHERE payrollId=?";
		pst = connection.prepareStatement(sqlQuery);
		pst.setInt(1, payrollId);
		ResultSet rs = pst.executeQuery();

//         if(!rs.next()) {
//			
//			return new PayrollException("payroll is not found at this id "+ payrollId).toString();
//		}
		System.out.println("result set is :" + rs);

		if (rs.next()) {

			System.out.println("payroll_id is : " + rs.getInt("payrollId"));
			System.out.println("payroll_employeeID : " + rs.getInt("employeeID"));
			System.out.println("payPeriodStartDate :" + rs.getDate("payPeriodStartDate"));
			System.out.println("payPeriodEndDate : " + rs.getDate("payPeriodEndDate"));
			System.out.println("basicSalary : " + rs.getDouble("basicSalary"));
			System.out.println("overtimePay : " + rs.getDouble("overtimePay"));
			System.out.println("deductions : " + rs.getInt("deductions"));
			System.out.println("netSalary : " + rs.getInt("netSalary"));
			System.out.println("=============================================================");
			return "payroll by payrollID is called ";

		}

		return new PayrollException("payroll is not found at this id " + payrollId).toString();
	}

	@Override
	public List<Payroll> GetPayrollsForEmployeeDao(int employeeID) throws SQLException {

		List<Payroll> payrollList = new ArrayList<>();
		String sqlString = "select * from payroll  where employeeID=?";

		pst = connection.prepareStatement(sqlString);
		pst.setInt(1, employeeID);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {

			Payroll payroll = new Payroll();
			payroll.setPayrollID(rs.getInt("payrollId"));
			payroll.setEmployeeID(rs.getInt("employeeID"));
			payroll.setPayPeriodStartDate(rs.getDate("payPeriodStartDate"));
			payroll.setPayPeriodEndDate(rs.getDate("payPeriodEndDate"));
			payroll.setBasicSalary(rs.getDouble("basicSalary"));
			payroll.setOvertimePay(rs.getDouble("overtimePay"));
			payroll.setDeductions(rs.getInt("deductions"));
			payroll.setNetSalary(rs.getInt("netSalary"));

			payrollList.add(payroll);
		}
	    new PayrollException("payroll not found at employee id " + employeeID).toString();
	    return payrollList;
	}

	@Override
	public String GetPayrollsForPeriodDao(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
