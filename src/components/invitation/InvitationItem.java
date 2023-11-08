package components.invitation;

import entity.Invitation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InvitationItem extends JPanel{
    private GridBagLayout layout;
    private Invitation invitation;
    private JPanel panel;

    private void panelBuilder() {
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(820, 70));
        panel.setBackground(Color.GRAY);
        contentBuilder();
        add(panel);
    }

    private void contentBuilder() {
        panel.add(new JLabel(invitation.getOrganizationName()));
    }

    public InvitationItem(Invitation invitation) {
        this.invitation = invitation;
        layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(840, 80));
        panelBuilder();
    }
}
