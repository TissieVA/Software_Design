package ua.tijsva.sd.project.check;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class Check
{
    private Database<Ticket> ticketDb;
    private Database<Person> personDb;
    private HashMap<UUID, Double> totalCheck;
    ArrayList<String> solution = new ArrayList<>();

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
                totalCheck.put(t.getPaidPerson(),totalCheck.get(t.getPaidPerson())+t.getPrice());
            else
                System.out.format("Person %s, who paid ticket %s not found", t.getPaidPerson().toString(),t.getId().toString());

            for(UUID id: t.getIndebted().keySet())
            {
                if (totalCheck.containsKey(id))
                    totalCheck.put(id,totalCheck.get(id)-t.getIndebted().get(id));
                else
                    System.out.format("Person %s in ticked %s not found", id.toString(), t.getId().toString());
            }
        }
        return totalCheck;
    }

    public void whoOwesWho(HashMap<UUID, Double> listing)
    {

        Double maxPrice = (Double) Collections.max(listing.values());
        Double minPrice = (Double) Collections.min(listing.values());
        if(Math.abs(maxPrice-minPrice)>0.1)
        {
            UUID maxPricePerson = getKeyFromValue(listing, maxPrice);
            UUID minPricePerson = getKeyFromValue(listing, minPrice);
            Double result = maxPrice + minPrice;
            result = Math.round(result* 100.0)/100.0;
            if(result>=0.1)
            {
                solution.add(String.format("%s -> %s : %.2f%n",Database.getPersonDB().get(minPricePerson).getName(),Database.getPersonDB().get(maxPricePerson).getName(),Math.abs(minPrice)));

                listing.remove(maxPricePerson);
                listing.remove(minPricePerson);
                listing.put(maxPricePerson, result);
                listing.put(minPricePerson, 0.0);
            }
            else
            {
                solution.add(String.format("%s -> %s : %.2f%n",Database.getPersonDB().get(minPricePerson).getName(),Database.getPersonDB().get(maxPricePerson).getName(),Math.abs(maxPrice)));
                listing.remove(maxPricePerson);
                listing.remove(minPricePerson);
                listing.put(minPricePerson, result);
                listing.put(maxPricePerson, 0.0);
            }
            whoOwesWho(listing);
        }

    }
    public String print()
    {
        this.totalCheck = calculateCheck();
        solution.clear();
        whoOwesWho(this.totalCheck);
        StringBuilder string = new StringBuilder("");

        solution.forEach(string::append);
        return string.toString();
    }

    private UUID getKeyFromValue(HashMap<UUID,Double> hm, Double value)
    {
        for (UUID id : hm.keySet())
        {
            if(hm.get(id).equals(value))
                return id;
        }
        return null;
    }
}
