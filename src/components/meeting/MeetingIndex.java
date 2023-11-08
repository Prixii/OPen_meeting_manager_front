package components.meeting;

import entity.Meeting;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingIndex extends JPanel {
    List<Meeting> meetings;
    List<Meeting> availableMeetings;
    List<Meeting> unavailableMeetings;

    Component itemListCreator(List<Meeting> items) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        JPanel listPanel = new JPanel();
        listPanel.setLayout(layout);
        listPanel.setPreferredSize(new Dimension(getWidth(),  80 + 80 * items.size() / 2));
        for (Meeting meeting: items) {
            listPanel.add(new MeetingItem(meeting));
        }
        return listPanel;
    }

    Component availableListBuilder() {
        return itemListCreator(availableMeetings);
    }

    Component unavailableListBuilder() {
        Box column = Box.createVerticalBox();
        column.setAlignmentY(TOP_ALIGNMENT);
        JLabel label = new JLabel("———— History ————");
        label.setFont(FontData.BODY);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        column.add(label);
        column.add(itemListCreator(unavailableMeetings));
        return column;
    }

    void testDataGenerator() {
        meetings.add(new Meeting(1,"1",2,0,0,"a","d"));
        meetings.add(new Meeting(1,"1",2,0,0,"a","d"));
        meetings.add(new Meeting(1,"1",2,0,0,"a","d"));
        meetings.add(new Meeting(1,"1",2,0,0,"a","d"));
        meetings.add(new Meeting(1,"1",2,0,0,"a","d"));

        meetings.add(new Meeting(1,"2",2,0,1,"a","d"));
        meetings.add(new Meeting(1,"2",2,0,1,"a","d"));
        refreshMeetings();
    }

    Component addComponent() {
        JButton jb = new JButton("添加");
        jb.addActionListener(e -> {
//            listPanel.add(new MeetingItem(new Meeting(1,"1",2,0,1,"a","d")));
//            updateUI();
        });
        return jb;
    }

    void refreshMeetings() {
        availableMeetings.clear();
        unavailableMeetings.clear();
        for (Meeting meeting:
             meetings) {
            if (meeting.available()) {
                availableMeetings.add(meeting);
            } else {
                unavailableMeetings.add(meeting);
            }
        }
    }

    public MeetingIndex() {
        meetings = new ArrayList<>();
        availableMeetings = new ArrayList<>();
        unavailableMeetings = new ArrayList<>();

        testDataGenerator();

        Box column = Box.createVerticalBox();
        column.add(availableListBuilder());
        column.add(unavailableListBuilder());

        JScrollPane scrollPane = new JScrollPane(column, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(900, 700));
        add(scrollPane);
    }
}
