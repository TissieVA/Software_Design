package ua.tijsva.sd.project.register_entry;

import java.util.Calendar;

public class RegisterEntry
{
    protected boolean created;
    protected int day;
    protected int month;
    protected int year;
    protected int hours;
    protected int minutes;

    public RegisterEntry(boolean created)
    {
        this.created = created;
        Calendar cal = Calendar.getInstance();
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.month = cal.get(Calendar.MONTH);
        this.year = cal.get((Calendar.YEAR));
        this.hours = cal.get(Calendar.HOUR_OF_DAY);
        this.minutes = cal.get(Calendar.MINUTE);
    }

    public boolean isCreated()
    {
        return created;
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public int getHours()
    {
        return hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public String entryStringBrief(String ticketType)
    {
        String status;
        if(this.created)
            status = "created";
        else
            status = "removed";

        return String.format("%s ticked %s at %02d:%02d ",ticketType,status,hours,minutes);
    }

    public String entryPStringDetail(String ticketType)
    {
        String status;
        if(this.created)
            status = "created";
        else
            status = "removed";

        return String.format("%s ticked %s at %02d:%02d %02d/%02d/%02d",ticketType,status,hours,minutes,day,month,(year%100));
    }
}
