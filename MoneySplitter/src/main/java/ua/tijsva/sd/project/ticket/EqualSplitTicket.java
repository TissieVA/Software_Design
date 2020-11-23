package ua.tijsva.sd.project.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EqualSplitTicket extends Ticket
{
    private ArrayList<UUID> persons;

    public EqualSplitTicket(String ticketType, UUID paidPerson, double price)
    {
        super(ticketType, paidPerson, price);
    }

    public ArrayList<UUID> getPersons() {
        return persons;
    }
    
    public void setPersons(ArrayList<UUID> persons) {
        this.persons = persons;
        for (UUID id : persons)
        {
            this.indebted.put(id, this.price/this.persons.size());
        }
    }
}
