package ua.tijsva.sd.project.database;

import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;

import java.util.HashMap;
import java.util.Observable;
import java.util.UUID;

public class Database<T> extends Observable
{
    static Database<Person> personDB;
    static Database<Ticket> ticketDB;
    private HashMap<UUID, T> dbMap;

    private Database()
    {
        this.dbMap = new HashMap<>();
    }

    public void add(UUID id, T t)
    {
        this.dbMap.put(id, t);
        this.setChanged();
        this.notifyObservers();
    }

    public T get(UUID id)
    {
        return this.dbMap.get(id);
    }

    public static Database<Person> getPersonDB()
    {
        if (personDB == null)
        {
            personDB = new Database<>();
        }
        return personDB;
    }

    public static Database<Ticket> getTicketDB()
    {
        if (ticketDB == null)
        {
            ticketDB = new Database<>();
        }
        return ticketDB;
    }
}
