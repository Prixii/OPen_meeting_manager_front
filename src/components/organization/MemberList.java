package components.organization;


import entity.Member;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberList extends JPanel {
    List<Member> memberList;
    Map<Integer, Component> itemMap;
    Component placeHolder;
    Box listView;

    Component memberListBuilder() {
        int size = 8;
        listView = Box.createVerticalBox();
        listView.setAlignmentY(SwingConstants.TOP);
        for (int i = 0; i < size; i++) {
            listView.add(new MemberItem(new Member(123,"Name")));
        }
        if (size <= 5) {
            placeHolder = Box.createVerticalStrut(410 - size * 80);
            listView.add(placeHolder);
        } else {
            placeHolder = Box.createVerticalStrut(0);
            listView.add(placeHolder);
        }

        return listView;
    }

    public MemberList() {
        memberList = new ArrayList<>();
        itemMap = new HashMap<>();
        setPreferredSize(new Dimension(870, 420));
        JScrollPane scrollPane = new JScrollPane(memberListBuilder(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(860, 410));
        memberListBuilder();
        add(scrollPane);

    }
}
