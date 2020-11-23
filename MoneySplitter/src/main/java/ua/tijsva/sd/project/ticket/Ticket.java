package ua.tijsva.sd.project.ticket;

import java.util.HashMap;
import java.util.UUID;

public abstract class Ticket
{
    protected String ticketType;
    protected UUID id;
    protected UUID paidPerson;
    protected double price=0;
    protected HashMap<UUID, Double> indebted;

    public Ticket(String ticketType, UUID paidPerson, double price) {
        this.ticketType = ticketType;
        this.paidPerson = paidPerson;
        this.price = price;
        this.id = UUID.randomUUID();
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPaidPerson() {
        return paidPerson;
    }

    public void setPaidPerson(UUID paidPerson) {
        this.paidPerson = paidPerson;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HashMap<UUID, Double> getIndebted() {
        return indebted;
    }

}
