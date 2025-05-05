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
	public String GeneratePayrollDao(int employeeId, Date startDate, Date endDate) throws SQLException, EmployeeException {

		if (employeeImpl.GetEmployeeByIdDao(employeeId)!=null) {
			return new EmployeeException("employee not found").toString();
		}

		Double basicSalary = 30000.0;

		Double overtimePay = 1000.0;

		Double deductions = basicSalary * 0.1;

		Double netSalary = 0.0;
		
		netSalary=calculateNetSalary(basicSalary , overtimePay , deductions);

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
	public Payroll GetPayrollByIdDao(int payrollId) throws SQLException, PayrollException {

		Payroll payroll = null;

		String sqlQuery = "SELECT * FROM PAYROLL WHERE payrollId=?";
		pst = connection.prepareStatement(sqlQuery);
		pst.setInt(1, payrollId);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			payroll = new Payroll();
			payroll.setPayrollID(rs.getInt("payrollId"));
			payroll.setEmployeeID(rs.getInt("employeeID"));
			payroll.setPayPeriodStartDate(rs.getDate("payPeriodStartDate"));
			payroll.setPayPeriodEndDate(rs.getDate("payPeriodEndDate"));
			payroll.setBasicSalary(rs.getDouble("basicSalary"));
			payroll.setOvertimePay(rs.getDouble("overtimePay"));
			payroll.setDeductions(rs.getInt("deductions"));
			payroll.setNetSalary(rs.getInt("netSalary"));

		} else {

			throw new PayrollException("payroll is not found at this id " + payrollId);

		}
		return payroll;

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
	public List<Payroll> GetPayrollsForPeriodDao(Date startDate, Date endDate) throws SQLException {
		// TODO Auto-generated method stub
		List<Payroll> payrolls = new ArrayList<>();
		String sql = "SELECT * FROM PAYROLL WHERE payPeriodStartDate >= ? AND payPeriodEndDate <= ?";

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setDate(1, startDate);
		pst.setDate(2, endDate);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Payroll payroll = new Payroll();
			payroll.setPayrollID(rs.getInt("payrollId"));
			payroll.setEmployeeID(rs.getInt("employeeID"));
			payroll.setPayPeriodStartDate(rs.getDate("payPeriodStartDate"));
			payroll.setPayPeriodEndDate(rs.getDate("payPeriodEndDate"));
			payroll.setBasicSalary(rs.getDouble("basicSalary"));
			payroll.setOvertimePay(rs.getDouble("overtimePay"));
			payroll.setDeductions(rs.getDouble("deductions"));
			payroll.setNetSalary(rs.getDouble("netSalary"));

			payrolls.add(payroll);
		}

		return payrolls;
	}
	public double calculateNetSalary(double basicSalary, double overtimePay,double deduction) {
        return basicSalary + overtimePay - deduction;
    }

}
