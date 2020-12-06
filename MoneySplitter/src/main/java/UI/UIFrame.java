package UI;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;
 //https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
//https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java

public class UIFrame extends JFrame implements Observer
{
    public static void addComponentsToPane(Container pane)
    {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton button;

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;



    }
    @Override
    public void update(Observable o, Object arg)
    {

    }
}
