import factory.FactoryProvider;
import factory.TeslaFactory;
import tesla.Tesla;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    List<Tesla> teslaList;

    public static void main(String[] args)
    {
        try {
            Main main = new Main();
            main.initialize();
            main.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Main()
    {

    }

    public void initialize()
    {
        teslaList = new ArrayList<>();

        // CAUTION:
        // "factory" does not exist yet, you have to obtain it via the specific Factories

        // Via FactoryProvider: Get factory for Tesla Model S
        Tesla apollo = factory.getBlackCar("Apollo");
        Tesla athena = factory.getRedCar("Athena");

        teslaList.add(apollo);
        teslaList.add(athena);

        // Via FactoryProvider: Get factory for Tesla Model 3
        Tesla elon = factory.getRedCar("Elon");
        Tesla musk = factory.getBlackCar("Musk");

        teslaList.add(elon);
        teslaList.add(musk);

        // Via FactoryProvider: Get factory for Tesla Model X
        Tesla olympus = factory.getRedCar("Olympus");

        // Via FactoryProvider: Get factory for Tesla Model Y
        Tesla zeus = factory.getBlackCar("Zeus");

        teslaList.add(olympus);
        teslaList.add(zeus);
    }

    public void run() throws InterruptedException
    {
        System.out.println("Accelerating...");
        for(Tesla car: teslaList)
        {
            car.accelerate();
        }

        System.out.println("\nWaiting...");
        Thread.sleep(1000);

        System.out.println("\nDecelerating...");
        for (Tesla car: teslaList)
        {
            car.decelerate();
        }
    }

}
