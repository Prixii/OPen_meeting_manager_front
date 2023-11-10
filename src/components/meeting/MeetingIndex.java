package components.meeting;

import api.Response.meeting.MeetingListResponse;
import bloc.MeetingBloc;
import components.ListTitle;
import entity.Meeting;
import lombok.var;
import state.MeetingState;
import util.CommonUtil;

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

    private final MeetingState meetingState;
    private final MeetingBloc meetingBloc;

    void setListener() {
        setOnRefreshListener();
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

    Component meetingListBuilder() {
        listView = Box.createVerticalBox();
        listView.setAlignmentY(SwingConstants.TOP);
        buildItem();
        return listView;
    }

    void buildItem() {
        try {
            for (MeetingListResponse meeting:
                    meetings) {
                var item = new MeetingItem(meeting.toMeeting());
                listView.add(item);
                itemMap.put(meeting.getId(),item);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (meetings.size() <= 8) {
            placeHolder = Box.createVerticalStrut(650 - meetings.size() * 80);
            listView.add(placeHolder);
        } else {
            placeHolder = Box.createVerticalStrut(0);
            listView.add(placeHolder);
        }
        CommonUtil.repaint(listView);
//        CommonUtil.repaint(scrollPane);
        CommonUtil.repaint(this);

    }


    public MeetingIndex() {
        meetingState = MeetingState.getInstance();
        meetingBloc = MeetingBloc.getInstance();

        meetings = new ArrayList<>();
        itemMap = new HashMap<>();

        setLayout(new BorderLayout());
        setListener();

        setPreferredSize(new Dimension(900, 700));

        JScrollPane scrollPane = new JScrollPane(meetingListBuilder(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(890, 650));

        add(new ListTitle("会议列表", new JButton("发起会议")), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        meetingBloc.getMeeting();

    }
}
