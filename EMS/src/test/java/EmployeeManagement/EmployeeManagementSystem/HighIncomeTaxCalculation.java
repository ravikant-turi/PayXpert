package EmployeeManagement.EmployeeManagementSystem;
import org.junit.jupiter.api.Test;

import EmployeeManagement.EmployeeManagementSystem.util.TaxCalCulator;

import static org.junit.jupiter.api.Assertions.*;
public class HighIncomeTaxCalculation {
	

	

	    @Test
	    public void testTaxResultValues() {
	        double expectedTaxableAmount = 800000.0;
	        double expectedTaxAmount = 85000.0;

	        TaxCalCulator.TaxResult result = new TaxCalCulator.TaxResult(expectedTaxableAmount, expectedTaxAmount);

	        assertEquals(expectedTaxableAmount, result.taxableAmount);
	        assertEquals(expectedTaxAmount, result.taxAmount);
	    }

	    @Test
	    public void testTaxResultToString() {
	        TaxCalCulator.TaxResult result = new TaxCalCulator.TaxResult(800000.0, 85000.0);
	        String expectedString = "Taxable Amount: ₹800000.0, Tax Amount: ₹85000.0";

	        assertEquals(expectedString, result.toString());
	    }
	

}
