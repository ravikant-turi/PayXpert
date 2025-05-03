package EmployeeManagement.EmployeeManagementSystem.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.dao.IFinancialRecordDao;
import EmployeeManagement.EmployeeManagementSystem.exception.IFnancialRecordException;
import EmployeeManagement.EmployeeManagementSystem.model.FinancialRecord;
import EmployeeManagement.EmployeeManagementSystem.model.Payroll;
import EmployeeManagement.EmployeeManagementSystem.util.ConnectionHelper;

public class IFinancialRecordDaoImpl implements IFinancialRecordDao {

	static Payroll payroll;
	static Connection connection;
	static PreparedStatement pmst;
	static EmployeeDaoImpl employeeImpl;

	static {
		payroll = new Payroll();
		employeeImpl = new EmployeeDaoImpl();

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
	public String AddFinancialRecordDao(int empId, String description, Double amount, String recordType) throws SQLException {
		
		String sql= "insert into financialrecord (employeeId , description, amount ,recordType ,recordDate) values (?,?,?,?,?)";
		
		
		pmst=connection.prepareStatement(sql);
		
		pmst.setInt(1, empId);
		pmst.setString(2, description);
		
		pmst.setDouble(3, amount);
		
		pmst.setString(4, recordType);
		
		pmst.setDate(5, new Date(System.currentTimeMillis()));
		
		int isAdded=pmst.executeUpdate();
		
		if(isAdded==1) {
			return "financialRecord is added ";
		}

		return  new IFnancialRecordException("recorde is not added ").toString();
	}

	@Override
	public FinancialRecord getFinanacialRecordByIdDao(int recordId) throws SQLException {

		FinancialRecord financialRecord = null;

		String sql = "select * from financialrecord where recordId=?";

		pmst = connection.prepareStatement(sql);
		
		pmst.setInt(1, recordId);

		ResultSet rs = pmst.executeQuery();

		while (rs.next()) {
			financialRecord = new FinancialRecord();

			financialRecord.setAmount(rs.getDouble("Amount"));
			financialRecord.setDescription(rs.getString("description"));
			financialRecord.setEmployeeID(rs.getInt("EmployeeID"));
			financialRecord.setRecordDate(rs.getDate("RecordDate"));
			financialRecord.setRecordID(rs.getInt("RecordID"));
			financialRecord.setRecordType(rs.getString("RecordType"));

		}

		return financialRecord;
	}

	@Override
	public List<FinancialRecord> GetFinancialRecordsForEmployeeDao(int empId) throws SQLException {

		List<FinancialRecord> financialRecords = new ArrayList<FinancialRecord>();

		String sql = "select * from financialrecord where employeeId=?";

		pmst = connection.prepareStatement(sql);

		pmst.setInt(1, empId);
		ResultSet rs = pmst.executeQuery();

		while (rs.next()) {
			FinancialRecord financialRecord = new FinancialRecord();

			financialRecord.setAmount(rs.getDouble("Amount"));
			financialRecord.setDescription(rs.getString("description"));
			financialRecord.setEmployeeID(rs.getInt("EmployeeID"));
			financialRecord.setRecordDate(rs.getDate("RecordDate"));
			financialRecord.setRecordID(rs.getInt("RecordID"));
			financialRecord.setRecordType(rs.getString("RecordType"));
             
			financialRecords.add(financialRecord);
		}
		return financialRecords;
	}

	@Override
	public List<FinancialRecord> GetFinancialRecordsForDateDao(Date date) throws SQLException {

        List<FinancialRecord> financialRecords=new ArrayList<FinancialRecord>();
        
        
		
		String sql="select * from financialrecord where RecordDate=?";
		
		pmst=connection.prepareStatement(sql);
		
		pmst.setDate(1, date);
		
		ResultSet rs=pmst.executeQuery();
		
		while(rs.next()) {
			FinancialRecord  financialRecord=new FinancialRecord();
			
			financialRecord.setAmount(rs.getDouble("Amount"));
			financialRecord.setDescription(rs.getString("description"));
			financialRecord.setEmployeeID(rs.getInt("EmployeeID"));
			financialRecord.setRecordDate(rs.getDate("RecordDate"));
			financialRecord.setRecordID(rs.getInt("RecordID"));
			financialRecord.setRecordType(rs.getString("RecordType"));
			
           financialRecords.add(financialRecord);
		}
		return financialRecords;
	}

}
