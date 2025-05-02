package EmployeeManagement.EmployeeManagementSystem.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Tax {

	private int taxId;

	private int employeeId;

	private int taxYear;
	
	private double taxableIncome;
	
	private double taxAmount;

}
