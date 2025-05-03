package EmployeeManagement.EmployeeManagementSystem.dao;

import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;

public interface EmployeeDao {

	
//	• GetEmployeeById(employeeId)
	
	Employee GetEmployeeByIdDao(int employeeId) throws SQLException, EmployeeException;
//	• GetAllEmployees()
	List<Employee> GetAllEmployeesDao() throws SQLException;
//	• AddEmployee(employeeData)
	String addEmployeeDao(Employee employee) throws SQLException;
//	• UpdateEmployee(employeeData)
	
	String updateEmployeeDao(Employee employee) throws SQLException, EmployeeException;
//	• RemoveEmployee(employeeId)
	String removeEmployeeDao(int employeeId) throws SQLException, EmployeeException;
}
