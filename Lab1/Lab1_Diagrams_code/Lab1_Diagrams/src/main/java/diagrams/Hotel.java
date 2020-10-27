package diagrams;

import java.util.HashMap;
import java.util.Map;

public class Hotel
{
    private HashMap<Integer, Room> rooms;
    private int roomCount = 0;
    private double pricePerRoom;

    public Hotel(double pricePerRoom)
    {
        this.rooms = new HashMap<>();
        this.pricePerRoom = pricePerRoom;
    }

    /**
     * Adds an empty room to the hotel.
     * Function needs to give ID to the room.
     * It's OK to use room count as ID
     * -> first room has ID 0
     * -> second room has ID 1
     * -> third room has ID 2
     * ...
     */
    public void addRoom()
    {
       Room newRoom = new Room();
       this.rooms.put(roomCount, newRoom);

       this.roomCount++;
    }

    /**
     * Checks available rooms on a specific date
     * @param date date to check for available room
     * @return int containing ID of first available room, -1 if no room is available
     */
    public int checkAvailability(long date)
    {
        int roomID = -1;
        for (Map.Entry<Integer, Room> e: this.rooms.entrySet())
        {
            int e_roomID = e.getKey();
            Room e_room = e.getValue();

            if (!e_room.getBooking(date))
            {
               roomID = e_roomID;
               break;
            }

        }
        return roomID;
    }

    /**
     * Books a given room on a given date
     * @param date date on which room needs to be booked
     * @param roomID ID of room that needs to be booked
     * @return -1 for error, 0 for OK
     */
    public int bookRoom(long date, int roomID)
    {
        Room e_room = this.rooms.get(roomID);
        Boolean booked = e_room.getBooking(date);
        if (booked)
        {
            return -1;
        }
        else
        {
            e_room.setBooking(date, true);
            this.rooms.put(roomID, e_room);
            return 0;
        }
    }

    public double getPricePerRoom()
    {
        return this.pricePerRoom;
    }
}
