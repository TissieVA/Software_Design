package tesla.model_y;

import tesla.Tesla;

public abstract class ModelY extends Tesla
{
    public ModelY(String name)
    {
        super(name);
        this.model = "ModelY";
    }

    @Override
    protected void increaseSpeed()
    {
        this.speed += 6;
    }

    @Override
    protected void decreaseSpeed()
    {
        this.speed -= 6;
    }
}
