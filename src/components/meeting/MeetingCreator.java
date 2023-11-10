package components.meeting;

import lombok.var;
import state.OrganizationState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MeetingCreator extends JDialog{

    private final OrganizationState organizationState;

    Component panelBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(800, 400));
        panel.setBackground(Color.GRAY);
        panel.add(contentBuilder());
        return panel;
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setPreferredSize(new Dimension(800, 400));
        row.setBorder(new EmptyBorder(10,0,10,0));
        row.add(memberListBuilder());
        row.add(Box.createHorizontalStrut(60));
        row.add(participantListBuilder());
        row.setBackground(Color.PINK);

        return row;
    }

    Component memberListBuilder() {
        var column = Box.createVerticalBox();
        column.setPreferredSize(new Dimension(360, 380));
        column.setBackground(Color.DARK_GRAY);
        column.add(new JPanel());
        return column;
    }

    Component participantListBuilder() {
        var listView = Box.createVerticalBox();
        listView.setPreferredSize(new Dimension(360, 380));
        listView.setBackground(Color.DARK_GRAY);
        listView.add(new JPanel());
        return listView;
    }

    public MeetingCreator(JFrame frame, String title) {
        super(frame, title);

        organizationState = OrganizationState.getInstance();

        setSize(new Dimension(800,400));
        setLocation(650, 380);
        setModal(true);
        var layout = new BorderLayout();
        setLayout(layout);

        add(panelBuilder(), BorderLayout.CENTER);
    }
}
