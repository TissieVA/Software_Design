package ua.tijsva.sd.project.ticket;

import java.util.UUID;

public class UnequalTicketFactory extends TicketFactory
{
    @Override
    public Ticket createTicket(String ticketType, UUID paidPerson) {
        return new UnequalSplitTicket(ticketType, paidPerson);
    }
}
