package ua.tijsva.sd.project.check;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Check
{
    private Database<Ticket> ticketDb;
    private Database<Person> personDb;
    private HashMap<UUID, Double> totalCheck;

    public Check(Database<Ticket> ticketDb, Database<Person> personDb)
    {
        this.personDb = personDb;
        this.ticketDb = ticketDb;
        this.totalCheck = new HashMap<>();
    }

    public HashMap<UUID, Double> calculateCheck()
    {
        this.totalCheck.clear();
        this.personDb.forEach(x -> this.totalCheck.put(x.getId(),0.0));
        for(Ticket t : ticketDb)
        {
            if(totalCheck.containsKey(t.getPaidPerson()))
                totalCheck.put(t.getPaidPerson(),totalCheck.get(t.getPaidPerson())-t.getPrice());
            else
                System.out.format("Person %s, who paid ticket %s not found", t.getPaidPerson().toString(),t.getId().toString());

            for(UUID id: t.getIndebted().keySet())
            {
                if (totalCheck.containsKey(id))
                    totalCheck.put(id,totalCheck.get(id)+t.getIndebted().get(id));
                else
                    System.out.format("Person %s in ticked %s not found", id.toString(), t.getId().toString());
            }
        }
        return totalCheck;
    }

    public void whoOwesWho()
    {
        this.totalCheck = calculateCheck();

    }

    public String print()
    {
        this.totalCheck = calculateCheck();
        StringBuilder string = new StringBuilder("Positive values are people who owe money to the people with negative value.%n");

        for(UUID id: totalCheck.keySet())
        {
            string.append(String.format("%s : %.2f%n", Database.getPersonDB().get(id).getName(), totalCheck.get(id)));
        }

        return string.toString();
    }
}
