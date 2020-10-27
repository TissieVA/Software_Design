package classdiagrams.abstractions;

public class Square extends Shape
{

    public Square(double size, String name) {
        super(size, name);
    }

    public double calcCircumference()
    {
        return 4*size;
    }

    public double calcArea()
    {
        return size*size;
    }
}
