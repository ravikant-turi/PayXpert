package EmployeeManagement.EmployeeManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test; // ✅ Use JUnit 5's @Test annotation

import EmployeeManagement.EmployeeManagementSystem.model.Calculator;

public class CalCulatorTest {

    @Test
    void testMultiply() {
        Calculator calc = new Calculator();

        int res = calc.multiply(2, 3);
        assertEquals(69, res);
    }
}
