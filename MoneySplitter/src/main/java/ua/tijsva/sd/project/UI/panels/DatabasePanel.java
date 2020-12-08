package ua.tijsva.sd.project.UI.panels;

import ua.tijsva.sd.project.UI.windows.TicketWindow;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabasePanel extends JPanel implements ActionListener
{

    private JButton addTicketButton;
    private JButton removeTicketButton;
    private JList<Ticket> jList;
    private DefaultListModel<Ticket> listModel;

    private Controller controller;

    public DatabasePanel(Controller controller)
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = new JLabel("Tickets");
        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);
        c.gridx = 0;
        c.gridy=0;
        this.add(label,c);
        c.gridy=1;
        this.add(jList,c);

        this.addTicketButton = new JButton("Add Ticket");
        c.weightx=0.5;
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        this.add(addTicketButton,c);
        this.addTicketButton.addActionListener(this);

        this.removeTicketButton = new JButton("Remove Ticket");
        c.gridx=1;
        c.gridy=2;
        this.add(removeTicketButton,c);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Add Ticket"))
        {
            TicketWindow tw = new TicketWindow(controller);
        }
    }
}
