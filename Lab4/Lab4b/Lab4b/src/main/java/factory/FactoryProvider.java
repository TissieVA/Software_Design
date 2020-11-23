package factory;

public class FactoryProvider
{
    // Use the following structure to make new factories in this provider.
    // Change the name from "YourNewFactory" to a more suitable name for your factories.



    public static TeslaFactory getModelSFactory()
    {
        return new ModelSFactory();
    }

    public static TeslaFactory getModel3Factory()
    {
        return new Model3Factory();
    }

    public static TeslaFactory getModelXFactory()
    {
        return new ModelXFactory();
    }

    public static TeslaFactory getModelYFactory()
    {
        return new ModelYFactory();
    }


    // In this way, the main function can call FactoryProvider.yourNewFactory() and gets a factory to work with.
}
