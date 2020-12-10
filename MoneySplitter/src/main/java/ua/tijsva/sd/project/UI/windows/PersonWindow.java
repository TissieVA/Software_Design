package ua.tijsva.sd.project.UI.windows;

import ua.tijsva.sd.project.database.Database;
import ua.tijsva.sd.project.person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonWindow extends JFrame implements ActionListener
{
    private JTextField textField;

    public PersonWindow()
    {
        super("MoneySplitter");
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

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        c.gridx = 1;
        c.gridy = 0;
        JLabel label = new JLabel("Person name");
        this.add(label,c);

        this.textField = new JTextField(25);
        textField.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        this.add(textField,c);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        this.add(cancelButton,c);

        JButton confirmButton = new JButton("Create Person");
        confirmButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        this.add(confirmButton,c);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Create Person"))
        {
            Person p = new Person(textField.getText());
            Database.getPersonDB().add(p.getId(),p);
            this.setVisible(false);
            this.dispose();
        }
        else if(e.getActionCommand().equals("Cancel"))
        {
            this.setVisible(false);
            this.dispose();
        }
    }
}
