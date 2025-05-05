package EmployeeManagement.EmployeeManagementSystem.util;

public class TaxCalCulator {
	

	    public static class TaxResult {
	        public double taxableAmount;
	        public double taxAmount;

	        public TaxResult(double taxableAmount, double taxAmount) {
	            this.taxableAmount = taxableAmount;
	            this.taxAmount = taxAmount;
	        }

	        @Override
	        public String toString() {
	            return "Taxable Amount: ₹" + taxableAmount + ", Tax Amount: ₹" + taxAmount;
	        }
	    }

	    public TaxResult calculateTax(double income) {
	        double tax = 0.0;
	        double taxable = income > 200000 ? income - 200000 : 0;

	        if (income <= 200000) {
	            tax = 0;
	        } else if (income <= 500000) {
	            tax = (income - 200000) * 0.05;
	        } else if (income <= 1000000) {
	            tax = (300000 * 0.05) + (income - 500000) * 0.10;
	        } else {
	            tax = (300000 * 0.05) + (500000 * 0.10) + (income - 1000000) * 0.20;
	        }

	        return new TaxResult(taxable, tax);
	    }
	

}
