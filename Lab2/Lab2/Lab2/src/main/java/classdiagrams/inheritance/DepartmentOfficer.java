package classdiagrams.inheritance;

public class DepartmentOfficer extends Employee
{
    protected double companyBonus;

    public DepartmentOfficer(double hourlySalary, double hoursWorked, double companyBonus) {
        super(hourlySalary, hoursWorked);
        this.companyBonus= companyBonus;
    }

    @Override
    public double calculateDailySalary() {
        return (this.hourlySalary * this.hoursWorked) + companyBonus;
    }
}
