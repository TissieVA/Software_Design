package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EqualSplitPanel extends JPanel implements ActionListener
{
    private ArrayList<Object> personArrayList = new ArrayList<>();
    private ArrayList<JComboBox<Object>> comboBoxArray = new ArrayList<>();
    private int i=0;
    private int row=0;
    JButton addPersonButton = new JButton("+");

    public EqualSplitPanel()
    {
        this.setLayout(new GridBagLayout());
        Insets inset = new Insets(10,10,10,10);

        JLabel label = new JLabel("Add persons to ticket");
        addComponent(label,0,0,1,1,inset,false);


        Database.getPersonDB().forEach(personArrayList::add);
        extraPersonLine();

        addPersonButton.addActionListener(this);

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
        addComponent(addPersonButton,row=row+1,0,1,1, new Insets(10,10,10,10),false);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("+"))
            extraPersonLine();

        SwingUtilities.updateComponentTreeUI(this);
    }

    public ArrayList<JComboBox<Object>> getComboBoxArray()
    {
        return comboBoxArray;
    }
}
