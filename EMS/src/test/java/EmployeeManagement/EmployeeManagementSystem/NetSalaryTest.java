package EmployeeManagement.EmployeeManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import EmployeeManagement.EmployeeManagementSystem.daoimpl.IpayrollDaoImpl;

public class NetSalaryTest {

   private	IpayrollDaoImpl ipayrolldao;
	
	@Test
	public void testNetSalaryGeneration() {
		
		ipayrolldao=new IpayrollDaoImpl();
		
		Double basicSalary = 30000.0;

		Double overtimePay = 1000.0;

		Double deductions = basicSalary * 0.1;
		
		assertEquals(28000.0, ipayrolldao.calculateNetSalary(basicSalary, overtimePay, deductions));
	}
	
}
