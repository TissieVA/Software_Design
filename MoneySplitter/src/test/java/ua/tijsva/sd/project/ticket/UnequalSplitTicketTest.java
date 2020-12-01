package ua.tijsva.sd.project.ticket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.tijsva.sd.project.person.Person;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

public class UnequalSplitTicketTest {
    private UnequalSplitTicket t1;
    ArrayList<Person> persons;
    HashMap<UUID,Double> personsHM;

    @Before
    public void Initialize()
    {
        Person p1 = new Person("A");
        Person p2 = new Person("B");
        Person p3 = new Person("C");


        Person[] pa = new Person[]{p1,p2,p3};
        this.persons = new ArrayList<>(Arrays.asList(pa));
        this.personsHM = new HashMap<>();
        for(int i=0; i<this.persons.size();i++)
        {
            personsHM.put(this.persons.get(i).getId(),(i+1)*10/1.0);
        }

        TicketFactory tf = new UnequalTicketFactory();
        this.t1 =(UnequalSplitTicket) tf.createTicket("Restaurant",p1.getId(),200.0);
        this.t1.setIndebted(this.persons);

    }

    @Test
    public void setTicketType()
    {
        this.t1.setTicketType("Test");
        Assert.assertEquals("Test",this.t1.getTicketType());
    }


    @Test
    public void getPaidPerson()
    {
        Assert.assertEquals(this.persons.get(0).getId(), this.t1.getPaidPerson());
    }

    @Test
    public void setIndepted()
    {
        this.t1.setIndebted(this.personsHM);
        Assert.assertEquals(3,this.t1.indebted.size(),0.01);
        Assert.assertEquals(10+20+30, this.t1.getPrice(),0.01);

    }

    @Test
    public void addIndebted()
    {
        this.t1.setIndebted(this.personsHM);
        Person p4 = new Person("D");
        this.persons.add(p4);
        Assert.assertEquals(this.t1.addIndebted(this.persons.get(3),40),true);
        Assert.assertEquals(10+20+30+40,this.t1.getPrice(),0.01);
        Assert.assertEquals(10.0,this.t1.getIndebted().get(this.persons.get(0).getId()),0.01);
        Assert.assertEquals(40.0,this.t1.getIndebted().get(this.persons.get(3).getId()),0.01);
    }

}
