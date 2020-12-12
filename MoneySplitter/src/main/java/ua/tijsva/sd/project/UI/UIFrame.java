package ua.tijsva.sd.project.UI;

import ua.tijsva.sd.project.UI.panels.PersonDBPanel;
import ua.tijsva.sd.project.UI.panels.TicketDBPanel;
import ua.tijsva.sd.project.UI.windows.CheckWindow;
import ua.tijsva.sd.project.check.Check;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
 //https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
//https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java

public class UIFrame extends JFrame implements Observer, ActionListener
{
    private static Controller controller = new Controller();
    private CheckWindow cw;

    public UIFrame()
    {
        super("MoneySplitter");
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Database.getTicketDB().addObserver(this);
        Database.getPersonDB().addObserver(this);

        initialise();

        this.setVisible(true);
    }

    private void initialise()
    {
        this.setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new PersonDBPanel(), new TicketDBPanel(controller));
        splitPane.setResizeWeight(0.4);
        this.add(splitPane,BorderLayout.CENTER);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Sans", Font.PLAIN, 50));
        calculateButton.addActionListener(this);
        this.add(calculateButton,BorderLayout.SOUTH);

    }

    @Override
    public void update(Observable o, Object arg)
    {
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Calculate"))
            cw = new CheckWindow();
    }
}
