package ua.tijsva.sd.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.tijsva.sd.project.check.Check;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest
{
    Controller controller;
    Person p1;
    Person p2;
    Person p3;
    EqualSplitTicket est;
    UnequalSplitTicket ust;

    @Before
    public void initialize()
    {
        this.controller = new Controller();
        this.p1 = new Person("A");
        this.p2 = new Person("B");
        this.p3 = new Person("C");
        Database.getPersonDB().add(p1.getId(),p1);
        Database.getPersonDB().add(p2.getId(),p2);
        Database.getPersonDB().add(p3.getId(),p3);
        this.ust = (UnequalSplitTicket) controller.createTicket(new UnequalTicketFactory(),"TestUnequalTicket1",p1);
        this.est = (EqualSplitTicket) controller.createTicket(new EqualTicketFactory(),"TestEqualTicket1",p1);
    }

    @Test
    public void t_addPerson()
    {
        Person p4 = new Person("D");
        int size = Database.getPersonDB().getSize();
        Assert.assertNull(Database.getPersonDB().get(p4.getId()));
        Database.getPersonDB().add(p4.getId(),p4);
        Assert.assertEquals(size+1,Database.getPersonDB().getSize(),0.01);
    }

    @Test
    public void t_createEqualTicket()
    {
        int size = Database.getTicketDB().getSize();
        EqualSplitTicket t = (EqualSplitTicket) controller.createTicket(new EqualTicketFactory(),"TestTicket",p1);
        Assert.assertEquals(size+1,Database.getTicketDB().getSize(),0.01);
        Assert.assertNotNull(Database.getTicketDB().get(t.getId()));
        Assert.assertEquals("TestTicket",Database.getTicketDB().get(t.getId()).getTicketType());
        Assert.assertEquals(p1.getId(),Database.getTicketDB().get(t.getId()).getPaidPerson());

    }

    @Test
    public void t_createUnequalTicket()
    {
        int size = Database.getTicketDB().getSize();
        UnequalSplitTicket t = (UnequalSplitTicket) controller.createTicket(new UnequalTicketFactory(),"TestTicket",p1);
        Assert.assertEquals(size+1,Database.getTicketDB().getSize(),0.01);
        Assert.assertNotNull(Database.getTicketDB().get(t.getId()));
        Assert.assertEquals("TestTicket",Database.getTicketDB().get(t.getId()).getTicketType());
        Assert.assertEquals(p1.getId(),Database.getTicketDB().get(t.getId()).getPaidPerson());
    }

    @Test
    public void t_removeTicket()
    {
        int size = Database.getTicketDB().getSize();
        Database.getTicketDB().remove(est.getId());
        Assert.assertEquals(size-1,Database.getTicketDB().getSize(),0.01);
        Database.getTicketDB().remove(ust.getId());
        Assert.assertEquals(size-2,Database.getTicketDB().getSize(),0.01);
    }

    @Test
    public void t_calculate1()
    {
        est.addIndebted(p2);
        est.addIndebted(p3);
        est.setPrice(30.0);

        EqualSplitTicket est2 = (EqualSplitTicket) controller.createTicket(new EqualTicketFactory(),"EqualTicket2",p2);
        est2.addIndebted(p1);
        est2.addIndebted(p3);
        est2.setPrice(15.0);

        ust.addIndebted(p1,3.0);
        ust.addIndebted(p2,4.0);
        ust.addIndebted(p3,5.0);

        Check check = new Check(Database.getTicketDB(), Database.getPersonDB());
        HashMap<UUID, Double> calculation = check.calculateCheck();

        Assert.assertEquals(24,calculation.get(p1.getId()),0.01);
        Assert.assertEquals(-4,calculation.get(p2.getId()),0.01);
        Assert.assertEquals(-20,calculation.get(p3.getId()),0.01);

    }

    @Test
    public void t_calculate2()
    {
        Person p4 = new Person("D");
        Database.getPersonDB().add(p4.getId(),p4);

        est.addIndebted(p2);
        est.addIndebted(p3);
        est.addIndebted(p4);
        est.setPrice(150.0);

        EqualSplitTicket est2 = (EqualSplitTicket) controller.createTicket(new EqualTicketFactory(),"est2",p4);
        est2.addIndebted(p1);
        est2.addIndebted(p2);
        est2.addIndebted(p3);
        est2.setPrice(129.15);

        EqualSplitTicket est3 = (EqualSplitTicket) controller.createTicket(new EqualTicketFactory(),"est3",p4);
        est3.addIndebted(p1);
        est3.addIndebted(p2);
        est3.setPrice(27.15);

        UnequalSplitTicket ust2 = (UnequalSplitTicket) controller.createTicket(new UnequalTicketFactory(),"ust2",p3);
        ust2.addIndebted(p3,3.1);
        ust2.addIndebted(p4,4.2);

        Database.getTicketDB().remove(ust.getId());

        Check check = new Check(Database.getTicketDB(), Database.getPersonDB());
        HashMap<UUID, Double> calculation = check.calculateCheck();

        Assert.assertEquals(71.1625,calculation.get(p1.getId()),0.001);
        Assert.assertEquals(-78.8375,calculation.get(p2.getId()),0.001);
        Assert.assertEquals(-65.5875,calculation.get(p3.getId()),0.001);
        Assert.assertEquals(73.2625,calculation.get(p4.getId()),0.001);


    }
}