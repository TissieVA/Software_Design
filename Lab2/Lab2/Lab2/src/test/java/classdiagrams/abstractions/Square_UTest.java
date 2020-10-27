package classdiagrams.abstractions;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class Square_UTest
{
    public Square_UTest()
    {
        // Intentionally left blank
    }

    @Before
    public void initialize()
    {

    }

    @Test
    public void t_calcCircumference()
    {
        Square squareUnderTest = new Square(3, "squareUnderTest");
        Assert.assertEquals("Testing circumference - input=3 - should be 12", 12, squareUnderTest.calcCircumference(), 0.001);

        squareUnderTest = new Square(4, "squareUnderTest");
        Assert.assertEquals("Testing circumference - input=4 - should be 16", 16, squareUnderTest.calcCircumference(), 0.001);

    }

    @Test
    public void t_calcArea()
    {
        Square squareUnderTest = new Square(3, "squareUnderTest");
        Assert.assertEquals("Testing area - input=3 - should be 9", 9, squareUnderTest.calcArea(), 0.001);

        squareUnderTest = new Square(4, "squareUnderTest");
        Assert.assertEquals("Testing area - input=4 - should be 16", 16, squareUnderTest.calcArea(), 0.001);
    }

    @Test
    public void t_getName()
    {
        Square squareUnderTest = new Square(3, "square123");
        Assert.assertEquals("Testing name - input=square123, should be square123", "square123", squareUnderTest.getName());
    }
}
