package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.UI.windows.PersonWindow;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PersonDBPanel extends JPanel implements ActionListener
{
    private ArrayList<Person> personArrayList = new ArrayList<>();
    private DefaultListModel<Person> listModel = new DefaultListModel<>();
    private JList<Person> list;

    public PersonDBPanel()
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        JLabel label = new JLabel("Persons");
        this.add(label,c);

        Database.getPersonDB().forEach(personArrayList::add);

        personArrayList.forEach(listModel::addElement);
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
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
        c.gridx = 1;
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

            case "-":
                if(list.getSelectedValue() != null)
                Database.getPersonDB().remove(list.getSelectedValue().getId());
        }
        refresh();
    }
}
