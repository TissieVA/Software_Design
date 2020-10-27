package diagrams;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Booking_UTest
{
    public Booking_UTest()
    {
       /*
        Needs to stay empty.
        Necessary initializations need to be done in function 'initialize()'
        */
    }

    @Before
    public void initialize()
    {

    }

    @Test
    public void t_addHotel() throws IllegalAccessException, NoSuchFieldException
    {
        Field field = Booking.class.getDeclaredField("hotels");
        field.setAccessible(true);

        Booking bookingUnderTest = new Booking();
        Hotel mockHotel = mock(Hotel.class);
        HashMap<Integer, Hotel> mockMap = new HashMap<>();
        field.set(bookingUnderTest, mockMap);

        bookingUnderTest.addHotel(mockHotel);

        assertEquals("Testing size of mockMap - should be 1",
                1, mockMap.size());
        assertThat(mockMap.get(0), instanceOf(Hotel.class));

        bookingUnderTest.addHotel(mockHotel);
        assertEquals("Testing size of mockMap - should be 2",
                2, mockMap.size());
        assertThat(mockMap.get(1), instanceOf(Hotel.class));
    }

    @Test
    public void t_findCheapestHotel() throws IllegalAccessException, NoSuchFieldException
    {
        Field field = Booking.class.getDeclaredField("hotels");
        field.setAccessible(true);

        Booking bookingUnderTest = new Booking();
        HashMap<Integer, Hotel> mockHotels = new HashMap<>();

        // Configure hotels
        // Hotel 1 is not available on given date
        Hotel hotel1 = mock(Hotel.class);
        when(hotel1.checkAvailability(25)).thenReturn(-1);
        when(hotel1.getPricePerRoom()).thenReturn(30.0);

        // Hotel 2 is available, returns roomID 2
        Hotel hotel2 = mock(Hotel.class);
        when(hotel2.checkAvailability(25)).thenReturn(2);
        when(hotel2.getPricePerRoom()).thenReturn(45.0);

        // Hotel 3 is available, returns roomID 1
        Hotel hotel3 = mock(Hotel.class);
        when(hotel3.checkAvailability(25)).thenReturn(1);
        when(hotel3.getPricePerRoom()).thenReturn(32.05);

        // Put hotels into mock map
        mockHotels.put(0, hotel1);
        mockHotels.put(1, hotel2);
        mockHotels.put(2, hotel3);
        field.set(bookingUnderTest, mockHotels);

        assertEquals("Testing cheapest hotel - date 25 - should return hotelID 2",
                2, bookingUnderTest.findCheapestHotel(25));
    }

    @Test
    public void t_findCheapestHotel_nothingAvailable() throws IllegalAccessException, NoSuchFieldException {
        Field field = Booking.class.getDeclaredField("hotels");
        field.setAccessible(true);

        Booking bookingUnderTest = new Booking();
        HashMap<Integer, Hotel> mockHotels = new HashMap<>();

        // Configure hotels
        // Hotel 1 is not available on given date
        Hotel hotel1 = mock(Hotel.class);
        when(hotel1.checkAvailability(26)).thenReturn(-1);
        when(hotel1.getPricePerRoom()).thenReturn(30.0);

        // Hotel 2 is also not available on given date
        Hotel hotel2 = mock(Hotel.class);
        when(hotel2.checkAvailability(26)).thenReturn(-1);
        when(hotel2.getPricePerRoom()).thenReturn(45.0);

        assertEquals("Testing cheapest hotel - date 26 - should return -1",
                -1, bookingUnderTest.findCheapestHotel(-1));
    }

    @Test
    public void t_bookRoomInHotel() throws IllegalAccessException, NoSuchFieldException
    {
        Field field = Booking.class.getDeclaredField("hotels");
        field.setAccessible(true);

        Booking bookingUnderTest = new Booking();
        HashMap<Integer, Hotel> mockHotels = new HashMap<>();

        // Configure hotels
        // Hotel 1 is not available on date 25
        Hotel hotel1 = mock(Hotel.class);
        when(hotel1.checkAvailability(25)).thenReturn(-1);

        // Hotel 2 is available on date 25, will return roomID 4
        Hotel hotel2 = mock(Hotel.class);
        when(hotel2.checkAvailability(25)).thenReturn(4);
        when(hotel2.bookRoom(25, 4)).thenReturn(0);

        mockHotels.put(0, hotel1);
        mockHotels.put(1, hotel2);
        field.set(bookingUnderTest, mockHotels);

        // Test that function returns 0 in case of OK
        assertEquals("Testing return of booking - date 25 - should return 0",
                0, bookingUnderTest.bookRoomInHotel(25, 1));

        // Test that function bookRoom is actually called
        verify(hotel2, times(1)).bookRoom(25, 4);
    }

    @Test
    public void t_bookRoomInHotel_error() throws IllegalAccessException, NoSuchFieldException
    {
        Field field = Booking.class.getDeclaredField("hotels");
        field.setAccessible(true);

        Booking bookingUnderTest = new Booking();
        HashMap<Integer, Hotel> mockHotels = new HashMap<>();

        // Configure hotels
        // Hotel 1 is not available on date 25
        Hotel hotel1 = mock(Hotel.class);
        when(hotel1.checkAvailability(25)).thenReturn(-1);

        // Hotel 2 is available on date 25, will return roomID 4
        Hotel hotel2 = mock(Hotel.class);
        when(hotel2.checkAvailability(25)).thenReturn(4);
        when(hotel2.bookRoom(25, 4)).thenReturn(-1);

        mockHotels.put(0, hotel1);
        mockHotels.put(1, hotel2);
        field.set(bookingUnderTest, mockHotels);

        // Check that error gets propagated to bookRoomInHotel
        assertEquals("Testing return of booking - date 25 - should return -1",
                -1, bookingUnderTest.bookRoomInHotel(25, 1));

        // Make sure that function is actually called
        verify(hotel2, times(1)).bookRoom(25, 4);
    }
}
