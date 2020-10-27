package classdiagrams.inheritance;

public class CustomerService extends Employee
{

        protected double bonusPerCostumer;
        protected double numberOfCostumers;

    public CustomerService(double hourlySalary, double hoursWorked, double bonusPerCostumer, double numberOfCostumers) {
        super(hourlySalary, hoursWorked);
        this.bonusPerCostumer = bonusPerCostumer;
        this.numberOfCostumers= numberOfCostumers;
    }

    @Override
    public double calculateDailySalary() {
        return (this.numberOfCostumers * this.bonusPerCostumer) + (this.hourlySalary * this.hoursWorked);
    }
}
