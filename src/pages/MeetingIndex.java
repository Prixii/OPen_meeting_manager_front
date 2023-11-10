package pages;

import api.Response.meeting.MeetingListResponse;
import bloc.MeetingBloc;
import components.ListTitle;
import components.meeting.MeetingCreator;
import components.meeting.MeetingItem;
import entity.Meeting;
import lombok.var;
import state.GlobalState;
import state.MeetingState;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MeetingIndex extends JPanel {
    List<MeetingListResponse> meetings;
    Map<Integer, Component> itemMap;
    Box listView;
    Component placeHolder;

    private final GlobalState globalState;

    private final MeetingState meetingState;

    void setListener() {
        setOnRefreshListener();
        setOnFinishListener();
        setOnCreateListener();
    }

    void setOnRefreshListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "refresh")) { return; }
            itemMap.clear();
            listView.removeAll();
            meetings = (List<MeetingListResponse>) evt.getNewValue();
            buildItem();
        });
    }

    void setOnFinishListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "finish")) { return; }
            var targetId = (Integer) evt.getNewValue();
            var target = itemMap.get(targetId);
            listView.remove(target);
            if (itemMap.size() <= 7) {
                listView.add(Box.createVerticalStrut(80));
            }
            CommonUtil.repaint(listView);
        });

    }
    void setOnCreateListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "create")) { return; }
            var newMeeting = (Meeting) evt.getNewValue();
            listView.remove(placeHolder);
            var item = new MeetingItem(newMeeting);
            listView.add(item, 0);
            itemMap.put(newMeeting.getId(), item);
            if (meetings.size() <= 7) {
                placeHolder = Box.createVerticalStrut(610 - meetings.size() * 80);
                listView.add(placeHolder);
            }
            CommonUtil.repaint(listView);
        });

    }

    JButton createMeetingButtonBuilder() {
        var button = new JButton();
        var label = new JLabel("Create");
        label.setFont(FontData.BODY);
        label.setForeground(Color.white);
        button.add(label);
        button.addActionListener(e -> showMeetingCreator());
        return button;
    }

    void showMeetingCreator() {
        var dialog = new MeetingCreator(globalState.getMainFrame(), "Create Meeting");
        dialog.setVisible(true);
    }

    Component meetingListBuilder() {
        listView = Box.createVerticalBox();
        listView.setAlignmentY(SwingConstants.TOP);
        buildItem();
        return listView;
    }

    void buildItem() {
        for (MeetingListResponse meeting:
                meetings) {
            var item = new MeetingItem(meeting.toMeeting());
            listView.add(item);
            itemMap.put(meeting.getId(),item);
        }

        if (meetings.size() <= 7) {
            placeHolder = Box.createVerticalStrut(610 - meetings.size() * 80);
            listView.add(placeHolder);
        } else {
            placeHolder = Box.createVerticalStrut(0);
            listView.add(placeHolder);
        }
        CommonUtil.repaint(listView);
        CommonUtil.repaint(this);
    }

    public MeetingIndex() {
        globalState = GlobalState.getInstance();
        meetingState = MeetingState.getInstance();
        MeetingBloc meetingBloc = MeetingBloc.getInstance();

        meetings = new ArrayList<>();
        itemMap = new HashMap<>();

        setLayout(new BorderLayout());
        setListener();

        setPreferredSize(new Dimension(900, 700));

        JScrollPane scrollPane = new JScrollPane(meetingListBuilder(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(850, 650));

        add(new ListTitle("Meetings", createMeetingButtonBuilder()), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        meetingBloc.getMeeting();
    }
}
