package ua.tijsva.sd.project;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.ticket.EqualTicketFactory;
import ua.tijsva.sd.project.ticket.TicketFactory;

public class Main
{
    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public Main()
    {
        TicketFactory etf = new EqualTicketFactory();
    }

    void run()
    {

    }
}
