package EmployeeManagement.EmployeeManagementSystem.daoimpl;

import EmployeeManagement.EmployeeManagementSystem.util.*;
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
import EmployeeManagement.EmployeeManagementSystem.exception.ItaxException;
import EmployeeManagement.EmployeeManagementSystem.model.Tax;
import EmployeeManagement.EmployeeManagementSystem.util.ConnectionHelper;

public class ItaxDaoImpl implements ItaxDao {

	static Tax tax;
	static Connection connection;
	static PreparedStatement pmst;
	static EmployeeDaoImpl employedaoimpl;

	static {
		tax = new Tax();
		employedaoimpl = new EmployeeDaoImpl();
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
	public Tax getTaxByIdDao(int taxId) throws SQLException, ItaxException {

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
		} else {
			throw new ItaxException("tax id not found ");
		}
		return tax;
	}

	@Override
	public List<Tax> getTaxesForEmployeeDao(int employeeId) throws SQLException {
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
	public List<Tax> getTaxesForYearDao(int year) throws SQLException {
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
	public double calculateTaxDao(int empId, int taxYear) throws SQLException, EmployeeException, ItaxException {

		double taxAmount = 0;
		if (employedaoimpl.GetEmployeeByIdDao(empId) != null) {

			String sql = "select * from payroll where employeeID=?";

			pmst = connection.prepareStatement(sql);

			pmst.setInt(1, empId);

			ResultSet rs = pmst.executeQuery();
			
			
			double Netsalary = 0;
			
			if (rs.next()) {

				Netsalary = rs.getDouble("netsalary");
				TaxCalCulator taxCalculator = new TaxCalCulator();
				TaxCalCulator.TaxResult result = taxCalculator.calculateTax(Netsalary);
				taxAmount = result.taxableAmount;
				if (helpcalculateTaxDao(empId, taxYear, result.taxableAmount, result.taxAmount) > 0) {
					return Netsalary;
				} else {
					throw new ItaxException("tax is not able calculated :");
				}
			}

		} else {
			throw new EmployeeException("employee not found at empid : " + empId);

		}
		return taxAmount;

	}

	public int helpcalculateTaxDao(int employeeId, int taxYear, Double taxableIncome, Double taxAmount)
			throws SQLException {

		String sql = "INSERT INTO tax (EmployeeID, TAXYEAR, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)";

		pmst = connection.prepareStatement(sql);

		pmst.setInt(1, employeeId);
		pmst.setInt(2, taxYear);
		pmst.setDouble(3, taxableIncome);
		pmst.setDouble(4, taxAmount);

		return pmst.executeUpdate();

	}

}
