package ua.tijsva.sd.project.ticket;

import ua.tijsva.sd.project.person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UnequalSplitTicket extends Ticket
{
    public UnequalSplitTicket(String ticketType, UUID paidPerson) {
        super(ticketType, paidPerson);
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
    public Boolean addIndebted(Person person)
    {
        return super.addIndebted(person);
    }

    @Override
    public void setIndebted(HashMap<UUID, Double> indebted)
    {
        super.setIndebted(indebted);
        calculatePrice();
    }

    public Boolean addIndebted(Person person, double price)
    {
        if(super.addIndebted(person))
        {
            this.indebted.put(person.getId(),price);
            calculatePrice();
            return true;
        }
        return false;
    }
}
