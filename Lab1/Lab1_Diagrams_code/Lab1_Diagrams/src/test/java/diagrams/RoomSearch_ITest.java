package diagrams;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class RoomSearch_ITest
{
    Booking booking;

    public RoomSearch_ITest()
    {

    }

    @Before
    public void initialize()
    {
        System.out.println("Initializing integration test");

        Hotel hotel0 = new Hotel(30.0);
        hotel0.addRoom();
        hotel0.addRoom();
        hotel0.addRoom();
        hotel0.bookRoom(10, 0);
        hotel0.bookRoom(10, 1);
        hotel0.bookRoom(10, 2);
        hotel0.bookRoom(11, 0);
        hotel0.bookRoom(11, 1);
        hotel0.bookRoom(11, 2);
        hotel0.bookRoom(12, 2);

        Hotel hotel1 = new Hotel(46.99);
        hotel1.addRoom();
        hotel1.addRoom();
        hotel1.bookRoom(10, 0);
        hotel1.bookRoom(10, 1);
        hotel1.bookRoom(11, 0);
        hotel1.bookRoom(12, 1);

        Hotel hotel2 = new Hotel(40.0);
        hotel2.addRoom();
        hotel2.bookRoom(11, 0);

        booking = new Booking();
        booking.addHotel(hotel0);
        booking.addHotel(hotel1);
        booking.addHotel(hotel2);
    }

    @Test
    public void t_cheapestDay10()
    {
        int hotelID = booking.findCheapestHotel(10);

        // Hotel 1 has 3 rooms, but all rooms booked at day 10
        // Hotel 2 has 2 rooms, but all rooms booked at day 10
        // Hotel 3 has 1 room, but is not booked at day 10
        // -> Should result in hotelID = 2
        assertEquals("Testing hotelID on day 10 - should result in ID 2",
                2, hotelID);
    }

    @Test
    public void t_cheapestDay11()
    {
        int hotelID = booking.findCheapestHotel(11);

        // Hotel 1 has 3 rooms, but all rooms booked at day 11
        // Hotel 2 has 2 rooms, only room 1 is available -> 46.99 per day
        // Hotel 3 has 1 room, but no rooms available
        // -> Should result in hotelID = 0
        assertEquals("Testing hotelID on day 11 - should result in ID 1",
                1, hotelID);
    }

    @Test
    public void t_cheapestDay12()
    {
        int hotelID = booking.findCheapestHotel(12);

        // Hotel 1 has 3 rooms, rooms 0 and 1 available -> 30 per day
        // Hotel 2 has 2 rooms, only room 0 available -> 46.99 per day
        // Hotel 3 has 1 room, room 0 available -> 40 per day
        // -> Should result in hotelID = 0
        assertEquals("Testing hotelID on day 12 - should result in ID 0",
                0, hotelID);
    }
}
