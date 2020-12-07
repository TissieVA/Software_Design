package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EqualSplitPanel extends JFrame implements ActionListener
{
    public EqualSplitPanel()
    {
        this.setLayout(new GridBagLayout());
        Insets inset = new Insets(10,10,10,10);

        JLabel label = new JLabel("Add persons to ticket");
        addComponent(label,0,0,1,1,inset,false);

        ArrayList<Object> personArrayList = new ArrayList<>();
        Database.getPersonDB().forEach(personArrayList::add);
        JComboBox<Object> personComboBox = new JComboBox<Object>( personArrayList.toArray());
        addComponent(personComboBox,1,0,1,1, inset, true);


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

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
