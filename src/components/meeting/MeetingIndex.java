package components.meeting;

import components.ListTitle;
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
        Box column = Box.createVerticalBox();
        column.setAlignmentY(TOP_ALIGNMENT);
        column.add(new ListTitle("会议列表", new JButton("发起会议")));
        column.add(itemListCreator(availableMeetings));
        return column;
    }


    void testDataGenerator() {
        meetings.add(new Meeting(1, "1", 2, 0, 0, "a", "d"));
        meetings.add(new Meeting(1, "1", 2, 0, 0, "a", "d"));
        meetings.add(new Meeting(1, "1", 2, 0, 0, "a", "d"));
        meetings.add(new Meeting(1, "1", 2, 0, 0, "a", "d"));
        meetings.add(new Meeting(1, "1", 2, 0, 0, "a", "d"));

        meetings.add(new Meeting(1, "2", 2, 0, 1, "a", "d"));
        meetings.add(new Meeting(1, "2", 2, 0, 1, "a", "d"));
        refreshMeetings();
    }

    void refreshMeetings() {
        availableMeetings.clear();
        for (Meeting meeting:
             meetings) {
            if (meeting.available()) {
                availableMeetings.add(meeting);
            }
        }
    }

    public MeetingIndex() {
        meetings = new ArrayList<>();
        availableMeetings = new ArrayList<>();

        testDataGenerator();

        Box column = Box.createVerticalBox();
        column.add(availableListBuilder());

        JScrollPane scrollPane = new JScrollPane(column, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(900, 700));
        add(scrollPane);
    }
}
