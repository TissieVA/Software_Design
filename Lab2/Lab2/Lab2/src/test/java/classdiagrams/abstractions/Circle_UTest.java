package classdiagrams.abstractions;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class Circle_UTest
{
    public Circle_UTest()
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
        Circle circleUnderTest = new Circle(3, "circleUnderTest");
        Assert.assertEquals("Testing circumference - input=3 - should be 2*PI*3", Math.PI * 2 * 3, circleUnderTest.calcCircumference(), 0.001);

        circleUnderTest = new Circle(4, "circleUnderTest");
        Assert.assertEquals("Testing circumference - input=4 - should be 2*PI*4", Math.PI * 2 * 4, circleUnderTest.calcCircumference(), 0.001);
    }

    @Test
    public void t_calcArea()
    {
        Circle circleUnderTest = new Circle(3, "circleUnderTest");
        Assert.assertEquals("Testing area - input=3 - should be PI*3*3", Math.PI * 3 * 3, circleUnderTest.calcArea(), 0.001);

        circleUnderTest = new Circle(4, "circleUnderTest");
        Assert.assertEquals("Testing area - input=4 - should be PI*4*4", Math.PI * 4 * 4, circleUnderTest.calcArea(), 0.001);
    }

    @Test
    public void t_getName()
    {
        Circle circleUnderTest = new Circle(3, "circle123");
        Assert.assertEquals("Testing name - input=circle123, should be circle123", "circle123", circleUnderTest.getName());
    }
}
