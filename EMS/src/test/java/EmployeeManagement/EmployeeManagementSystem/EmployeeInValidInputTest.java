package EmployeeManagement.EmployeeManagementSystem;


import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test; // ✅ Use JUnit 5's @Test annotation

import org.junit.jupiter.api.BeforeEach;

import EmployeeManagement.EmployeeManagementSystem.model.Employee;
import EmployeeManagement.EmployeeManagementSystem.model.Gender;
import EmployeeManagement.EmployeeManagementSystem.validation.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class EmployeeInValidInputTest {

    private EmployeeDaoValidation validator;
    private Employee employee;

    @BeforeEach
    public void setUp() {
    	
        validator = new EmployeeDaoValidation();

        employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDateOfBirth(Date.valueOf("1990-01-01"));
        employee.setJoiningDate((Date.valueOf("2020-01-01")));
        employee.setTerminationDate((Date.valueOf("2023-01-01")));
        employee.setEmail("john.doe@example.com");
        employee.setPhoneNumber("9876543210");
        employee.setPosition("Developer");
        employee.setAddress("Delhi");
        employee.setGender(Gender.MALE);// Assuming enum Gender { MALE, FEMALE }
        employee.setAge(employee.calculateAge("1990-01-01"));
    }

    @Test
    public void testValidEmployeeReturnsTrue() throws Exception {
        assertTrue(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testNullEmployeeObject() throws Exception {
        assertFalse(validator.validateEmployeeInput(null));
    }

    @Test
    public void testEmptyFirstName() throws Exception {
        employee.setFirstName("");
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testEmptyLastName() throws Exception {
        employee.setLastName("");
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testNullDateOfBirth() throws Exception {
        employee.setDateOfBirth(null);
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testFutureDateOfBirth() throws Exception {
        employee.setDateOfBirth(Date.valueOf(LocalDate.now().plusDays(1)));
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testNullJoiningDate() throws Exception {
        employee.setJoiningDate(null);
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testFutureJoiningDate() throws Exception {
        employee.setJoiningDate(Date.valueOf(LocalDate.now().plusDays(1)));
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testTerminationBeforeJoining() throws Exception {
        employee.setTerminationDate(Date.valueOf(LocalDate.of(2019, 1, 1)));
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testInvalidEmail() throws Exception {
        employee.setEmail("invalid-email");
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testInvalidPhoneNumber() throws Exception {
        employee.setPhoneNumber("abcd1234");
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testEmptyPosition() throws Exception {
        employee.setPosition("");
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testEmptyAddress() throws Exception {
        employee.setAddress("");
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testInvalidGender() throws Exception {
        // Assuming your code checks string equality, use a dummy gender not matching "MALE"/"FEMALE"
        employee.setGender(Gender.OTHER);
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testAgeBelow18() throws Exception {
        employee.setDateOfBirth(Date.valueOf(LocalDate.now().minusYears(17)));
        assertFalse(validator.validateEmployeeInput(employee));
    }

    @Test
    public void testAgeAbove65() throws Exception {
        employee.setDateOfBirth(Date.valueOf(LocalDate.now().minusYears(66)));
        assertFalse(validator.validateEmployeeInput(employee));
    }
}
