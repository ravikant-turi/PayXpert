package EmployeeManagement.EmployeeManagementSystem.dao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
public interface IpayrollDao {
//	GeneratePayroll(employeeId, startDate, endDate)
	
	String GeneratePayrollDao(int employeeId,Date startDate,Date endDate) throws SQLException;
//	• GetPayrollById(payrollId)
	
	String GetPayrollByIdDao(int payrollId) throws SQLException;
//	• GetPayrollsForEmployee(employeeId)
	List<Payroll> GetPayrollsForEmployeeDao(int employeeID) throws SQLException;
//	• GetPayrollsForPeriod(startDate, endDate)
	
	String GetPayrollsForPeriodDao(Date startDate, Date endDate);
	
	
}
