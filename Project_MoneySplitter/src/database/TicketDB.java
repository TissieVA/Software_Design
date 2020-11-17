package database;

import person.Person;
import ticket.Ticket;

import java.util.HashMap;
import java.util.UUID;

public class TicketDB extends Database
{
    private static TicketDB instance;
    private final HashMap<UUID, Ticket> tickets;

    public TicketDB()
    {
        this.tickets = new HashMap<>();
    }

    public static TicketDB getInstance()
    {
        if(instance == null)
            instance = new TicketDB();

        return instance;
    }

    @Override
    public void add(UUID id, Object o)
    {
        this.tickets.put(id,(Ticket) o);
        instance.setChanged();
        instance.notifyObservers();
    }

    @Override
    public Object get(UUID id) {
        return this.tickets.get(id);
    }

}
