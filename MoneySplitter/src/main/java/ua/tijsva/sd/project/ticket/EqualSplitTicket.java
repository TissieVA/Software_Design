package ua.tijsva.sd.project.ticket;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EqualSplitTicket extends Ticket
{

    public EqualSplitTicket(String ticketType, UUID paidPerson)
    {
        super(ticketType, paidPerson);
    }

    public void setPrice(double price)
    {
        this.price = price;
        this.setIndebted( new ArrayList<>(this.indebted.keySet()));
    }

    @Override
    public Boolean addIndebted(Person person)
    {
        if(super.addIndebted(person))
        {
            this.indebted.forEach((id,price) -> this.indebted.put(id,this.price/ this.indebted.size())); //recalculate price for every person
            return true;
        }
        return false;
    }

    @Override
    public void setIndebted(ArrayList<UUID> persons)
    {
        this.indebted.clear();
        for (UUID id : persons)
        {
            this.indebted.put(id, this.price/persons.size());
        }
    }
}
