package EmployeeManagement.EmployeeManagementSystem.dao;

import java.sql.SQLException;

import EmployeeManagement.EmployeeManagementSystem.model.Employee;

public interface EmployeeDao {

	
//	• GetEmployeeById(employeeId)
	
	String GetEmployeeByIdDao(int employeeId) throws SQLException;
//	• GetAllEmployees()
	String GetAllEmployees() throws SQLException;
//	• AddEmployee(employeeData)
	String AddEmployee(Employee employee) throws SQLException;
//	• UpdateEmployee(employeeData)
	
	String updateEmployee(Employee employee) throws SQLException;
//	• RemoveEmployee(employeeId)
	String RemoveEmployee(int employeeId) throws SQLException;
}
