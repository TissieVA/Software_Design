package diagrams;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.*;

public class Room_UTest
{
    public Room_UTest()
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
    public void t_getBooking() throws NoSuchFieldException, IllegalAccessException
    {
        // Access the private field inside the Room class
        Field field = Room.class.getDeclaredField("bookSchedule");
        field.setAccessible(true);

        // Create a test unit of Room, and populate it with a mock hashmap
        Room roomUnderTest = new Room();
        HashMap<Long, Boolean> mockMap = new HashMap<>();
        mockMap.put((long) 4, true);
        mockMap.put((long) 5, false);
        field.set(roomUnderTest, mockMap);

        // Test the functions of the room
        assertTrue("Testing returning true", roomUnderTest.getBooking(4));
        assertFalse("Testing returning false", roomUnderTest.getBooking(5));
        assertFalse("Testing nonexisting date", roomUnderTest.getBooking(3));
    }

    @Test
    public void t_setBooking() throws NoSuchFieldException, IllegalAccessException
    {
        // Access the private field inside the Room class
        Field field = Room.class.getDeclaredField("bookSchedule");
        field.setAccessible(true);

        // Create a test unit of Room, and populate it with a mock hashmap
        Room roomUnderTest = new Room();
        HashMap<Long, Boolean> mockMap = new HashMap<>();
        field.set(roomUnderTest, mockMap);

        // Execute function under test
        roomUnderTest.setBooking(5, true);

        // Test if function executed successfully
        assertTrue(
                "Testing true booking of table",
                ((HashMap<Long, Boolean>) field.get(roomUnderTest)).getOrDefault((long) 5, false)
        );
    }
}
