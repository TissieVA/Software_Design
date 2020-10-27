package classdiagrams.inheritance;

public class Employee
{
protected double hourlySalary;
protected double hoursWorked;

    public Employee(double hourlySalary, double hoursWorked) {
        this.hourlySalary = hourlySalary;
        this.hoursWorked = hoursWorked;
    }

    public double calculateDailySalary()
    {
        return this.hourlySalary*this.hoursWorked;
    }
}
