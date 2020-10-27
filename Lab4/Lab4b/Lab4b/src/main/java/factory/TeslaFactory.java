package factory;

import tesla.Tesla;

public interface TeslaFactory
{
    Tesla getRedCar(String name);
    Tesla getBlackCar(String name);
}
