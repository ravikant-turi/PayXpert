package EmployeeManagement.EmployeeManagementSystem.validation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.dao.EmployeeDao;
import EmployeeManagement.EmployeeManagementSystem.dao.IpayrollDao;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.EmployeeDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.IpayrollDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.exception.PayrollException;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;

public class PayrollDaoValidation {
	static EmployeeDao employeedao;
	static IpayrollDao payrollDao;
	static StringBuilder sb;

	static {
		employeedao = new EmployeeDaoImpl();
		payrollDao=new  IpayrollDaoImpl();
		sb=new StringBuilder();
	}
	
	
	public String GeneratePayrollDaoVal(int employeeId,Date startDate,Date endDate) throws SQLException, EmployeeException{
		
		return payrollDao.GeneratePayrollDao(employeeId, startDate, endDate);
	}
	
	
	public Payroll GetPayrollByIdDaoVal(int payrollId) throws SQLException, PayrollException{
		 return payrollDao.GetPayrollByIdDao(payrollId);
	 }
	 
	 
	public List<Payroll> GetPayrollsForEmployeeDaoVal(int employeeID) throws SQLException{
		return payrollDao.GetPayrollsForEmployeeDao(employeeID);
	}
	 
	
	public List<Payroll> GetPayrollsForPeriodDaoVal(Date startDate, Date endDate) throws SQLException{
		return payrollDao.GetPayrollsForPeriodDao(startDate, endDate);
	}
	
}
