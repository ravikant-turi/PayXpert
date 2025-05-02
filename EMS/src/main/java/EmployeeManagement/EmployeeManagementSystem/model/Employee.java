package EmployeeManagement.EmployeeManagementSystem.model;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
@Getter
@Setter
@Data
public class Employee {
	private int employeeID;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String gender;
	private String email;
	private String phoneNumber;
	private String address;
	private String position;
	private Date joiningDate;
	private Date terminationDate;
	private int age;
	
	
	public int calculateAge(String dob) {
		
		 Date sqlDate = Date.valueOf(dob); // yyyy-mm-dd
		 
		LocalDate localDate = sqlDate.toLocalDate();

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Calculate age
        return Period.between(localDate, currentDate).getYears();
		
	}
	
	

}
