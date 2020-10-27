package diagrams;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class RoomBook_ITest
{
    Booking booking;

    public RoomBook_ITest()
    {

    }

    @Before
    public void initialize()
    {
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
    public void t_bookAvailableRoom10()
    {
        // Cheapest room on day 10 is in hotelID 2
        int returnCode = booking.bookRoomInHotel(10, 2);

        assertEquals("Testing booking integration - day 10 - should return 0",
                0, returnCode);

        // Room is now booked. Trying to book again results in error
        returnCode = booking.bookRoomInHotel(10, 2);
        assertEquals("Testing booking integration - day 10 - should return in -1",
                -1, returnCode);
    }

    @Test
    public void t_fullyBooked()
    {

        int returnCode;
        
        // Hotel 2 should be the cheapest hotel on day 10
        // -> book a room in hotel 2 on day 10
        returnCode = booking.bookRoomInHotel(10, 2);
        assertEquals("Testing integration (1/6) - booking hotel 2 - should return 0",
                0, returnCode);

        // Booking again should return in -1
        returnCode = booking.bookRoomInHotel(10, 2);
        assertEquals("Testing integration (2/6) - booking hotel 2 - should return -1",
                -1, returnCode);

        // The next available room is in hotelID 1
        returnCode = booking.findCheapestHotel(10);
        assertEquals("Testing integration (3/6) - checking hotel 1 - should return 1",
                1, returnCode);

        // Book a room in hotel 1 on day 10
        returnCode = booking.bookRoomInHotel(10, 1);
        assertEquals("Testing integration (4/6) - booking hotel 1 - should return 0",
                0, returnCode);

        // Booking again should return in -1
        returnCode = booking.bookRoomInHotel(10, 1);
        assertEquals("Testing integration (5/6) - booking hotel 1 - should return -1",
                -1, returnCode);

        // Now there won't be any room available anymore
        returnCode = booking.findCheapestHotel(10);
        assertEquals("Testing integration (6/6) - checking availability - should return -1",
                -1, returnCode);
    }
}
