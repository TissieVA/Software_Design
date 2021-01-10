package ua.tijsva.sd.project.UI.windows;

import ua.tijsva.sd.project.UI.panels.EqualSplitPanel;
import ua.tijsva.sd.project.UI.panels.UnequalSplitPanel;
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

    private Controller controller;
    private GridBagConstraints c = new GridBagConstraints();
    private EqualSplitPanel esp = new EqualSplitPanel();
    private UnequalSplitPanel usp = new UnequalSplitPanel();
    private JSpinner priceField;
    private JTextField textField;
    private JComboBox<Object> paidPersonComboBox;
    private JButton confirmButton;
    private boolean isEqualSplitTicket;


    public TicketWindow(Controller controller)
    {
        super("MoneySplitter");
        this.controller = controller;
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        this.textField = new JTextField(25);
        addComponent(this.textField,1,1,1,1,inset,true);

        label = new JLabel("Who paid? How much?");
        addComponent(label,2,1,1,1,inset,false);


        ArrayList<Object> personArrayList = new ArrayList<>();
        Database.getPersonDB().forEach(personArrayList::add);
        this.paidPersonComboBox = new JComboBox<Object>( personArrayList.toArray());
        JScrollPane personPane = new JScrollPane(paidPersonComboBox);

        SpinnerNumberModel model = new SpinnerNumberModel(0,0,Double.POSITIVE_INFINITY,1);
        this.priceField = new JSpinner(model);
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

        confirmButton = new JButton("Create Ticket");
        confirmButton.addActionListener(this);
        addComponent(confirmButton,8,2,1,1,inset,false);
        confirmButton.setEnabled(false);


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
        switch(e.getActionCommand())
        {
            case "Equal Split Ticket":
                this.confirmButton.setEnabled(true);

                this.remove(usp);
                addComponent(esp, 7, 0, 3, 1, new Insets(10, 0, 10, 10), false);
                priceField.setVisible(true);
                isEqualSplitTicket = true;
                break;

            case "Unequal Split Ticket":
                this.confirmButton.setEnabled(true);

                this.remove(esp);
                addComponent(usp, 7, 0, 3, 1, new Insets(10, 10, 10, 10), false);
                priceField.setVisible(false);
                priceField.setValue(0);
                isEqualSplitTicket = false;
                break;

            case "Cancel":
                this.setVisible(false);
                this.dispose();
                break;

            case "Create Ticket":
                if(isEqualSplitTicket)
                {
                    esp.create(this.textField.getText(), (Person) this.paidPersonComboBox.getSelectedItem(), (Double) this.priceField.getValue(),this.controller);
                    this.setVisible(false);
                    this.dispose();
                }
                else {
                    usp.create(this.textField.getText(), (Person) this.paidPersonComboBox.getSelectedItem(), 0.0, this.controller);
                    this.setVisible(false);
                    this.dispose();
                }
                break;

            default:
                System.out.println(e.getActionCommand());
                break;

        }
        SwingUtilities.updateComponentTreeUI(this);

    }
}
