package components.meeting;

import entity.Meeting;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MeetingItem extends JPanel {
    private GridBagLayout layout;
    private Meeting meeting;
    private  JPanel panel;

    private void panelBuilder() {
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(410, 70));
        panel.setBackground(Color.GRAY);
        contentBuilder();
        add(panel);
    }

    private void contentBuilder() {
        panel.add(new JLabel(meeting.getTitle()));
    }

    public MeetingItem(Meeting meeting) {
        this.meeting = meeting;
        layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(430, 80));
        panelBuilder();


    }


}
