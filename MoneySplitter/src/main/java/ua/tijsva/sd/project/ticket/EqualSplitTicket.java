package ua.tijsva.sd.project.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EqualSplitTicket extends Ticket
{

    public EqualSplitTicket(String ticketType, UUID paidPerson, double price)
    {
        super(ticketType, paidPerson, price);
    }


    @Override
    public void setIndebted(ArrayList<UUID> persons)
    {
        for (UUID id : persons)
        {
            this.indebted.put(id, this.price/persons.size());
        }
    }
}
