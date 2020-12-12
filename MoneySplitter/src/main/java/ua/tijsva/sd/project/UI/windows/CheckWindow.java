package ua.tijsva.sd.project.UI.windows;

import ua.tijsva.sd.project.check.Check;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class CheckWindow extends JFrame implements ActionListener, Observer
{

    public CheckWindow()
    {
        super("MoneySplitter");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Database.getTicketDB().addObserver(this);
        Database.getPersonDB().addObserver(this);

        initialise();
        this.setVisible(true);
    }


    private void initialise()
    {
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        c.gridx=1;
        c.gridy=0;
        JLabel label = new JLabel("The Calculation");
        this.add(label,c);



        Check check = new Check(Database.getTicketDB(), Database.getPersonDB());
        JTextArea textArea = new JTextArea(check.print());
        c.gridy = 1;
        this.add(textArea, c);

        c.gridx = 0;
        c.gridy = 2;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        this.add(backButton, c);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Back"))
        {
            this.setVisible(false);
            this.dispose();
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.setVisible(false);
        this.dispose();
    }
}
