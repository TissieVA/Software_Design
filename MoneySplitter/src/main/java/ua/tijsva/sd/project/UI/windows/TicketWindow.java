package ua.tijsva.sd.project.UI.windows;

import ua.tijsva.sd.project.UI.panels.EqualSplitPanel;
import ua.tijsva.sd.project.UI.panels.UnequalSplitPanel;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TicketWindow extends JFrame implements ActionListener
{
    protected JTextField textfield;
    private Controller controller;
    private GridBagConstraints c = new GridBagConstraints();
    private EqualSplitPanel esp = new EqualSplitPanel();
    private UnequalSplitPanel usp = new UnequalSplitPanel();
    private boolean esTicket;


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
        this.setSize(700,700);
        Insets inset = new Insets(10,10,10,10);

        JLabel label = new JLabel("Ticket name");
        addComponent(label,0,1,1,1,inset,false);

        JTextField textfield = new JTextField(25);
        addComponent(textfield,1,1,1,1,inset,true);

        label = new JLabel("Who paid? How much?");
        addComponent(label,2,1,1,1,inset,false);


        ArrayList<Object> personArrayList = new ArrayList<>();
        Database.getPersonDB().forEach(personArrayList::add);
        JComboBox<Object> paidPersonComboBox = new JComboBox<Object>( personArrayList.toArray());
        JScrollPane personPane = new JScrollPane(paidPersonComboBox);
        //addComponent(paidPersonComboBox,3,0,2,1,inset,true);

        SpinnerNumberModel model = new SpinnerNumberModel(50,0,Double.POSITIVE_INFINITY,1);
        JSpinner priceField = new JSpinner(model);
        JScrollPane pricePane = new JScrollPane(priceField);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,personPane,pricePane);
        splitPane.setResizeWeight(0.3);
        addComponent(splitPane,3,0,3,1,inset,true);

        addComponent(new JSeparator(JSeparator.HORIZONTAL),4,0,3,1,inset, true);

        JRadioButton equalTicketButton = new JRadioButton("Equal Split Ticket");
        JRadioButton unEqualTicketButton = new JRadioButton("Unequal Split Ticket");
        ButtonGroup group = new ButtonGroup();
        group.add(equalTicketButton);
        group.add(unEqualTicketButton);
        addComponent(equalTicketButton,5,1,1,1,inset,false);
        addComponent(unEqualTicketButton,6,1,1,1,inset,false);
        equalTicketButton.addActionListener(this);
        unEqualTicketButton.addActionListener(this);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        addComponent(cancelButton,8,0,1,1,inset,false);

        JButton confirmButton = new JButton("Create Ticket");
        confirmButton.addActionListener(this);
        addComponent(confirmButton,8,2,1,1,inset,false);


    }

    private void addComponent(Component component,int row, int column, int width, int height, Insets insets, boolean fill)
    {
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
        //TODO: add Unequal split panel, remove it at if statemant and add it at else if statement
        switch(e.getActionCommand())
        {
            case "Equal Split Ticket":
                this.remove(usp);
                addComponent(esp, 7, 0, 3, 1, new Insets(10, 0, 10, 10), false);
                esTicket = true;
                break;

            case "Unequal Split Ticket":
                this.remove(esp);
                addComponent(usp, 7, 0, 3, 1, new Insets(10, 10, 10, 10), false);
                esTicket = false;
                break;

            case "Cancel":
                this.setVisible(false);
                this.dispose();

            case "Create Ticket":
                if(esTicket)
                {
                    //controller.createTicket()
                }


            default:
                System.out.println(e.getActionCommand());

        }
        SwingUtilities.updateComponentTreeUI(this);

    }
}
