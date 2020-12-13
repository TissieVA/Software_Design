package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.UI.windows.PersonWindow;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class PersonDBPanel extends JPanel implements ActionListener, Observer
{
    private ArrayList<Person> personArrayList = new ArrayList<>();
    private DefaultListModel<Person> listModel = new DefaultListModel<>();
    private JList<Person> list;

    public PersonDBPanel()
    {
        Database.getPersonDB().addObserver(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        JLabel label = new JLabel("Persons");
        this.add(label,c);


        Database.getPersonDB().forEach(personArrayList::add);

        personArrayList.forEach(listModel::addElement);
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        c.gridx = 1;
        c.gridy = 1;
        this.add(list,c);

        JButton addPersonButton = new JButton("+");
        addPersonButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        this.add(addPersonButton,c);

        JButton removePersonButton = new JButton("-");
        removePersonButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 2;
        this.add(removePersonButton,c);


    }

    public void refresh()
    {
        personArrayList.clear();
        Database.getPersonDB().forEach(personArrayList::add);
        listModel.clear();
        personArrayList.forEach(listModel::addElement);
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "+":
                PersonWindow pw = new PersonWindow();
                break;

            case "-":
                AtomicBoolean error = new AtomicBoolean(false);
                if(list.getSelectedValue() != null) {
                    Database.getTicketDB().forEach(ticket ->
                    {
                        if (ticket.getPaidPerson().equals(list.getSelectedValue().getId()))
                            error.set(true);
                        else if (ticket.getIndebted().containsKey(list.getSelectedValue().getId()))
                            error.set(true);
                    });

                    if(!error.get())
                        Database.getPersonDB().remove(list.getSelectedValue().getId());
                    else
                        JOptionPane.showMessageDialog(null,"Remove the tickets that contains this person", "Warning", JOptionPane.WARNING_MESSAGE);

                }
                break;
        }
        refresh();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        refresh();
    }
}
