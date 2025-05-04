package EmployeeManagement.EmployeeManagementSystem.dao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.exception.PayrollException;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
public interface IpayrollDao {
	
	
//	GeneratePayroll(employeeId, startDate, endDate)
	String GeneratePayrollDao(int employeeId,Date startDate,Date endDate) throws SQLException, EmployeeException;
	
	
//	• GetPayrollById(payrollId)
	 Payroll GetPayrollByIdDao(int payrollId) throws SQLException, PayrollException;
	 
	 
//	• GetPayrollsForEmployee(employeeId)
	List<Payroll> GetPayrollsForEmployeeDao(int employeeID) throws SQLException;
	
	
//	• GetPayrollsForPeriod(startDate, endDate)
	List<Payroll> GetPayrollsForPeriodDao(Date startDate, Date endDate) throws SQLException;
	
	
	 
}
