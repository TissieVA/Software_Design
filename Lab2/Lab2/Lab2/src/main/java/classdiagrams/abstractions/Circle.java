package classdiagrams.abstractions;

public class Circle extends Shape
{

    public Circle(double size, String name) {
        super(size, name);
    }

    public double calcCircumference()
    {
        return Math.PI*2*size;
    }

    public double calcArea()
    {
        return Math.PI*size*size;
    }

}
