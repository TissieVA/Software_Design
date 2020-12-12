package ua.tijsva.sd.project.ticket;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class Ticket
{
    protected String ticketType;
    protected UUID id;
    protected UUID paidPerson;
    protected double price=0;
    protected HashMap<UUID, Double> indebted;

    public Ticket(String ticketType, UUID paidPerson) {
        this.ticketType = ticketType;
        this.paidPerson = paidPerson;
        this.id = UUID.randomUUID();
        this.indebted = new HashMap<>();
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPaidPerson() {
        return paidPerson;
    }

    public double getPrice() {
        return price;
    }

    public HashMap<UUID, Double> getIndebted() {
        return indebted;
    }

    public void setIndebted(HashMap<UUID, Double> indebted) {
        this.indebted = indebted;
    }

    public void setIndebted(ArrayList<UUID> persons)
    {
        this.indebted.clear();
        for (UUID person: persons)
        {
            this.indebted.put(person,0.0);
        }
    }

    public Boolean addIndebted(Person person)
    {
        if(this.indebted.containsKey(person))
            return false;

        this.indebted.put(person.getId(),0.0);
        return true;
    }

    public void setIndebted(List<Person> persons)
    {
        ArrayList<UUID> UUIDlist = (ArrayList<UUID>) persons.stream().map(Person::getId).collect(Collectors.toList());
        this.setIndebted(UUIDlist);
    }

    @Override
    public String toString()
    {
        String string = String.format("%s : %.2f paid by %s. Persons: ",ticketType,price,Database.getPersonDB().get(paidPerson).getName());
        for (UUID id: this.indebted.keySet())
        {
            string += String.format("%s, ",Database.getPersonDB().get(id).getName());
        }
        string = string.substring(0, string.length() -2);
        return string;
    }
}
