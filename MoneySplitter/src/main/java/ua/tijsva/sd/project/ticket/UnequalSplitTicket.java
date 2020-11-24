package ua.tijsva.sd.project.ticket;

import java.util.UUID;

public class UnequalSplitTicket extends Ticket
{
    public UnequalSplitTicket(String ticketType, UUID paidPerson, double price) {
        super(ticketType, paidPerson, price);
    }
}
