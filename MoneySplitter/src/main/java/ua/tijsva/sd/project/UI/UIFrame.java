package ua.tijsva.sd.project.UI;

import ua.tijsva.sd.project.UI.panels.DatabasePanel;
import ua.tijsva.sd.project.UI.windows.TicketWindow;
import ua.tijsva.sd.project.controller.Controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;
 //https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
//https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java

public class UIFrame extends JFrame implements Observer
{
    private static Controller controller = new Controller();


    public UIFrame()
    {
        super("MoneySplitter");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialise();

        this.setVisible(true);
    }

    private void initialise()
    {
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        JButton calculateButton;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        calculateButton = new JButton("CALCULATE");
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 2;
        c.gridy = 3;
        this.add(calculateButton,c);

        c.gridx = 1;
        c.gridy = 2;

        this.add(new DatabasePanel(controller));
    }




    @Override
    public void update(Observable o, Object arg)
    {

    }
}
