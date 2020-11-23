package factory;

import tesla.Tesla;
import tesla.model_3.Model3_Black;
import tesla.model_3.Model3_Red;
import tesla.model_y.ModelY_Black;
import tesla.model_y.ModelY_Red;

public class ModelYFactory implements TeslaFactory
{

    @Override
    public Tesla getRedCar(String name) {
        return new ModelY_Black(name);
    }

    @Override
    public Tesla getBlackCar(String name) {
        return new ModelY_Red(name);
    }
}
