package ua.tijsva.sd.project.ticket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.tijsva.sd.project.person.Person;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class EqualSplitTicketTest {
    private EqualSplitTicket t1;
    ArrayList<Person> persons;

    @Before
    public void Initialize()
    {
        Person p1 = new Person("A");
        Person p2 = new Person("B");
        Person p3 = new Person("C");


        Person[] pa = new Person[]{p1,p2,p3};
        this.persons = new ArrayList<>(Arrays.asList(pa));

        TicketFactory tf = new EqualTicketFactory();
        this.t1 =(EqualSplitTicket) tf.createTicket("Restaurant",p1.getId(),200.0);
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
    public void setPrice()
    {
        this.t1.setPrice(300.0);
        Assert.assertEquals(300.0, this.t1.getPrice(), 0.01);
        Assert.assertEquals(100.0,this.t1.getIndebted().get(this.persons.get(0).getId()),0.01);
    }

    @Test
    public void addIndebted()
    {
        Person p4 = new Person("D");
        this.persons.add(p4);
        Assert.assertEquals(this.t1.addIndebted(this.persons.get(3)),true);
        Assert.assertEquals(50.0,this.t1.getIndebted().get(this.persons.get(3).getId()),0.01);
        //Assert.assertEquals(50.0,this.t1.getIndebted().get(this.persons.get(0).getId()),0.01);
    }

}
