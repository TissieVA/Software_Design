package ua.tijsva.sd.project.check;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;

import java.util.HashMap;
import java.util.UUID;

public class Check
{
    private Database<Ticket> ticketDb;
    private Database<Person> personDb;
    private HashMap<UUID, Double> totalCheck;

    public Check(Database db)
    {
        this.personDb = db.getPersonDB();
        this.ticketDb = db.getTicketDB();
    }

    public HashMap calculateCheck()
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
}
