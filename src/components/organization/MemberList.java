package components.organization;

import entity.Member;
import lombok.var;
import state.OrganizationState;
import util.CommonUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MemberList extends JPanel {
    List<Member> memberList;
    Map<Integer, Component> itemMap;
    Component placeHolder;
    Box listView;
    OrganizationState organizationState;

    void setListener() {
    }

    void setRemoveMemberListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "remove")) { return; }
            var targetId = (Integer) evt.getNewValue();
            var targetItem = itemMap.get(targetId);
            if (targetItem != null) {
                listView.remove(targetItem);
                CommonUtil.repaint(listView);
            }
        });
    }

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
        organizationState = OrganizationState.getInstance();
        itemMap = new HashMap<>();
        setListener();
        setPreferredSize(new Dimension(870, 420));
        JScrollPane scrollPane = new JScrollPane(memberListBuilder(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(860, 410));
        memberListBuilder();
        add(scrollPane);

    }
}
