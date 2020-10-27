package classdiagrams.abstractions;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.mockito.*;

public class Shape_UTest
{
    public Shape_UTest()
    {
        // Intentionally left blank
    }

    @Before
    public void initialize()
    {

    }

    @Test
    public void t_getName()
    {
        // Write 4d instead of 4 in constructor -> 4d defines number as double instead of integer
        Shape shapeUnderTest = Mockito.mock(Shape.class,
                Mockito.withSettings()
                        .useConstructor(4d, "shape123")
                        .defaultAnswer(Mockito.CALLS_REAL_METHODS));

        Assert.assertEquals("Testing naming - input=shape123 - should be shape123", "shape123", shapeUnderTest.getName());
    }
}
