package ua.tijsva.sd.project.person;

import java.util.UUID;

public class Person
{
    private String name;
    private UUID id;

    public Person(String name)
    {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
}
