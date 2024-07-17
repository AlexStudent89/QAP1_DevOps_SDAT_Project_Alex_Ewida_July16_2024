
//EmployeeTest
package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

    @Test
    public void testGetAnnualSalary() {
        Employee employeeUnderTest = new Employee(1, "Jamie", "Cornick", 100);
        Assertions.assertEquals(1200, employeeUnderTest.getAnnualSalary());
    }

    @Test
    public void testEmployeesAreEqual() {
        Employee employeeUnderTest1 = new Employee(1, "Jamie", "Cornick", 100);
        Employee employeeUnderTest2 = new Employee(2, "Jamie", "Cornick", 100);
        Assertions.assertEquals(employeeUnderTest1, employeeUnderTest2);
    }
// Fixed the test below
    @Test
    public void testRaiseSalaryBy20Percent() {
        Employee employeeUnderTest = new Employee(1, "Jamie", "Cornick", 5000);
        int newSalary = employeeUnderTest.raiseSalary(20);
        Assertions.assertEquals(6000, newSalary);
        Assertions.assertEquals(6000, employeeUnderTest.getMonthlySalary());
    }
// Fixed the test below
    @Test
    public void testToString() {
        Employee employeeUnderTest = new Employee(1, "Jamie", "Cornick", 100);
        String expectedString = "Employee[id=1,name=Jamie Cornick,salary=100]";
        Assertions.assertEquals(expectedString, employeeUnderTest.toString());
    }
}
