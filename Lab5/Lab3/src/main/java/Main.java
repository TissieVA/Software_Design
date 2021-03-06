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
import view.ViewFrame;
import view.panels.RegistrationButtonPanel;

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
        // Replace with your own objects
        Database timedb = RegistrationDB.getInstance();
        RegistrationController register= new RegistrationController(timedb);
        EmployeeFactory factory = new EmployeeFactory();

        ViewFrame view = new ViewFrame(register);
        view.initialize();

        // Replace with your own observers
        timedb.addObserver(new DatabaseUpdater());
        timedb.addObserver(new EntryPrintObserver());
        timedb.addObserver(new PrintObserver());
        timedb.addObserver(view);

        // Replace with your own employee creation methods
        Employee e1 = factory.getEmployee("Alice", "Programmer");
        Employee e2 = factory.getEmployee("Bob", "CustomerService");
        Employee e3 = factory.getEmployee("Charlie", "Manager");

        sleep(3000);

        register.checkIn(e1);
        register.checkIn(e2);
        register.checkIn(e3);

        sleep(1000);
        register.checkOut(e1);
        sleep(1000);
        register.checkOut(e2);
        sleep(1000);
        register.checkOut(e3);
    }

    public void sleep(int millis)
    {
        try
        {
            System.out.print("Sleeping [    ]\r");
            Thread.sleep(millis);
            System.out.println("Sleeping [ OK ]");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
