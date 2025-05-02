package EmployeeManagement.EmployeeManagementSystem.model;

import java.sql.Date;
import lombok.Data;

@Data
public class Payroll {
	
	private int payrollID;
	
	private int employeeID;
	
	private Date payPeriodStartDate;
	
	private Date payPeriodEndDate;
	
	private double basicSalary;
	
	private double overtimePay;
	
	private double deductions;
	
	private double netSalary;
	 
}
