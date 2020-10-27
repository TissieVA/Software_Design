package classdiagrams.relations;

public class FrontWiper implements Wiper
{
    public FrontWiper()
    {

    }

    @Override
    public void wipe()
    {
        System.out.println("Wiping front window");
    }
}
