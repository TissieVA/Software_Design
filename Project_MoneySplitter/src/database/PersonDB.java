package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import person.Person;

public class PersonDB extends Database
{
    private static PersonDB instance;
    private final HashMap<UUID,Person> persons;

    public PersonDB()
    {
        this.persons = new HashMap<>();
    }

    public static PersonDB getInstance()
    {
        if(instance == null)
            instance = new PersonDB();

        return instance;
    }

    @Override
    public void add(UUID id, Object o)
    {
        this.persons.put(id,(Person) o);
        instance.setChanged();
        instance.notifyObservers();
    }

    @Override
    public Object get(UUID id) {
        return this.persons.get(id);
    }

}
