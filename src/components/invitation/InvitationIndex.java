package components.invitation;

import bloc.InvitationBloc;
import bloc.OrganizationBloc;
import components.ListTitle;
import components.organization.MemberItem;
import entity.Invitation;
import entity.Member;
import lombok.var;
import state.InvitationState;
import util.CommonUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class InvitationIndex extends JPanel {
    List<Invitation> invitations;
    Map<Integer, Component> itemMap;
    Box listView;
    Component placeHolder;
    InvitationBloc invitationBloc;
    InvitationState invitationState;
    OrganizationBloc organizationBloc;

    Component invitationListBuilder() {
        listView = Box.createVerticalBox();
        listView.setAlignmentY(SwingConstants.TOP);
        for (Invitation invitation:
            invitations) {
            listView.add(new InvitationItem(invitation));
        }

        if (invitations.size() <= 8) {
            placeHolder = Box.createVerticalStrut(650 - invitations.size() * 80);
            listView.add(placeHolder);
        } else {
            placeHolder = Box.createVerticalStrut(0);
            listView.add(placeHolder);
        }

        return listView;
    }

    void setListener() {
        setInvitationsListener();
        setRemoveInvitationListener();
    }

    void setInvitationsListener() {
        invitationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "invitationList")) { return; }
            listView.removeAll();
            invitations = (List<Invitation>) evt.getNewValue();
            itemMap.clear();
            for (Invitation invitation:
                 invitations) {
                var item = new InvitationItem(invitation);
                listView.add(item);
                itemMap.put(invitation.getId(), item);
            }

            if (invitations.size() <= 8) {
                placeHolder = Box.createVerticalStrut(650 - invitations.size() * 80);
                listView.add(placeHolder);
            } else {
                placeHolder = Box.createVerticalStrut(0);
                listView.add(placeHolder);
            }

            CommonUtil.repaint(this);
            CommonUtil.repaint(listView);
        });
    }

    void setRemoveInvitationListener() {
        invitationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "remove")) { return; }
            var targetId = (Integer) evt.getNewValue();
            var targetItem = itemMap.get(targetId);
            if (targetItem != null) {
                invitations = invitationState.getInvitations();
                if (invitations.size() < 8 ) {
                    listView.add(Box.createVerticalStrut(80));
                }
                listView.remove(targetItem);
                CommonUtil.repaint(listView);
            }

            if ((Boolean) evt.getOldValue()) {
                organizationBloc.getJoinedOrganizations();
            }
        });
    }

    public InvitationIndex() {
        invitations = new ArrayList<>();
        itemMap = new HashMap<>();
        organizationBloc = OrganizationBloc.getInstance();
        invitationBloc = InvitationBloc.getInstance();
        invitationState = InvitationState.getInstance();
        setLayout(new BorderLayout());
        setListener();

        invitationBloc.getInvitations();

        setPreferredSize(new Dimension(900, 700));

        JScrollPane scrollPane = new JScrollPane(invitationListBuilder(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(890, 650));

        add(new ListTitle("我的邀请", null), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
