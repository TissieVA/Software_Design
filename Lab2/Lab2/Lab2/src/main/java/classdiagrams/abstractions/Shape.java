package classdiagrams.abstractions;

public abstract class Shape
{
    protected double size;
    private String name;

    public Shape(double size, String name) {
        this.size = size;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calcCircumference();
    public abstract double calcArea();

}
