package classdiagrams.interfaces;

public class TelenetDigibox implements VolumeDevice
{
    private double volume;

    public TelenetDigibox(double volume)
    {
        this.volume = volume;
    }

    public double getVolume()
    {
        return volume;
    }

    @Override
    public void volumeUp()
    {
        if(this.volume<10)
            this.volume++;
    }

    @Override
    public void volumeDown()
    {
        if(this.volume>0)
            this.volume--;
    }
}
