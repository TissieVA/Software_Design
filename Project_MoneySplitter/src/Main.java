import database.Database;
import database.PersonDB;
import database.TicketDB;

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

    void run()
    {
        Database personDB = PersonDB.getInstance();
        Database ticketDB = TicketDB.getInstance();
    }
}
