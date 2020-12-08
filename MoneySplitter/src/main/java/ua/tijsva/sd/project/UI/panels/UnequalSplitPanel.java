package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UnequalSplitPanel extends JPanel implements ActionListener
{
    private ArrayList<Object> personArrayList = new ArrayList<>();
    private ArrayList<JSplitPane> splitPanesArray = new ArrayList<>();
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

        JScrollPane personPane = new JScrollPane(new JComboBox<Object>( personArrayList.toArray()));

        SpinnerNumberModel model = new SpinnerNumberModel(50,0,Double.POSITIVE_INFINITY,1);
        JSpinner priceField = new JSpinner(model);
        JScrollPane pricePane = new JScrollPane(priceField);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, personPane, priceField);
        splitPane.setResizeWeight(0.3);
        splitPanesArray.add(splitPane);
        i++;
        addComponent(splitPane,row=row+1,0,3,1,new Insets(10,10,10,10),true);

        this.remove(addPersonButton);
        this.remove(removePersonButton);
        addComponent(addPersonButton,row=row+1,0,1,1, new Insets(10,10,10,10),false);
        addComponent(removePersonButton,row,1,1,1,new Insets(10,10,10,10),false);
    }

    public void lessPersonLine()
    {
        if(i>1) {
            i--;
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
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("+"))
            extraPersonLine();
        else if(e.getActionCommand().equals("-"))
            lessPersonLine();


        SwingUtilities.updateComponentTreeUI(this);
    }

}
