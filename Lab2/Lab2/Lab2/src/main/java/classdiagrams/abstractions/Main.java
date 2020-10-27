package classdiagrams.abstractions;

public class Main
{

    public static void main(String[] args)
    {
        Main main = new Main();
        main.run();
    }

    public Main()
    {

    }

    public void run()
    {
        Shape circle1 = new Circle(1, "circle1");
        Shape circle2 = new Circle(2, "circle2");

        printShape(circle1);
        printShape(circle2);

        Shape square1 = new Square(4, "square1");
        Shape square2 = new Square(3, "square2");

        printShape(square1);
        printShape(square2);
    }

    public void printShape(Shape shape)
    {
        System.out.println("Shape with name " + shape.getName() +
                ": circumference = " + shape.calcCircumference() +
                ", area = " + shape.calcArea());
    }

}
