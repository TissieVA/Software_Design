package ua.tijsva.sd.project.ticket;

import java.util.ArrayList;
import java.util.UUID;

public class EqualTicketFactory extends TicketFactory
{
    @Override
    public Ticket createTicket(String ticketType, UUID paidPerson) {
        return new EqualSplitTicket(ticketType, paidPerson);
    }
}
