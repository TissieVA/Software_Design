package view.panels;

import javax.swing.*;
import java.awt.*;

public class NamePanel extends JPanel {

    private JList<String> entryJList;
    private DefaultListModel<String> entryListModel;

    public NamePanel()
    {
        entryListModel = new DefaultListModel<>();
        entryJList = new JList<>(entryListModel);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Registrations with names"),BorderFactory.createEmptyBorder(5,5,5,5)));
        this.setBackground(new Color(59,178,226));

        this.add(entryJList);
    }

    public void addEntry(String entry)
    {
        this.entryListModel.addElement(entry);
    }
}
