package diagrams;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Hotel_UTest
{
    public Hotel_UTest()
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
    public void t_addRoom() throws NoSuchFieldException, IllegalAccessException
    {
        // Access the private field inside the Hotel class
        Field field = Hotel.class.getDeclaredField("rooms");
        field.setAccessible(true);

        Hotel hotelUnderTest = new Hotel(45);
        HashMap<Integer, Room> mockMap = new HashMap<>();
        field.set(hotelUnderTest, mockMap);

        // Test function for first time
        hotelUnderTest.addRoom();
        assertEquals("Testing size of rooms", 1, mockMap.size());
        assertThat(mockMap.get(0), instanceOf(Room.class));

        // Test function for second time
        hotelUnderTest.addRoom();
        assertEquals("Testing size of room for 2nd time", 2, mockMap.size());
        assertThat(mockMap.get(0), instanceOf(Room.class));
        assertThat(mockMap.get(1), instanceOf(Room.class));
        assertNotSame(mockMap.get(0), mockMap.get(1));
    }

    @Test
    public void t_checkAvailability() throws NoSuchFieldException, IllegalAccessException
    {
        // Access the private field inside the Hotel class
        Field field = Hotel.class.getDeclaredField("rooms");
        field.setAccessible(true);

        Hotel hotelUnderTest = new Hotel(45);
        HashMap<Integer, Room> mockMap = new HashMap<>();

        // Populate map with rooms
        Room room1 = new Room();
        room1.setBooking(26, true);
        room1.setBooking(27, true);
        room1.setBooking(28, false);

        Room room2 = new Room();

        room2.setBooking(25, true);
        room2.setBooking(26, true);
        room2.setBooking(28, false);

        mockMap.put(0, room1);
        mockMap.put(1, room2);
        field.set(hotelUnderTest, mockMap);

        // Test availability
        assertNotEquals("Testing availability - should not return -1",
                -1, hotelUnderTest.checkAvailability(25));
        assertEquals("Testing availability - date 25 - should return 0",
                0, hotelUnderTest.checkAvailability(25));
        assertEquals("Testing availability - date 27 - should return 1",
                1, hotelUnderTest.checkAvailability(27));
        assertEquals("Testing availability - date 28 - should return 0 or 1",
                0, hotelUnderTest.checkAvailability(28));
        assertEquals("Testing availability - date 26 - should return -1",
                -1, hotelUnderTest.checkAvailability(26));
        assertEquals("Testing availability - date 24 - should return 0 or 1",
                0, hotelUnderTest.checkAvailability(24));
    }

    @Test
    public void t_bookRoom() throws NoSuchFieldException, IllegalAccessException
    {
        // Access the private field inside the Hotel class
        Field field = Hotel.class.getDeclaredField("rooms");
        field.setAccessible(true);

        Hotel hotelUnderTest = new Hotel(45);
        HashMap<Integer, Room> mockMap = new HashMap<>();

        // Populate map with rooms
        Room room1 = new Room();
        room1.setBooking(26, true);
        room1.setBooking(27, false);

        Room room2 = new Room();
        room2.setBooking(25, true);
        room2.setBooking(28, false);

        mockMap.put(0, room1);
        mockMap.put(1, room2);
        field.set(hotelUnderTest, mockMap);

        // Test function
        assertEquals("Testing booking - date 25 - room 0 - should return 0",
                0, hotelUnderTest.bookRoom(25, 0));
        assertEquals("Testing booking - date 25 - room 0 - should return -1",
                -1, hotelUnderTest.bookRoom(25, 0));
        assertEquals("Testing booking - date 25 - room 1 - should return -1",
                -1, hotelUnderTest.bookRoom(25, 1));

        assertEquals("Testing booking - date 26 - room 0 - should return -1",
                -1, hotelUnderTest.bookRoom(26, 0));
        assertEquals("Testing booking - date 26 - room 1 - should return 0",
                0, hotelUnderTest.bookRoom(26, 1));
        assertEquals("Testing booking - date 26 - room 1 - should return -1",
                -1, hotelUnderTest.bookRoom(26,1));

        assertEquals("Testing booking - date 27 - room 0 - should return 0",
                0, hotelUnderTest.bookRoom(27, 0));
        assertEquals("Testing booking - date 27 - room 1 - should return 0",
                0, hotelUnderTest.bookRoom(27, 1));
        assertEquals("Testing booking - date 27 - room 0 - should return -1",
                -1, hotelUnderTest.bookRoom(27, 0));
        assertEquals("Testing booking - date 27 - room 1 - should return -1",
                -1, hotelUnderTest.bookRoom(27, 1));
    }

    @Test
    public void t_getPricePerRoom() throws IllegalAccessException, NoSuchFieldException
    {
        // Access the private field inside the Hotel class
        Field field = Hotel.class.getDeclaredField("pricePerRoom");
        field.setAccessible(true);

        // Test getting value set via constructor
        Hotel hotelUnderTest = new Hotel(45);
        assertEquals("Testing getting value - set via constructor - expected 45",
                45, hotelUnderTest.getPricePerRoom(), 0.001);

        // Test getting value set via Field
        field.setDouble(hotelUnderTest, 32.05);
        assertEquals("Testing getting value - set via Field - expected 32.05",
                32.05, hotelUnderTest.getPricePerRoom(), 0.001);
    }
}
