package components.invitation;

import bloc.InvitationBloc;
import components.ListTitle;
import entity.Invitation;
import state.InvitationState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvitationIndex extends JPanel {
    InvitationBloc invitationBloc;
    InvitationState invitationState;

    List<Invitation> invitations;

    void setListener() {
        setInvitationsListener();
    }


    void setInvitationsListener() {
        invitationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "invitationList")) { return; }
            invitations = (List<Invitation>) evt.getNewValue();
        });
    }


    Component itemListCreator() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        JPanel listPanel = new JPanel();
        listPanel.setLayout(layout);
        listPanel.setPreferredSize(new Dimension(getWidth(),  80 + 80 * invitations.size() / 2));
        for (Invitation invitation: invitations) {
            listPanel.add(new InvitationItem(invitation));
        }
        return listPanel;
    }


    public InvitationIndex() {
        invitations = new ArrayList<>();
        invitationBloc = InvitationBloc.getInstance();
        invitationState = InvitationState.getInstance();

        setListener();

        invitationBloc.getInvitations();

        setPreferredSize(new Dimension(900, 700));

        Box column = Box.createVerticalBox();
        column.add(new ListTitle("我的邀请", null));
        column.add(itemListCreator());

        JScrollPane scrollPane = new JScrollPane(column, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(900, 700));
        add(scrollPane);
    }
}
