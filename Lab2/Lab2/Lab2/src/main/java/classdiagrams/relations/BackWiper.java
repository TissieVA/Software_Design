package classdiagrams.relations;

public class BackWiper implements Wiper
{
    public BackWiper()
    {

    }

    @Override
    public void wipe() {
        System.out.println("Wiping back window");
    }
}
