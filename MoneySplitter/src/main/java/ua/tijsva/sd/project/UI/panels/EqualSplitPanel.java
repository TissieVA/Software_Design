package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.UI.panels.ITicketPanel;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.EqualSplitTicket;
import ua.tijsva.sd.project.ticket.EqualTicketFactory;
import ua.tijsva.sd.project.ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EqualSplitPanel extends JPanel implements ActionListener, ITicketPanel
{
    private ArrayList<Object> personArrayList = new ArrayList<>();
    private ArrayList<JComboBox<Object>> comboBoxArray = new ArrayList<>();
    private int i=0;
    private int row=0;
    JButton addPersonButton = new JButton("+");
    JButton removePersonButton = new JButton("-");

    public EqualSplitPanel()
    {
        this.setLayout(new GridBagLayout());
        Insets inset = new Insets(10,10,10,10);

        JLabel label = new JLabel("Add persons to ticket");
        addComponent(label,0,0,1,1,new Insets(10,50,10,10),false);


        Database.getPersonDB().forEach(personArrayList::add);
        extraPersonLine();

        this.addPersonButton.addActionListener(this);
        this.removePersonButton.addActionListener(this);

    }

    private void addComponent(Component component,int row, int column, int width, int height, Insets insets, boolean fill)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = width;
        c.gridheight = height;
        c.insets = insets;
        if(fill)
            c.fill = GridBagConstraints.HORIZONTAL;
        else
            c.fill = GridBagConstraints.NONE;
        this.add(component, c);
    }


    public void extraPersonLine()
    {
        comboBoxArray.add(new JComboBox<Object>( personArrayList.toArray()));
        addComponent(comboBoxArray.get(i),row=row+1,0,1,1, new Insets(10,10,10,10),false);
        i++;
        this.remove(addPersonButton);
        this.remove(removePersonButton);
        addComponent(addPersonButton,row=row+1,0,1,1, new Insets(10,10,10,10),false);
        addComponent(removePersonButton,row,1,1,1,new Insets(10,10,10,10),false);
    }

    public void lessPersonLine()
    {
        if(i>1) {
            i--;
            this.remove(comboBoxArray.get(i));
            comboBoxArray.remove(i);

            row--;
            this.remove(addPersonButton);
            this.remove(removePersonButton);
            addComponent(addPersonButton, row = row + 1, 0, 1, 1, new Insets(10, 10, 10, 10), false);
            addComponent(removePersonButton, row, 1, 1, 1, new Insets(10, 10, 10, 10), false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("+"))
            extraPersonLine();
        else if(e.getActionCommand().equals("-"))
            lessPersonLine();


        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public Ticket create(String ticketType, Person paidPerson, double price, Controller controller)
    {
        EqualTicketFactory etf = new EqualTicketFactory();
        Ticket t1 = controller.createTicket(etf,"abc",paidPerson.getId());
        EqualSplitTicket t = (EqualSplitTicket) controller.createTicket(etf, ticketType, paidPerson);
        t.setPrice(price);

        for (int j = 0; j < i; j++)
        {
            t.addIndebted((Person) comboBoxArray.get(j).getSelectedItem());
        }
        return t;
    }
}
