package factory;

import tesla.Tesla;
import tesla.model_3.Model3_Black;
import tesla.model_3.Model3_Red;
import tesla.model_s.ModelS_Black;
import tesla.model_s.ModelS_Red;

public class Model3Factory implements TeslaFactory
{

    @Override
    public Tesla getRedCar(String name) {
        return new Model3_Black(name);
    }

    @Override
    public Tesla getBlackCar(String name) {
        return new Model3_Red(name);
    }
}
