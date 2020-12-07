package ua.tijsva.sd.project.controller;

import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;
import ua.tijsva.sd.project.ticket.TicketFactory;

import java.util.ArrayList;
import java.util.UUID;

public class Controller
{
    public Ticket CreateTicket(TicketFactory tf, String ticketType, UUID paidPerson, double price)
    {
        return tf.createTicket( ticketType, paidPerson, price);
    }

    public Ticket createTicket(TicketFactory tf, String ticketType, UUID paidPerson, double price, ArrayList<UUID> persons)
    {
        return tf.createTicket(ticketType,paidPerson,price,persons);
    }

    public Ticket createTicket(TicketFactory tf, String ticketType, Person paidPerson, double price)
    {
        return tf.createTicket(ticketType, paidPerson.getId(), price);
    }

    public Ticket createTicket(TicketFactory tf, String ticketType, Person paidPerson, double price, ArrayList<UUID> persons)
    {
        return tf.createTicket(ticketType, paidPerson, price, persons);
    }


}
