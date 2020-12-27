package ua.tijsva.sd.project.database;

import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;

import java.util.*;
import java.util.function.Consumer;

public class Database<T> extends Observable implements Iterable<T>
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

    public void remove(UUID id)
    {
        if(dbMap.containsKey(id))
            this.dbMap.remove(id);
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

    public Integer getSize()
    {
        return this.dbMap.size();
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.dbMap.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action)
    {
       this.dbMap.values().forEach(action);
    }

    @Override
    public Spliterator<T> spliterator()
    {
        return this.dbMap.values().spliterator();
    }
}
