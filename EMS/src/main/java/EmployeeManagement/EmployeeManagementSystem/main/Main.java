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
import EmployeeManagement.EmployeeManagementSystem.exception.IFnancialRecordException;
import EmployeeManagement.EmployeeManagementSystem.exception.InvalidInputException;
import EmployeeManagement.EmployeeManagementSystem.exception.ItaxException;
import EmployeeManagement.EmployeeManagementSystem.exception.PayrollException;
import EmployeeManagement.EmployeeManagementSystem.model.Employee;
import EmployeeManagement.EmployeeManagementSystem.model.FinancialRecord;
import EmployeeManagement.EmployeeManagementSystem.model.Gender;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
import EmployeeManagement.EmployeeManagementSystem.model.Tax;
import EmployeeManagement.EmployeeManagementSystem.validation.EmployeeDaoValidation;
import EmployeeManagement.EmployeeManagementSystem.validation.IFinancialRecordDaoValidation;
import EmployeeManagement.EmployeeManagementSystem.validation.PayrollDaoValidation;
import EmployeeManagement.EmployeeManagementSystem.validation.TaxDaoValidation;

public class Main {
	static Scanner sc;
	static EmployeeDao dao;
	static IpayrollDao ipayrollDao;
	static ItaxDao itaxdao;
	static IFinancialRecordDao ifanDao;
	static Scanner scanner;
	static EmployeeDaoValidation employeeValidation;
	static PayrollDaoValidation ipayrolldaovalid;
	static TaxDaoValidation taxDaoValidation;
	static IFinancialRecordDaoValidation financialdaoValid;
	static {
		sc = new Scanner(System.in);
		dao = new EmployeeDaoImpl();
		ipayrollDao = new IpayrollDaoImpl();
		itaxdao = new ItaxDaoImpl();
		ifanDao = new IFinancialRecordDaoImpl();
		scanner = new Scanner(System.in);
		employeeValidation = new EmployeeDaoValidation();
		ipayrolldaovalid = new PayrollDaoValidation();
		taxDaoValidation = new TaxDaoValidation();
		financialdaoValid=new IFinancialRecordDaoValidation();
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
				} catch (SQLException | EmployeeException | InvalidInputException e) {
					System.err.println(e.getMessage());
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

				} catch (SQLException | EmployeeException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 5:

				try {
					// RemoveEmployee
					removeEmployeeByIdDaoValMain();
				} catch (SQLException | EmployeeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				try {
					// AddFinancialRecordDao
					AddFinancialRecordDaoValMain();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				
				try {
					// getFinanacialRecordByIdDao
					getFinancialRecordByEmployeeIDValMain();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 8:
				try {
					// GetFinancialRecordsForEmployeeDao
					getFinancialRecordByEmployeeIDValMain();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:
				try {
					// GetFinancialRecordsForDateDao
					getFinancialRecordByDateValMain();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 10:

				try {
					// GeneratePayrollDao
					GeneratePayrollDaoValMain();
				} catch (SQLException | EmployeeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 11:
				try {
					// GetPayrollByIdDao
					GetPayrollByIdDaoValMain();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (PayrollException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 12:
				try {
					// GetPayrollsForEmployeeDao
					GetPayrollsForEmployeeDaoValMain();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 13:
				try {
					// GetPayrollsForPeriodDao
					GetPayrollsForPeriodDaoValMain();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 14:
				try {
					// CalculateTax
					calculateTaxDaoValMain();
				} catch (SQLException | EmployeeException | ItaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 15:
				try {
					// GetTaxById
					getTaxByIdDaoValMain();
				} catch (SQLException | ItaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 16:
				try {
					// GetTaxesForEmployee
					getTaxesForEmployeeDaoValMain();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 17:
				try {
					// GetTaxesForYear
					getTaxesForYearDaoValMain();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 18:
				System.out.println("Exiting... Thank you!");
				System.exit(0);
			default:
				System.out.println("Invalid choice! Try again.");
			}
		}

	}

//	=============FINANTIAL CALCULATION===================

	public static void getFinancialRecordByDateValMain() throws SQLException {

		System.out.println("Enter date to know financial record YYYY-MM-DD :");
		String date = scanner.next();
		List<FinancialRecord> list = financialdaoValid.GetFinancialRecordsForDateDaoVal(Date.valueOf(date));
		for (FinancialRecord financialRecord : list) {
			System.out.println(financialRecord);
		}
	}

	public static void getFinancialRecordByEmployeeIDValMain() throws SQLException {

		System.out.println("Enter empId to know financial record :");
		int empId = scanner.nextInt();
		List<FinancialRecord> list = financialdaoValid.GetFinancialRecordsForEmployeeDaoVal(empId);

		for (FinancialRecord financialRecord : list) {
			System.out.println(financialRecord);
		}
	}

	public static void getFinancialRecordByFinancialIdValMain() throws SQLException, IFnancialRecordException {

		System.out.println("Enter financialId    to know financial record :");
		int ifId = scanner.nextInt();
		System.out.println(financialdaoValid.getFinanacialRecordByIdDaoVal(ifId));

	}

	public static void AddFinancialRecordDaoValMain() throws SQLException {

		 
         System.out.print("Enter Employee ID: ");
        int  employeeID = scanner.nextInt();
         scanner.nextLine(); // consume newline

         System.out.print("Enter Record Date (yyyy-MM-dd): ");
         String dateInput = scanner.nextLine();
       

         System.out.print("Enter Description: ");
         String description = scanner.nextLine();

         System.out.print("Enter Amount: ");
        Double  amount = scanner.nextDouble();
         

         System.out.print("Enter Record Type (e.g., 'Credit' or 'Debit'): ");
        String recordType = scanner.nextLine();
		
		System.out.println(financialdaoValid.AddFinancialRecordDaoVal(employeeID,description,amount,recordType));
	
	}

//==================TAX CALCULATION=================	
	public static void getTaxByIdDaoValMain() throws SQLException, ItaxException {
		System.out.println("Enter the taxId : ");
		int taxId = scanner.nextInt();
		System.out.println(taxDaoValidation.getTaxByIdDaoVal(taxId));
	}

	

	public static void getTaxesForYearDaoValMain() throws SQLException {

		System.out.println("Ente the Year to show tax yyyy");

		int  year = scanner.nextInt();

		List<Tax> list = taxDaoValidation.getTaxesForYearDaoVal(year);

		for (Tax tax : list) {
			System.out.println(tax);
		}

	}

	public static void getTaxesForEmployeeDaoValMain() throws SQLException {
		System.out.println("Enter the employeeId :");

		int employeeId = scanner.nextInt();

		List<Tax> list = taxDaoValidation.getTaxesForEmployeeDaoVal(employeeId);

		for (Tax tax : list) {
			System.out.println(tax);
		}

	}

	public static void calculateTaxDaoValMain() throws SQLException, EmployeeException, ItaxException {
		System.out.println("Enter the employeeId :");

		int employeeId = scanner.nextInt();
		System.out.println("Ente the Year to show tax  yyyy");

		int year = scanner.nextInt();
		System.out.println(taxDaoValidation.calculateTaxDaoVal(employeeId, year));
	}

	// ==============PAYROLL CALCULATION===============

	public static void GeneratePayrollDaoValMain() throws SQLException, EmployeeException {
		System.out.println("Enter the empId :");
		int employeeId = sc.nextInt();

		System.out.println("Enter the start date :");

		Date startDate = Date.valueOf(scanner.next());

		System.out.println("Enter the end  date :");

		Date endDate = Date.valueOf(scanner.next());

		System.out.println(ipayrolldaovalid.GeneratePayrollDaoVal(employeeId, startDate, endDate));
	}

	public static void GetPayrollByIdDaoValMain() throws SQLException, PayrollException {
		System.out.println("Enter the payroll Id : ");
		int payrollId = scanner.nextInt();
		System.out.println(ipayrolldaovalid.GetPayrollByIdDaoVal(payrollId));
	}

	public static void GetPayrollsForEmployeeDaoValMain() throws SQLException {
		System.out.println("Enter the employee Id");
		int employeeID = scanner.nextInt();
		List<Payroll> list = ipayrollDao.GetPayrollsForEmployeeDao(employeeID);
		for (Payroll payroll : list) {
			System.out.println(payroll);
		}
	}

	public static void GetPayrollsForPeriodDaoValMain() throws SQLException {
		System.out.println("Enter the start date :");

		Date startDate = Date.valueOf(scanner.next());

		System.out.println("Enter the end  date :");

		Date endDate = Date.valueOf(scanner.next());

		List<Payroll> list = ipayrollDao.GetPayrollsForPeriodDao(startDate, endDate);
		for (Payroll payroll : list) {
			System.out.println(payroll);
		}
	}

//=====EMPLOYEE METHODS ============

	public static void addEmployeeDaoValMain() throws SQLException, EmployeeException, InvalidInputException {

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
		employee.setGender(Gender.valueOf(sc.nextLine()));

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

	public static void updateEmployeeDaoValMain() throws SQLException, EmployeeException, InvalidInputException {

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
		employee.setGender(Gender.valueOf(sc.nextLine()));

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
