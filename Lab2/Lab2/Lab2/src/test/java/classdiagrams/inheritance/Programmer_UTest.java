package classdiagrams.inheritance;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class Programmer_UTest
{
    public Programmer_UTest()
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
        double bonusPerBug = 0.5;
        double numberOfBugs = 20;
        double expectedSalary = (hourlySalary * hoursWorked) + (bonusPerBug * numberOfBugs);

        Employee employeeUnderTest = new Programmer(hourlySalary, hoursWorked, bonusPerBug, numberOfBugs);
        Assert.assertEquals("Testing salary -- input: (" + hourlySalary + " * " + hoursWorked + ") + ("
                        + bonusPerBug + " * " + numberOfBugs + ")"
                        + " -- should be " + expectedSalary,
                expectedSalary, employeeUnderTest.calculateDailySalary(), 0.001);
    }
}
