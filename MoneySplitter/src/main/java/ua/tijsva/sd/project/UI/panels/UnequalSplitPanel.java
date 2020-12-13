package ua.tijsva.sd.project.UI.panels;

import javafx.scene.control.ComboBox;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;
import ua.tijsva.sd.project.ticket.Ticket;
import ua.tijsva.sd.project.ticket.UnequalSplitTicket;
import ua.tijsva.sd.project.ticket.UnequalTicketFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UnequalSplitPanel extends JPanel implements ActionListener, ITicketPanel
{
    private ArrayList<Person> personArrayList = new ArrayList<>();
    private ArrayList<Person> remainingPersons = new ArrayList<>();
    private ArrayList<JSplitPane> splitPanesArray = new ArrayList<>();
    private ArrayList<JComboBox<Object>> comboBoxArray = new ArrayList<>();
    private ArrayList<JSpinner> spinnersArray = new ArrayList<>();
    private int i=0;
    private int row=0;
    JButton addPersonButton = new JButton("+");
    JButton removePersonButton = new JButton("-");

    public UnequalSplitPanel()
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
        personArrayList.clear();
        Database.getPersonDB().forEach(personArrayList::add);

        if (comboBoxArray.size() < personArrayList.size()) {

            if (comboBoxArray.size() > 0) {
                comboBoxArray.get(i - 1).setEnabled(false);
            }

            remainingPersonsUpdate();
            JComboBox<Object> comboBox = new JComboBox<Object>(remainingPersons.toArray());
            comboBoxArray.add(comboBox);
            comboBox.addActionListener(this);
            JScrollPane personPane = new JScrollPane(comboBox);

            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, Double.POSITIVE_INFINITY, 1);
            JSpinner priceField = new JSpinner(model);
            spinnersArray.add(priceField);
            JScrollPane pricePane = new JScrollPane(priceField);

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, personPane, priceField);
            splitPane.setResizeWeight(0.3);
            splitPanesArray.add(splitPane);
            i++;
            addComponent(splitPane, row = row + 1, 0, 3, 1, new Insets(10, 10, 10, 10), true);

            this.remove(addPersonButton);
            this.remove(removePersonButton);
            addComponent(addPersonButton, row = row + 1, 0, 1, 1, new Insets(10, 10, 10, 10), false);
            addComponent(removePersonButton, row, 1, 1, 1, new Insets(10, 10, 10, 10), false);
        }
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

    private void lessPersonLine()
    {
        if(i>1) {

            i--;
            this.comboBoxArray.get(i-1).setEnabled(true);
            this.comboBoxArray.remove(i);
            this.spinnersArray.remove(i);
            this.remove(splitPanesArray.get(i));
            splitPanesArray.remove(i);

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
    public Ticket create(String ticketType, Person paidPerson, double Price, Controller controller)
    {
        UnequalTicketFactory utf = new UnequalTicketFactory();
        UnequalSplitTicket t = (UnequalSplitTicket) controller.createTicket(utf, ticketType, paidPerson);

        for (int j = 0; j < i; j++)
        {

            JSpinner spinner = (JSpinner) (this.spinnersArray.get(j));
            Double price = (Double) (spinner.getValue());

            t.addIndebted((Person) this.comboBoxArray.get(j).getSelectedItem(),price);

        }
        return t;
    }

}
