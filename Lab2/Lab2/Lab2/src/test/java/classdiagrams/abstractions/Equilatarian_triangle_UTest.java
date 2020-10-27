package classdiagrams.abstractions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Equilatarian_triangle_UTest
{
    public Equilatarian_triangle_UTest()
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
        Equilateral_triangle triangleUnderTest = new Equilateral_triangle(3, "triangleUnderTest");
        Assert.assertEquals("Testing circumference - input=3 - should be 3*3", 3 * 3, triangleUnderTest.calcCircumference(), 0.001);

        triangleUnderTest = new Equilateral_triangle(4, "triangleUnderTest");
        Assert.assertEquals("Testing circumference - input=4 - should be 3*4", 3 * 4, triangleUnderTest.calcCircumference(), 0.001);
    }

    @Test
    public void t_calcArea()
    {
        Equilateral_triangle triangleUnderTest = new Equilateral_triangle(3, "triangleUnderTest");
        Assert.assertEquals("Testing area - input=3 - should be 3.89711", 3.89711, triangleUnderTest.calcArea(), 0.001);

        triangleUnderTest = new Equilateral_triangle(4, "triangleUnderTest");
        Assert.assertEquals("Testing area - input=4 - should be 6.9282", 6.9282, triangleUnderTest.calcArea(), 0.001);
    }

    @Test
    public void t_getName()
    {
        Equilateral_triangle triangleUnderTest = new Equilateral_triangle(3, "triangle123");
        Assert.assertEquals("Testing name - input=triangle123, should be triangle123", "triangle123", triangleUnderTest.getName());
    }
}
