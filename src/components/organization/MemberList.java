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
    JScrollPane scrollPane;

    void setListener() {
        setRemoveMemberListener();
        setToDetailListener();
    }

    void setRemoveMemberListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "remove")) { return; }
            var targetId = (Integer) evt.getNewValue();
            var targetItem = itemMap.get(targetId);
            if (targetItem != null) {
                listView.remove(targetItem);
                if (itemMap.size() < 5 ) {
                    listView.add(Box.createVerticalStrut(80));
                }
                CommonUtil.repaint(listView);
                CommonUtil.repaint(this);
            }
        });
    }

    void setToDetailListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "toDetail")) { return; }
            memberList = (List<Member>) evt.getOldValue();
            listView.removeAll();
            itemMap.clear();
            buildItem();
        });
    }

    void memberListBuilder() {
        listView = Box.createVerticalBox();
        listView.setAlignmentY(SwingConstants.TOP);
        buildItem();
    }

    void buildItem() {
        for (Member member:
                memberList) {
            var item = new MemberItem(member);
            listView.add(item);
            itemMap.put(member.getAccount(),item);
        }

        if (memberList.size() <= 5) {
            placeHolder = Box.createVerticalStrut(410 - memberList.size() * 80);
            listView.add(placeHolder);
        } else {
            placeHolder = Box.createVerticalStrut(0);
            listView.add(placeHolder);
        }
        CommonUtil.repaint(listView);
        if (scrollPane != null) {
            CommonUtil.repaint(scrollPane);
        }
        CommonUtil.repaint(this);

    }

    public MemberList() {
        memberList = new ArrayList<>();
        organizationState = OrganizationState.getInstance();
        setListener();
        itemMap = new HashMap<>();
        setPreferredSize(new Dimension(870, 420));
        memberListBuilder();
        scrollPane = new JScrollPane(listView, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(860, 410));
        add(scrollPane);

    }
}
