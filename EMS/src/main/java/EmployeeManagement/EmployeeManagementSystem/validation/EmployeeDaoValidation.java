package EmployeeManagement.EmployeeManagementSystem.validation;

import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.dao.EmployeeDao;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.EmployeeDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;

public class EmployeeDaoValidation {
	
	
	static EmployeeDao employeedao;
	
	static {
		employeedao=new EmployeeDaoImpl();
	}
	
	public Employee getEmployeeByIdDaoVal(int employeeId) throws SQLException, EmployeeException {
		return employeedao.GetEmployeeByIdDao(employeeId);
	}
	
	public List<Employee>  getAllEmployeeDaoVal() throws SQLException {
		return employeedao.GetAllEmployeesDao();
	}
	
	public String addEmployeeDaoVal(Employee employee) throws SQLException {
		return employeedao.addEmployeeDao(employee);
	}
	
	public String removeEmployeeDaoVal(int employeeId) throws SQLException, EmployeeException {
		return employeedao.removeEmployeeDao(employeeId);
	}
    public String updateEmployeeByIdDaoVal(Employee employee) throws SQLException, EmployeeException {
    	return employeedao.updateEmployeeDao(employee);
    }

}
