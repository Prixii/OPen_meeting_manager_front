package components.navigator;

import bloc.NavigationBloc;
import components.invitation.InvitationIndex;
import components.meeting.MeetingIndex;
import components.organization.OrganizationIndex;
import entity.enums.PageIndex;
import state.NavigationState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PageView extends JPanel {
    NavigationBloc navigationBloc;
    NavigationState navigationState;
    CardLayout layout;


    void setIndexListener() {
        navigationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "index")) { return; }
            if (evt.getOldValue() != evt.getNewValue()){
                layout.show(this, evt.getNewValue().toString());
            }
        });
    }

    public PageView() {
        navigationBloc = NavigationBloc.getInstance();
        navigationState = NavigationState.getInstance();
        setIndexListener();

        setPreferredSize(new Dimension(900, 700));
        layout = new CardLayout();
        setLayout(layout);

        add(PageIndex.MEETING_LIST.toString(), new MeetingIndex());
        add(PageIndex.ORGANIZATION_LIST.toString(), new OrganizationIndex());
        add(PageIndex.INVITATION_LIST.toString(), new InvitationIndex());
    }
}
