package ua.tijsva.sd.project.controller;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;
import ua.tijsva.sd.project.ticket.TicketFactory;

import java.util.ArrayList;
import java.util.UUID;

public class Controller
{
    public Ticket CreateTicket(TicketFactory tf, String ticketType, UUID paidPerson)
    {
        Ticket t = tf.createTicket(ticketType, paidPerson);
        Database.getTicketDB().add(UUID.randomUUID(),t);
        return t;
    }

    public Ticket createTicket(TicketFactory tf, String ticketType, UUID paidPerson, ArrayList<UUID> persons)
    {
        Ticket t = tf.createTicket(ticketType, paidPerson, persons);
        Database.getTicketDB().add(UUID.randomUUID(),t);
        return t;
    }

    public Ticket createTicket(TicketFactory tf, String ticketType, Person paidPerson)
    {
        Ticket t = tf.createTicket(ticketType, paidPerson);
        Database.getTicketDB().add(UUID.randomUUID(), t);
        return t;
    }

    public Ticket createTicket(TicketFactory tf, String ticketType, Person paidPerson, ArrayList<UUID> persons)
    {
        Ticket t = tf.createTicket(ticketType, paidPerson, persons);
        Database.getTicketDB().add(UUID.randomUUID(), t);
        return t;
    }


}
