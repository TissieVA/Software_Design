package classdiagrams.inheritance;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CustomerService_UTest
{
    public CustomerService_UTest()
    {

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
        double bonusPerCustomer = 0.5;
        double numberOfCustomers = 20;
        double expectedSalary = (hourlySalary * hoursWorked) + (bonusPerCustomer * numberOfCustomers);

        Employee employeeUnderTest = new Programmer(hourlySalary, hoursWorked, bonusPerCustomer, numberOfCustomers);
        Assert.assertEquals("Testing salary -- input: (" + hourlySalary + " * " + hoursWorked + ") + ("
                        + bonusPerCustomer + " * " + numberOfCustomers + ")"
                        + " -- should be " + expectedSalary,
                expectedSalary, employeeUnderTest.calculateDailySalary(), 0.001);
    }
}
