package ua.tijsva.sd.project.UI;

import ua.tijsva.sd.project.UI.panels.PersonDBPanel;
import ua.tijsva.sd.project.UI.panels.TicketDBPanel;
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
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialise();

        this.setVisible(true);
    }

    private void initialise()
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        this.add(new PersonDBPanel(),c);

        c.gridx = 3;
        c.gridy = 0;
        this.add(new TicketDBPanel(controller),c);

    }




    @Override
    public void update(Observable o, Object arg)
    {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
