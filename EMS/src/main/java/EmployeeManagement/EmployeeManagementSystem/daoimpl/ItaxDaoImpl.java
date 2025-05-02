package EmployeeManagement.EmployeeManagementSystem.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.dao.ItaxDao;
import EmployeeManagement.EmployeeManagementSystem.exception.EmployeeException;
import EmployeeManagement.EmployeeManagementSystem.model.Tax;
import EmployeeManagement.EmployeeManagementSystem.util.ConnectionHelper;

public class ItaxDaoImpl implements ItaxDao {

	static Tax tax;
	static Connection connection;
	static PreparedStatement pmst;
	static EmployeeDaoImpl employedaoimpl;
	static {
		tax = new Tax();
		employedaoimpl=new EmployeeDaoImpl();
		try {
			connection = new ConnectionHelper().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Tax getTaxById(int taxId) throws SQLException {
		// TODO Auto-generated method stub

		Tax tax = null;

		String sqlQuery = "select * from tax where taxId=?";

		pmst = connection.prepareStatement(sqlQuery);

		pmst.setInt(1, taxId);

		ResultSet rs = pmst.executeQuery();

		if (rs.next()) {
			tax = new Tax();

			tax.setTaxId(rs.getInt("TaxId"));
			tax.setEmployeeId(rs.getInt("employeeID"));
			tax.setTaxYear(rs.getInt("TaxYear"));
			tax.setTaxableIncome(rs.getDouble("TaxableIncome"));
			tax.setTaxAmount(rs.getDouble("TaxAmount"));

			System.out.println("------------------------------------------------");
		}
		return tax;
	}

	@Override
	public List<Tax> getTaxesForEmployee(int employeeId) throws SQLException {
		List<Tax> alltaxes = new ArrayList<>();

		String sql = "select * from tax where employeeId=?";

		pmst = connection.prepareStatement(sql);

		pmst.setInt(1, employeeId);

		ResultSet rs = pmst.executeQuery();

		while (rs.next()) {
			tax = new Tax();

			tax.setTaxId(rs.getInt("TaxId"));
			tax.setEmployeeId(rs.getInt("employeeID"));
			tax.setTaxYear(rs.getInt("TaxYear"));
			tax.setTaxableIncome(rs.getDouble("TaxableIncome"));
			tax.setTaxAmount(rs.getDouble("TaxAmount"));

			alltaxes.add(tax);
		}
		return alltaxes;
	}

	@Override
	public List<Tax> getTaxesForYear(int year) throws SQLException {
		List<Tax> alltaxes = new ArrayList<>();

		String sql = "select * from tax where taxyear=?";

		pmst = connection.prepareStatement(sql);

		pmst.setInt(1, year);

		ResultSet rs = pmst.executeQuery();

		while (rs.next()) {
			tax = new Tax();
            tax.setTaxId(rs.getInt("TaxId"));
			tax.setEmployeeId(rs.getInt("employeeID"));
			tax.setTaxYear(rs.getInt("TaxYear"));
			tax.setTaxableIncome(rs.getDouble("TaxableIncome"));
			tax.setTaxAmount(rs.getDouble("TaxAmount"));

			alltaxes.add(tax);
		}
		return alltaxes;
	}

	@Override
	public double calculateTax(int empId, int taxYear) throws SQLException {
		// TODO Auto-generated method stub
		
		if(employedaoimpl.searchEmployeeById(empId)){
			
			String sql="select * from payroll where employeeID=?";
			
			pmst=connection.prepareStatement(sql);
			
			pmst.setInt(1, empId);
			
			ResultSet rs=pmst.executeQuery();
			
			double netsalary=0;
			if(rs.next()) {
				netsalary=rs.getDouble("netsalary");
			}
			
			return netsalary*0.1;
			
		}
		else {
			System.out.println( new EmployeeException("employee not found at empid : "+ empId).toString());
			return 0;
		}
		
		
	}

}
