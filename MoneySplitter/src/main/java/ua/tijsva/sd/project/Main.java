package ua.tijsva.sd.project;

import ua.tijsva.sd.project.UI.UIFrame;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.EqualTicketFactory;
import ua.tijsva.sd.project.ticket.TicketFactory;
import ua.tijsva.sd.project.ticket.UnequalTicketFactory;

import java.util.UUID;

public class Main
{
    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public Main()
    {
        Person p1 = new Person("Alberto Vermicelli");
        Person p2 = new Person("B");
        Database.getPersonDB().add(p1.getId(),p1);
        Database.getPersonDB().add(p2.getId(),p2);
    }

    void run()
    {
        UIFrame ui = new UIFrame();
    }
}
