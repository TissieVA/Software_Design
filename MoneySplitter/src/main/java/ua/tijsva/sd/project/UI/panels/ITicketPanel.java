package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;

public interface ITicketPanel
{
    Ticket create(String ticketType, Person paidPerson, double price);
}
