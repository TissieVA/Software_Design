package classdiagrams.relations;

public class BreakPedal extends Pedal
{
    public BreakPedal(Car car)
    {
        super(car);
    }

    @Override
    public void press()
    {
        car.decelerate();
    }
}
