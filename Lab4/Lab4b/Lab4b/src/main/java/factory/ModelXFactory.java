package factory;

import tesla.Tesla;
import tesla.model_3.Model3_Black;
import tesla.model_3.Model3_Red;
import tesla.model_x.ModelX_Black;
import tesla.model_x.ModelX_Red;

public class ModelXFactory implements TeslaFactory
{

    @Override
    public Tesla getRedCar(String name) {
        return new ModelX_Black(name);
    }

    @Override
    public Tesla getBlackCar(String name) {
        return new ModelX_Red(name);
    }
}
