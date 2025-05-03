package EmployeeManagement.EmployeeManagementSystem.validation;

import java.sql.Date;
import EmployeeManagement.EmployeeManagementSystem.exception.InvalidInputException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Pattern;

import EmployeeManagement.EmployeeManagementSystem.dao.EmployeeDao;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.EmployeeDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;

public class EmployeeDaoValidation {

	static EmployeeDao employeedao;
	static StringBuilder sb;

	static {
		employeedao = new EmployeeDaoImpl();
		sb=new StringBuilder();
	}

	public Employee getEmployeeByIdDaoVal(int employeeId) throws SQLException, EmployeeException {
		return employeedao.GetEmployeeByIdDao(employeeId);
	}

	public List<Employee> getAllEmployeeDaoVal() throws SQLException {
		return employeedao.GetAllEmployeesDao();
	}

	public String addEmployeeDaoVal(Employee employee) throws SQLException, EmployeeException, InvalidInputException {
		
		if(validateEmployeeInput(employee)) {
			
			return employeedao.addEmployeeDao(employee);
		}
		
		return new InvalidInputException(sb.toString()).toString();
	}

 	public String removeEmployeeDaoVal(int employeeId) throws SQLException, EmployeeException {
		return employeedao.removeEmployeeDao(employeeId);
	}

	public String updateEmployeeByIdDaoVal(Employee employee) throws SQLException, EmployeeException, InvalidInputException {
                if(validateEmployeeInput(employee)) {
			
			return employeedao.updateEmployeeDao(employee);
		}
		
		return new InvalidInputException(sb.toString()).toString();
		
	}

	public  boolean validateEmployeeInput(Employee employee) throws EmployeeException, InvalidInputException {
     
		if (employee == null) {
			sb.append("Employee object is null");
			
			return false;
			
		}

		if (isNullOrEmpty(employee.getFirstName())) {
			
			sb.append("First name is required.");
			return false;
		}

		if (isNullOrEmpty(employee.getLastName())) {
			sb.append("Last name is required.");
			return false;
		}

		if (employee.getDateOfBirth() == null) {
			
			sb.append("Date of birth is required.");
			return false;
		}

		if (employee.getDateOfBirth().after(new Date(System.currentTimeMillis()))) {
			
			sb.append("Date of birth cannot be in the future.");
			return false;
		}

		if (employee.getJoiningDate() == null) {
			
			sb.append("Joining date is required.");
			return false;
		}

		if (employee.getJoiningDate().after(new Date(System.currentTimeMillis()))) {
			
		   sb.append("Joining date cannot be in the future.");
		   return false;
		}

		if ( employee.getJoiningDate()!=null    &&   employee.getTerminationDate().before(employee.getJoiningDate())) {
			
			 sb.append("Termination date cannot be before joining date.");
			 return false;
		}

		if (isNullOrEmpty(employee.getEmail()) || !isValidEmail(employee.getEmail())) {
			
			 sb.append("Invalid email address.");
			 return false;
		}

		if (isNullOrEmpty(employee.getPhoneNumber()) || !isValidPhoneNumber(employee.getPhoneNumber())) {
			
			 sb.append("Invalid phone number.");
			 return false;
		}

		if (isNullOrEmpty(employee.getPosition())) {
			
			 sb.append("Position is required.");
			 return false;
		}
		if(isNullOrEmpty(employee.getAddress())) {
			
			 sb.append("address is null");
			 return false;
		}
		if(!employee.getGender().toString().equals("MALE") && !employee.getGender().toString().equals("FEMALE")) {
			
			 sb.append("Gender is invalid"+employee.getGender());
			 return false;
		}

		int age =employee.calculateAge(employee.getDateOfBirth().toString());
		if (age < 18 || age > 65) {
			
			 sb.append("Employee age must be between 18 and 65.");
			 return false;
		}

		// All checks passed
		
		return true;
	}

	private  boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	private  boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return Pattern.matches(regex, email);
	}

	private  boolean isValidPhoneNumber(String phone) {
		String regex = "\\d{10}";
		return Pattern.matches(regex, phone);
	}

	

}
