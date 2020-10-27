package tesla.model_s;

import tesla.Tesla;

public abstract class ModelS extends Tesla
{
    public ModelS(String name)
    {
        super(name);
        this.model = "ModelS";
    }

    @Override
    protected void increaseSpeed()
    {
        this.speed += 10;
    }

    @Override
    protected void decreaseSpeed()
    {
        this.speed -= 10;
    }
}
