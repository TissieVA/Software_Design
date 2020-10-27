package classdiagrams.inheritance;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class DepartmentOfficer_UTest
{
    public DepartmentOfficer_UTest()
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
        double companyBonus = 20;
        double expectedSalary = hourlySalary * hoursWorked + companyBonus;

        Employee employeeUnderTest = new DepartmentOfficer(hourlySalary, hoursWorked, companyBonus);
        Assert.assertEquals("Testing salary -- input: " + hourlySalary + " * " + hoursWorked + " + " + companyBonus
                        + " -- should be " + expectedSalary,
                expectedSalary, employeeUnderTest.calculateDailySalary(), 0.001);
    }
}
