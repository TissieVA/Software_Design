package ua.tijsva.sd.project.ticket;

import ua.tijsva.sd.project.person.Person;

import java.util.HashMap;
import java.util.UUID;

public class UnequalSplitTicket extends Ticket
{
    public UnequalSplitTicket(String ticketType, UUID paidPerson, double price) {
        super(ticketType, paidPerson, price);
    }

    public void calculatePrice()
    {
        double price =0.0;
        for (UUID id: this.indebted.keySet())
        {
            price += this.indebted.get(id);
        }
        this.price=price;
    }

    @Override
    public void setIndebted(HashMap<UUID, Double> indebted)
    {
        super.setIndebted(indebted);
    }

    public Boolean addIndebted(Person person, double price)
    {
        if(super.addIndebted(person))
        {
            this.indebted.put(person.getId(),price);
            return true;
        }
        return false;
    }
}
