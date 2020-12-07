package ua.tijsva.sd.project.UI.windows;

import ua.tijsva.sd.project.UI.panels.EqualSplitPanel;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicketWindow extends JFrame implements ActionListener
{
    protected JTextField textfield;
    private Controller controller;

    public TicketWindow(Controller controller)
    {
        super("MoneySplitter");
        this.controller = controller;
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialise();
        this.setVisible(true);

    }

    private void initialise()
    {
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        this.setSize(700,500);
        Insets inset = new Insets(10,10,10,10);

        JLabel label = new JLabel("Ticket name");
        addComponent(label,0,2,1,1,inset,false);

        JTextField textfield = new JTextField(25);
        addComponent(textfield,1,2,1,1,inset,true);

        label = new JLabel("Who paid? How much?");
        addComponent(label,2,2,1,1,inset,false);

        ArrayList<Object> personArrayList = new ArrayList<>();
        Database.getPersonDB().forEach(personArrayList::add);
        JComboBox<Object> paidPersonComboBox = new JComboBox<Object>( personArrayList.toArray());
        addComponent(paidPersonComboBox,3,0,2,1,inset,true);

        SpinnerNumberModel model = new SpinnerNumberModel(50,0,Double.POSITIVE_INFINITY,1);
        JSpinner priceField = new JSpinner(model);
        addComponent(priceField,3,3,2,1,inset,true);

        addComponent(new JSeparator(JSeparator.HORIZONTAL),4,0,5,1,inset, true);

        JRadioButton equalTicketButton = new JRadioButton("Equal Split Ticket");
        equalTicketButton.setSelected(true);
        JRadioButton unEqualTicketButton = new JRadioButton("Unequal Split Ticket");
        ButtonGroup group = new ButtonGroup();
        group.add(equalTicketButton);
        group.add(unEqualTicketButton);
        addComponent(equalTicketButton,5,2,1,1,inset,false);
        addComponent(unEqualTicketButton,6,2,1,1,inset,false);
        equalTicketButton.addActionListener(this);
        unEqualTicketButton.addActionListener(this);


        addComponent(new EqualSplitPanel(),7,0,5,1,inset,false);

        JButton confirmButton = new JButton("Create Ticket");
        confirmButton.addActionListener(listener -> System.out.println(paidPersonComboBox.getSelectedItem()));
        addComponent(confirmButton,7,4,1,1,inset,false);


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
