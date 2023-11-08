package components.organization;

import entity.Organization;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OrganizationItem extends JPanel {
    private GridBagLayout layout;
    private Organization organization;
    private JPanel panel;

    private void panelBuilder() {
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(410, 70));
        panel.setBackground(Color.GRAY);
        contentBuilder();
        add(panel);
    }

    private void contentBuilder() {
        panel.add(new JLabel(organization.getName()));
    }

    public OrganizationItem(Organization organization) {
        this.organization = organization;
        layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(430, 80));
        panelBuilder();
    }
}
