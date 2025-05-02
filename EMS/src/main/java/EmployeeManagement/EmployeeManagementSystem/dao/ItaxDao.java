package EmployeeManagement.EmployeeManagementSystem.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.model.Tax;

public interface ItaxDao {
	
	
//	CalculateTax(employeeId, taxYear)
//	• GetTaxById(taxId)
//	• GetTaxesForEmployee(employeeId)
//	• GetTaxesForYear(taxYear)
	    double calculateTax(int empId,int taxYear) throws SQLException;
	    Tax getTaxById(int taxId) throws SQLException;
	    List<Tax> getTaxesForEmployee(int employeeId) throws SQLException;
	    List<Tax> getTaxesForYear(int year) throws SQLException;
	

}
