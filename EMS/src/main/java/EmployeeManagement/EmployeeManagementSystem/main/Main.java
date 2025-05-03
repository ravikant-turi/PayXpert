package EmployeeManagement.EmployeeManagementSystem.main;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

import EmployeeManagement.EmployeeManagementSystem.dao.EmployeeDao;
import EmployeeManagement.EmployeeManagementSystem.dao.IFinancialRecordDao;
import EmployeeManagement.EmployeeManagementSystem.dao.IpayrollDao;
import EmployeeManagement.EmployeeManagementSystem.dao.ItaxDao;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.EmployeeDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.IFinancialRecordDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.IpayrollDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.ItaxDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;
import EmployeeManagement.EmployeeManagementSystem.model.FinancialRecord;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
import EmployeeManagement.EmployeeManagementSystem.model.Tax;

public class Main {
	static Scanner sc;
	static EmployeeDao dao;
	static IpayrollDao payDao;
	static IFinancialRecordDao ifanDao;
	static Scanner scanner;
	static ItaxDao itaxdao;

	static {
		sc = new Scanner(System.in);
		dao = new EmployeeDaoImpl();
		payDao = new IpayrollDaoImpl();
		itaxdao = new ItaxDaoImpl();
		ifanDao = new IFinancialRecordDaoImpl();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {

		EmployeeDao emp = new EmployeeDaoImpl();

		System.out.println("Welcome to the updated code ");

		System.out.println("Welcome");

		try {
//		System.out.println( emp.GetAllEmployees());
//		System.out.println(emp.GetEmployeeByIdDao(22));

//		addEmployeeDaoMain();

//		updateEmployeeDaoMain();

//		System.out.println(payDao.GeneratePayrollDao(20, Date.valueOf("2025-10-10"), Date.valueOf("2025-10-25")));

//		System.out.println(payDao.GetPayrollByIdDao(22));

//		showAllPayrollByEmpID();

//			showAllTaxByEmpId();

//			showAllTaxByYear();

//			calculateTaxMain();
			getFinancialRecordByEmployeeIDValMain();
			getFinancialRecordByFinancialIdValMain();
			getFinancialRecordByDateValMain();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getFinancialRecordByDateValMain() throws SQLException {

		System.out.println("Enter date to know financial record :");
		String date = scanner.next();
		List<FinancialRecord> list = ifanDao.GetFinancialRecordsForDateDao(Date.valueOf(date));
		for (FinancialRecord financialRecord : list) {
			System.out.println(financialRecord);
		}
	}

	public static void getFinancialRecordByEmployeeIDValMain() throws SQLException {

		System.out.println("Enter empId to know financial record :");
		int empId = scanner.nextInt();
		List<FinancialRecord> list = ifanDao.GetFinancialRecordsForEmployeeDao(empId);

		for (FinancialRecord financialRecord : list) {
			System.out.println(financialRecord);
		}
	}

	public static void getFinancialRecordByFinancialIdValMain() throws SQLException {

		System.out.println("Enter financialId    to know financial record :");
		int ifId = scanner.nextInt();
		System.out.println(ifanDao.getFinanacialRecordByIdDao(ifId));
		
	}

	public static void AddFinancialRecordDaoValMain() {
//		ifanDao.AddFinancialRecordDao(2, null, null, null)
	}

	public static void calculateTaxMain() throws SQLException {

		System.out.println(itaxdao.calculateTax(20, Year.parse("2025").getValue()));
	}

	public static void showAllTaxByYear() throws SQLException {

		List<Tax> list = itaxdao.getTaxesForYear(Year.parse("2025").getValue());

		for (Tax tax : list) {
			System.out.println(tax);
		}

	}

	public static void showAllTaxByEmpId() throws SQLException {

		List<Tax> list = itaxdao.getTaxesForEmployee(1);

		for (Tax tax : list) {
			System.out.println(tax);
		}

	}

	public static void showAllPayrollByEmpID() throws SQLException {
		List<Payroll> list = payDao.GetPayrollsForEmployeeDao(2);

		for (Payroll payroll : list) {
			System.out.println(payroll);
		}
	}

	public static void addEmployeeDaoMain() throws SQLException {

		Employee employee = new Employee();

		System.out.print("Enter Employee ID: ");
		employee.setEmployeeID(sc.nextInt());
		sc.nextLine(); // consume newline

		System.out.print("Enter First Name: ");
		employee.setFirstName(sc.nextLine());

		System.out.print("Enter Last Name: ");
		employee.setLastName(sc.nextLine());

		System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
		employee.setDateOfBirth(Date.valueOf(sc.nextLine()));

		System.out.print("Enter Gender: ");
		employee.setGender(sc.nextLine());

		System.out.print("Enter Email: ");
		employee.setEmail(sc.nextLine());

		System.out.print("Enter Phone Number: ");
		employee.setPhoneNumber(sc.nextLine());

		System.out.print("Enter Address: ");
		employee.setAddress(sc.nextLine());

		System.out.print("Enter Position: ");
		employee.setPosition(sc.nextLine());

		System.out.print("Enter Joining Date (yyyy-mm-dd): ");
		employee.setJoiningDate(Date.valueOf(sc.nextLine()));

		System.out.print("Enter Termination Date (yyyy-mm-dd) or press Enter if still working: ");
		String terminationInput = sc.nextLine();
		if (!terminationInput.isEmpty()) {
			employee.setTerminationDate(Date.valueOf(terminationInput));
		} else {
			employee.setTerminationDate(null);
		}

		// insert employee
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		System.out.println(dao.AddEmployee(employee));
	}

	public static void updateEmployeeDaoMain() throws SQLException {

		Employee employee = new Employee();
		System.out.print("Enter Employee ID to update: ");
		employee.setEmployeeID(sc.nextInt());
		sc.nextLine(); // consume newline

		System.out.print("Enter First Name: ");
		employee.setFirstName(sc.nextLine());

		System.out.print("Enter Last Name: ");
		employee.setLastName(sc.nextLine());

		System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
		employee.setDateOfBirth(Date.valueOf(sc.nextLine()));

		// calculate age
		int age = Period.between(employee.getDateOfBirth().toLocalDate(), LocalDate.now()).getYears();
		employee.setAge(age);

		System.out.print("Enter Gender: ");
		employee.setGender(sc.nextLine());

		System.out.print("Enter Email: ");
		employee.setEmail(sc.nextLine());

		System.out.print("Enter Phone Number: ");
		employee.setPhoneNumber(sc.nextLine());

		System.out.print("Enter Address: ");
		employee.setAddress(sc.nextLine());

		System.out.print("Enter Position: ");
		employee.setPosition(sc.nextLine());

		System.out.print("Enter Joining Date (yyyy-mm-dd): ");
		employee.setJoiningDate(Date.valueOf(sc.nextLine()));

		System.out.print("Enter Termination Date (yyyy-mm-dd) or press Enter if still working: ");
		String terminationInput = sc.nextLine();
		if (!terminationInput.isEmpty()) {
			employee.setTerminationDate(Date.valueOf(terminationInput));
		} else {
			employee.setTerminationDate(null);
		}

		// update employee
		EmployeeDaoImpl dao = new EmployeeDaoImpl();
		System.out.println(dao.updateEmployee(employee));

	}
}
