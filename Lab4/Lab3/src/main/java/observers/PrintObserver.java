package observers;

import database.Database;
import employee.Employee;
import register_entry.RegisterEntry;

import java.util.Observable;
import java.util.Observer;

public class PrintObserver implements Observer
{

    @Override
    public void update(Observable o, Object arg)
    {
        Database database= (Database) o ;
        print((Employee) arg, database.getEntry((Employee) arg));
    }

    private void print(Employee e, RegisterEntry re)
    {
        System.out.println(e.getName() +
                " (" + e.getFunction() + ")" +
                " " + re);
    }
}
