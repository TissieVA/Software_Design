package ua.tijsva.sd.project.ticket;

import ua.tijsva.sd.project.person.Person;

import java.util.ArrayList;
import java.util.UUID;

public abstract class TicketFactory
{
    public abstract Ticket createTicket(String ticketType, UUID paidPerson);

    public Ticket createTicket(String ticketType, UUID paidPerson, ArrayList<UUID> persons)
    {
        Ticket ticket = createTicket(ticketType, paidPerson);
        ticket.setIndebted(persons);
        return ticket;
    }

    public Ticket createTicket(String ticketType, Person paidPerson) {
        return createTicket(ticketType, paidPerson.getId());
    }

    public Ticket createTicket(String ticketType, Person paidPerson, ArrayList<UUID> persons)
    {
        Ticket ticket = createTicket(ticketType, paidPerson);
        ticket.setIndebted(persons);
        return ticket;
    }

    public static TicketFactory getEqualSplitTicketFactory()
    {
        return new EqualTicketFactory();
    }

    public static TicketFactory getUnequalSplitTicketFactory()
    {
        return new UnequalTicketFactory();
    }

}
