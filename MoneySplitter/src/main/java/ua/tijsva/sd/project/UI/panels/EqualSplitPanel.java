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
    private ArrayList<Person> remainingPersons = new ArrayList<>();
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

    private void remainingPersonsUpdate()
    {
        remainingPersons.clear();
        Database.getPersonDB().forEach(remainingPersons::add);
        for (JComboBox<Object> cb : comboBoxArray)
        {
            remainingPersons.remove(cb.getSelectedItem());
        }
    }


    private void extraPersonLine()
    {
        personArrayList.clear();
        Database.getPersonDB().forEach(personArrayList::add);
        if (comboBoxArray.size() < personArrayList.size()) {

            if (comboBoxArray.size() > 0) {
                comboBoxArray.get(i - 1).setEnabled(false);
            }
            remainingPersonsUpdate();

            JComboBox<Object> comboBox = new JComboBox<Object>(remainingPersons.toArray());
            comboBox.addActionListener(this);
            comboBoxArray.add(comboBox);
            addComponent(comboBoxArray.get(i), row = row + 1, 0, 1, 1, new Insets(10, 10, 10, 10), false);
            i++;
            this.remove(addPersonButton);
            this.remove(removePersonButton);
            addComponent(addPersonButton, row = row + 1, 0, 1, 1, new Insets(10, 10, 10, 10), false);
            addComponent(removePersonButton, row, 1, 1, 1, new Insets(10, 10, 10, 10), false);
        }
    }

    private void lessPersonLine()
    {
        if(i>1) {
            i--;
            this.comboBoxArray.get(i-1).setEnabled(true);
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
        EqualSplitTicket t = (EqualSplitTicket) controller.createTicket(etf, ticketType, paidPerson);
        t.setPrice(price);

        for (int j = 0; j < i; j++)
        {
            t.addIndebted((Person) comboBoxArray.get(j).getSelectedItem());
        }
        return t;
    }
}
