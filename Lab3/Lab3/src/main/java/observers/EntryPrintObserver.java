package observers;

import database.Database;
import employee.Employee;
import register_entry.RegisterEntry;

import java.util.Observable;
import java.util.Observer;

public class EntryPrintObserver implements Observer
{

    @Override
    public void update(Observable o, Object arg)
    {
        Database database= (Database) o ;
        System.out.println(database.getEntry((Employee) arg));
    }
}
