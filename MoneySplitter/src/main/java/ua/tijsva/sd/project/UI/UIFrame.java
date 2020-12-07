package ua.tijsva.sd.project.UI;

import ua.tijsva.sd.project.UI.panels.DatabasePanel;
import ua.tijsva.sd.project.controller.Controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;
 //https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
//https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java

public class UIFrame implements Observer
{
    private static Controller controller = new Controller();


    public static void addComponentsToPane(Container pane)
    {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton button;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;

        pane.add(new DatabasePanel(controller));

    }

    public void createAndShowGUI()
    {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.

        //frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg)
    {

    }
}
