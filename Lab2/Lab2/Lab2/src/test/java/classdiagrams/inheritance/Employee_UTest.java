package classdiagrams.inheritance;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class Employee_UTest
{
    public Employee_UTest()
    {
        // Intentionally left blank
    }

    @Before
    public void initialize()
    {

    }

    @Test
    public void t_calculateDailySalary()
    {
        double hoursWorked = 8;
        double hourlySalary = 10;
        double expectedSalary = hourlySalary * hoursWorked;

        Employee employeeUnderTest = new Employee(hourlySalary, hoursWorked);
        Assert.assertEquals("Testing salary -- input: " + hourlySalary + " * " + hoursWorked
                        + " -- should be " + expectedSalary,
                expectedSalary, employeeUnderTest.calculateDailySalary(), 0.001);
    }
}
