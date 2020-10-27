package classdiagrams.interfaces;

import java.util.ArrayList;

public class Remote
{
    private ArrayList<VolumeDevice> volumeDevices;

    public Remote()
    {
        volumeDevices = new ArrayList<VolumeDevice>();
    }

    public void addDevice(VolumeDevice volumeDevice)
    {
        this.volumeDevices.add(volumeDevice);
    }

    public void lowerVolume()
    {
        for (VolumeDevice device: this.volumeDevices)
        {
            device.volumeDown();
        }
    }

    public void increaseVolume()
    {
        for(VolumeDevice device: this.volumeDevices)
        {
            device.volumeUp();
        }
    }
}
