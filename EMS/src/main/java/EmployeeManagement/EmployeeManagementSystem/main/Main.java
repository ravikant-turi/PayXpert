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
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;
import EmployeeManagement.EmployeeManagementSystem.model.FinancialRecord;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
import EmployeeManagement.EmployeeManagementSystem.model.Tax;
import EmployeeManagement.EmployeeManagementSystem.validation.EmployeeDaoValidation;

public class Main {
	static Scanner sc;
	static EmployeeDao dao;
	static IpayrollDao payDao;
	static ItaxDao itaxdao;
	static IFinancialRecordDao ifanDao;
	static Scanner scanner;
	static EmployeeDaoValidation employeeValidation;

	static {
		sc = new Scanner(System.in);
		dao = new EmployeeDaoImpl();
		payDao = new IpayrollDaoImpl();
		itaxdao = new ItaxDaoImpl();
		ifanDao = new IFinancialRecordDaoImpl();
		scanner = new Scanner(System.in);
		employeeValidation = new EmployeeDaoValidation();
	}

	public static void main(String[] args) {

		int choice = 0;

		while (true) {
			System.out.println("\n============= payXpert Menu ============");
			System.out.println("1. Add Employee");
			System.out.println("2. Get Employee by ID");
			System.out.println("3. Get All Employees");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");
			System.out.println("6. Add Financial Record");
			System.out.println("7. Get Financial Record by ID");
			System.out.println("8. Get Financial Records for Employee");
			System.out.println("9. Get Financial Records for Date");
			System.out.println("10. Generate Payroll");
			System.out.println("11. Get Payroll by ID");
			System.out.println("12. Get Payrolls for Employee");
			System.out.println("13. Get Payrolls for Period");
			System.out.println("14. Calculate Tax");
			System.out.println("15. Get Tax by ID");
			System.out.println("16. Get Taxes for Employee");
			System.out.println("17. Get Taxes for Year");
			System.out.println("18. Exit");
			System.out.print("Enter your choice: ");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				try {
					// AddEmployee
					addEmployeeDaoValMain();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:

				try {
					// GetEmployeeByIdDao
					getEmployeeByIdDaoValMain();
				} catch (SQLException | EmployeeException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 3:

				try {
					// GetAllEmployees
					getAllEmployeeDaoValMain();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 4:
				try {
					// UpdateEmployee
					updateEmployeeDaoValMain();
				} catch (SQLException | EmployeeException e) {
					System.err.println(e.getMessage());
				}

				break;
			case 5:

				try {
					// RemoveEmployee
					removeEmployeeByIdDaoValMain();
				} catch (SQLException | EmployeeException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 6:
				// AddFinancialRecordDao
				break;
			case 7:
				// getFinanacialRecordByIdDao
				break;
			case 8:
				// GetFinancialRecordsForEmployeeDao
				break;
			case 9:
				// GetFinancialRecordsForDateDao
				break;
			case 10:
				// GeneratePayrollDao
				break;
			case 11:
				// GetPayrollByIdDao
				break;
			case 12:
				// GetPayrollsForEmployeeDao
				break;
			case 13:
				// GetPayrollsForPeriodDao
				break;
			case 14:
				// CalculateTax
				break;
			case 15:
				// GetTaxById
				break;
			case 16:
				// GetTaxesForEmployee
				break;
			case 17:
				// GetTaxesForYear
				break;
			case 18:
				System.out.println("Exiting... Thank you!");
				System.exit(0);
			default:
				System.out.println("Invalid choice! Try again.");
			}
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

	public static void addEmployeeDaoValMain() throws SQLException {

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

		System.out.println(employeeValidation.addEmployeeDaoVal(employee));
	}

	public static void updateEmployeeDaoValMain() throws SQLException, EmployeeException {

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

		System.out.println(employeeValidation.updateEmployeeByIdDaoVal(employee));
	}

	public static void getEmployeeByIdDaoValMain() throws SQLException, EmployeeException {

		System.out.println("Enter the  EmployeeId: ");

		int employeeId = scanner.nextInt();

		System.out.println(employeeValidation.getEmployeeByIdDaoVal(employeeId));
	}

	public static void getAllEmployeeDaoValMain() throws SQLException {
		List<Employee> list = employeeValidation.getAllEmployeeDaoVal();

		System.out.println("employee are : ");
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	public static void removeEmployeeByIdDaoValMain() throws SQLException, EmployeeException {
		System.out.println("Enter the employee ID :");
		int employeeId = scanner.nextInt();
		employeeValidation.removeEmployeeDaoVal(employeeId);
	}
}
