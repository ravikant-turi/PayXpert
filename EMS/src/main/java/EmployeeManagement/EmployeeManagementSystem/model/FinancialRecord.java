package EmployeeManagement.EmployeeManagementSystem.model;

import java.sql.Date;

import lombok.Data;

@Data

public class FinancialRecord {

	private int recordID;
	private int employeeID;
	private Date recordDate;
	private String description;
	private double amount;
	private String recordType;

}
