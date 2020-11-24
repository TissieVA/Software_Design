package ua.tijsva.sd.project.ticket;

import ua.tijsva.sd.project.person.Person;

import java.util.ArrayList;
import java.util.UUID;

public abstract class TicketFactory
{
    public abstract Ticket createTicket(String ticketType, UUID paidPerson, double price);

    public Ticket createTicket(String ticketType, UUID paidPerson, double price, ArrayList<UUID> persons)
    {
        Ticket ticket = createTicket(ticketType, paidPerson, price);
        ticket.setIndebted(persons);
        return ticket;
    }

    public Ticket createTicket(String ticketType, Person paidPerson, double price) {
        return createTicket(ticketType, paidPerson.getId(), price);
    }

    public Ticket createTicket(String ticketType, Person paidPerson, double price, ArrayList<UUID> persons)
    {
        Ticket ticket = createTicket(ticketType, paidPerson, price);
        ticket.setIndebted(persons);
        return ticket;
    }

}
