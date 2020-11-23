package factory;

import tesla.Tesla;
import tesla.model_s.ModelS_Black;
import tesla.model_s.ModelS_Red;

public class ModelSFactory implements TeslaFactory
{

    @Override
    public Tesla getRedCar(String name) {
        return new ModelS_Black(name);
    }

    @Override
    public Tesla getBlackCar(String name) {
        return new ModelS_Red(name);
    }
}
