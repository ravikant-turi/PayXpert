package EmployeeManagement.EmployeeManagementSystem.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.exception.IFnancialRecordException;
import EmployeeManagement.EmployeeManagementSystem.model.FinancialRecord;

public interface IFinancialRecordDao {
	
//	IFinancialRecordService:
//		• AddFinancialRecord(employeeId, description, amount, recordType)
	
	String AddFinancialRecordDao(int empId,String description,Double amount,String recordType) throws SQLException ;
	    
	
//		• GetFinancialRecordById(recordId)
	
	FinancialRecord getFinanacialRecordByIdDao(int recordId) throws SQLException, IFnancialRecordException;
	
//		• GetFinancialRecordsForEmployee(employeeId)
	
	List<FinancialRecord> GetFinancialRecordsForEmployeeDao(int empId) throws SQLException;
	
//		• GetFinancialRecordsForDate(recordDate)
	
	List<FinancialRecord> GetFinancialRecordsForDateDao(Date date) throws SQLException;

}
