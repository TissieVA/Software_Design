package classdiagrams.interfaces;

import org.junit.Assert;
import org.junit.Test;

public class Remote_UTest
{
    @Test
    public void t_lowerVolume()
    {
        Remote remoteUnderTest = new Remote();
        TelenetDigibox digibox = new TelenetDigibox(5);
        BluetoothSpeaker speaker = new BluetoothSpeaker(10);
        remoteUnderTest.addDevice(digibox);
        remoteUnderTest.addDevice(speaker);
        remoteUnderTest.lowerVolume();
        Assert.assertEquals("Testing if volume went down on digibox",4,digibox.getVolume(),0.001);
        Assert.assertEquals("Testing if volume went down on speaker",9,speaker.getVolume(),0.001);
        remoteUnderTest.increaseVolume();
        remoteUnderTest.increaseVolume();
        Assert.assertEquals("Testing if volume increased on digibox",6,digibox.getVolume(),0.001);
        Assert.assertEquals("Testing if volume increased on speaker",10,speaker.getVolume(),0.001);

    }
}
