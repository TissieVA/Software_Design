package view;

import controller.RegistrationController;
import database.Database;
import database.RegistrationDB;
import employee.Employee;
import register_entry.RegisterEntry;
import view.panels.ListPanel;
import view.panels.NamePanel;
import view.panels.RegistrationButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewFrame extends JFrame implements Observer
{
    // Get your controller in this private field
    RegistrationController registrationController;
    ListPanel panel;
    NamePanel namePanel;
    RegistrationButtonPanel buttons;

    public ViewFrame(RegistrationController registrationController)
    {
        super("Registration");
        this.registrationController = registrationController;
    }

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel(registrationController);
        panel = new ListPanel();
        namePanel = new NamePanel();

        this.add(panel);
        this.add(namePanel);
        this.add(buttons);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        RegisterEntry entry = RegistrationDB.getInstance().getEntry((Employee) arg);
        this.panel.addEntry(entry);

        Employee e = (Employee) arg;
        String nameEntry=(e.getName() +
                " (" + e.getFunction() + ")" +
                " " + entry);
        this.namePanel.addEntry(nameEntry);
    }
}
