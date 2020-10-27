package diagrams;

import java.util.HashMap;
import java.util.Map;

public class Booking
{
    private HashMap<Integer, Hotel> hotels;
    private int hotelCount = 0;

    public Booking()
    {
        this.hotels = new HashMap<>();
    }

    /**
     * Adds a given hotel to the booking system.
     * Function needs to give ID to the hotel in its HashMap.
     * It's OK to use hotel count as ID
     * -> first hotel has ID 0
     * -> second hotel has ID 1
     * -> third hotel has ID 2
     * ...
     */
    public void addHotel(Hotel h)
    {
        this.hotels.put(hotelCount, h);
        hotelCount++;
    }

    /**
     * Iterates over all hotels and searches for the cheapest hotel
     * that has an available room on the given date.
     * @param date date to search for room
     * @return ID of cheapest hotel, -1 for nothing available
     */
    public int findCheapestHotel(long date)
    {
        double cheapestPrice = Double.MAX_VALUE;
        int cheapestHotelID = -1;

        for(Map.Entry<Integer, Hotel> entry: hotels.entrySet())
        {
           int e_hotelID = entry.getKey();
           Hotel e_hotel = entry.getValue();

           if (e_hotel.checkAvailability(date) != -1 &&
                   e_hotel.getPricePerRoom() < cheapestPrice)
           {
                  cheapestHotelID = e_hotelID;
                  cheapestPrice = e_hotel.getPricePerRoom();

           }
        }

        return cheapestHotelID;
    }

    /**
     * For given hotelID and date, book a room in that hotel.
     * @param date date on which room needs to be booked
     * @param hotelID hotel to book a room in
     * @return 0 for OK, -1 for error
     */
    public int bookRoomInHotel(long date, int hotelID)
    {
        Hotel currentHotel= this.hotels.get(hotelID);
        int roomID = currentHotel.checkAvailability(date);
        if (roomID < 0)
        {
            return -1;
        }
        else
        {
            int bookRoomID =currentHotel.bookRoom(date,roomID);
            if (bookRoomID < 0)
                return -1;
            else
                return 0;
        }
    }
}
