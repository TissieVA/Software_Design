package view.panels;

import controller.RegistrationController;
import employee.Employee;
import employee.Programmer;
import factory.EmployeeFactory;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class RegistrationButtonPanel extends JPanel {

    private JButton checkIn;
    private JButton checkOut;

    // Get your controller in this private field
    private RegistrationController controller;


    // For now, just make a new employee in this class via your factory.
    // You can change this later on to a more unified way
    private Employee employee;

    // Get your controller in this class via the constructor
    public RegistrationButtonPanel(RegistrationController controller)
    {
        JLabel label = new JLabel("Registration buttons:");
        this.checkIn = new JButton("Check In");
        this.checkOut = new JButton("Check Out");
        this.controller = controller;

        // Create your temporary employee here
        EmployeeFactory eFactory = new EmployeeFactory();
        this.employee = eFactory.getEmployee("buttonEmployee","Programmer");
        addCheckInButtonActionListener();
        addCheckOutButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(this.checkIn);
        this.add(this.checkOut);
    }

    public void addCheckInButtonActionListener()
    {
        this.checkIn.addActionListener(listener ->
        {
            // Insert here your controller functionality
            controller.checkIn(this.employee);
        });
    }

    public void addCheckOutButtonActionListener()
    {
        this.checkOut.addActionListener(listener ->
        {
            // Insert here your controller functionality
            controller.checkOut(this.employee);
        });
    }
}
