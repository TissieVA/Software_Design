import controller.Controller;
import controller.RegistrationController;
import database.Database;
import database.RegistrationDB;
import employee.CustomerService;
import employee.Employee;
import employee.Manager;
import employee.Programmer;
import factory.EmployeeFactory;
import observers.DatabaseUpdater;
import observers.EntryPrintObserver;
import observers.PrintObserver;
import register_entry.RegisterEntry;


public class Main
{
    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public Main()
    {

    }

    public void run()
    {
        Database timedb = RegistrationDB.getInstance();
        Controller register= new RegistrationController(timedb);
        timedb.addObserver(new DatabaseUpdater());
        timedb.addObserver(new EntryPrintObserver());
        timedb.addObserver(new PrintObserver());
        EmployeeFactory factory = new EmployeeFactory();

        Employee e1 = factory.getEmployee("Alice","Programmer");
        Employee e2 = factory.getEmployee("Bob","CustomerService");
        Employee e3 = factory.getEmployee("Charlie","Manager");

        register.checkIn(e1);
        register.checkIn(e2);

        register.checkIn(e3);

        register.checkOut(e1);
        register.checkOut(e2);
        register.checkOut(e3);

    }

    public void print(Employee e, RegisterEntry re)
    {
        System.out.println(e.getName() +
                " (" + e.getFunction() + ")" +
                " " + re);
    }
}
