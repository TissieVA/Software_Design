package ua.tijsva.sd.project;

import ua.tijsva.sd.project.UI.UIFrame;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.*;

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
        Person p3 = new Person("C");
        Database.getPersonDB().add(p1.getId(),p1);
        Database.getPersonDB().add(p2.getId(),p2);
        Database.getPersonDB().add(p3.getId(),p3);
        Controller controller = new Controller();
        EqualSplitTicket t1 = (EqualSplitTicket) controller.createTicket(new EqualTicketFactory(),"azerty",p1);
        t1.setPrice(20);
        t1.addIndebted(p2);
    }

    void run()
    {
        UIFrame ui = new UIFrame();
    }
}
