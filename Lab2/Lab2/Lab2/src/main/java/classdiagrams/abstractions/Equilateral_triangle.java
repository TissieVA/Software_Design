package classdiagrams.abstractions;

public class Equilateral_triangle extends Shape
{

    public Equilateral_triangle(double size, String name) {
        super(size, name);
    }

    public double calcCircumference()
    {
        return 3*size;
    }

    public double calcArea()
    {
        return (size/2) * Math.sqrt(size*size-(size*size/4));
    }

}
