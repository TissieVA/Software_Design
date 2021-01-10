package ua.tijsva.sd.project.UI.panels;


import ua.tijsva.sd.project.UI.windows.TicketWindow;
import ua.tijsva.sd.project.controller.Controller;
import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class TicketDBPanel extends JPanel implements ActionListener, Observer
{
    private Controller controller;
    private DefaultListModel<Ticket> listModel = new DefaultListModel<>();
    private JList<Ticket> list;

    public TicketDBPanel(Controller controller)
    {
        Database.getTicketDB().addObserver(this);

        this.controller = controller;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        JLabel label = new JLabel("Tickets");
        this.add(label,c);


        Database.getTicketDB().forEach(listModel::addElement);
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        c.gridx = 1;
        c.gridy = 1;
        this.add(list,c);

        JButton addTicketButton = new JButton("+");
        addTicketButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 0;
        this.add(addTicketButton,c);

        JButton removeTicketButton = new JButton("-");
        removeTicketButton.addActionListener(this);
        c.gridy = 2;
        c.gridx = 2;
        this.add(removeTicketButton,c);
    }

    public void refresh()
    {
        listModel.clear();
        Database.getTicketDB().forEach(listModel::addElement);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "+":
                new TicketWindow(controller);
                break;

            case "-":
                if(list.getSelectedValue() != null)
                    Database.getTicketDB().remove(list.getSelectedValue().getId());
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
