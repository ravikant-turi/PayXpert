package EmployeeManagement.EmployeeManagementSystem.validation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import EmployeeManagement.EmployeeManagementSystem.dao.IFinancialRecordDao;
import EmployeeManagement.EmployeeManagementSystem.dao.ItaxDao;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.IFinancialRecordDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.daoimpl.ItaxDaoImpl;
import EmployeeManagement.EmployeeManagementSystem.exception.IFnancialRecordException;
import EmployeeManagement.EmployeeManagementSystem.model.FinancialRecord;
import EmployeeManagement.EmployeeManagementSystem.model.Tax;

public class IFinancialRecordDaoValidation {
	static Tax tax;
	static ItaxDao taxdao;
	static FinancialRecord financialRecord;
    static IFinancialRecordDao iFinancialRecordDao;
	static {
		tax = new Tax();
		taxdao = new ItaxDaoImpl();
		financialRecord=new  FinancialRecord();
		iFinancialRecordDao=new IFinancialRecordDaoImpl();
	}

	public String AddFinancialRecordDaoVal(int empId, String description, Double amount, String recordType)
			throws SQLException {
		
		return iFinancialRecordDao.AddFinancialRecordDao(empId, description, amount, recordType);

	}



    public FinancialRecord getFinanacialRecordByIdDaoVal(int recordId) throws SQLException, IFnancialRecordException{
	    return iFinancialRecordDao.getFinanacialRecordByIdDao(recordId);
    }


    public List<FinancialRecord> GetFinancialRecordsForEmployeeDaoVal(int empId) throws SQLException{
	    return iFinancialRecordDao.GetFinancialRecordsForEmployeeDao(empId);
    }


    public List<FinancialRecord> GetFinancialRecordsForDateDaoVal(Date date) throws SQLException{
	  return iFinancialRecordDao.GetFinancialRecordsForDateDao(date);
     }


}
