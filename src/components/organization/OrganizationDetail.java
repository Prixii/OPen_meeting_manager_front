package components.organization;

import assets.IconAssets;
import bloc.InvitationBloc;
import bloc.OrganizationBloc;
import components.PlaceholderTextField;
import entity.Organization;
import lombok.var;
import state.InvitationState;
import state.OrganizationState;
import util.CommonUtil;
import util.FontData;
import util.SizedBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class OrganizationDetail extends JPanel {
    PlaceholderTextField phoneBox;
    FlowLayout layout;
    JLabel nameLabel;
    private final OrganizationBloc organizationBloc;
    private final OrganizationState organizationState;
    private final InvitationBloc invitationBloc;
    private final InvitationState invitationState;
    Organization organization = new Organization(-1,-1,"");

    void setListener() {
        toDetailListener();
        inviteResultListener();
    }

    void toDetailListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "toDetail")) { return; }
            organization = (Organization) evt.getNewValue();
            nameLabel.setText(organization.getName());
            CommonUtil.repaint(this);
            CommonUtil.repaint(nameLabel);
        });
    }

    void inviteResultListener() {
        invitationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "invite")) { return; }
            if ((Integer) evt.getOldValue() == 200) {
                onInviteSucceed();
            } else {
                onInviteFailed((String) evt.getNewValue());
            }
        });
    }

    void onInviteSucceed() {
        JOptionPane.showMessageDialog(null, "Invite succeed", "GREAT!🥰", JOptionPane.INFORMATION_MESSAGE);
    }

    void onInviteFailed(String msg) {
        JOptionPane.showMessageDialog(null, "Invite failed " + msg, "INFO", JOptionPane.INFORMATION_MESSAGE);

    }

    Component backButtonBuilder() {
        var panel = new Panel();

        var backButton = CommonUtil.IconButton(IconAssets.ARROW_LEFT);
        backButton.addActionListener(e -> organizationBloc.toOverView());
        var flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        panel.add(backButton);
        return panel;
    }

    Component nameBuilder() {
        nameLabel = new JLabel(organization.getName());
        nameLabel.setFont(FontData.LARGE_TITLE);
        return nameLabel;

    }

    Component invitationBuilder() {
        var invitationPanel = new JPanel();
        invitationPanel.setLayout(new BorderLayout());
        invitationPanel.setPreferredSize(new Dimension(900, 40));

        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(0,0,0,30));

        var inviteButton = CommonUtil.IconButton(IconAssets.PEOPLE_PLUS);
        inviteButton.setPreferredSize(new Dimension(60, 30));

        inviteButton.addActionListener(e -> onInvitePressed());

        var addMemberLabel = new Label("Add Member:");
        addMemberLabel.setFont(FontData.BODY);

        phoneBox = new PlaceholderTextField("Phone");
        phoneBox.setFont(FontData.BODY);

        row.add(addMemberLabel, BorderLayout.WEST);
        row.add(phoneBox, BorderLayout.CENTER);
        row.add(inviteButton, BorderLayout.EAST);

        invitationPanel.add(row);
        return invitationPanel;
    }

    void onInvitePressed() {
        if (phoneBox.getText().isEmpty()) { return; }
        invitationBloc.onInvite(organization.getId(), phoneBox.getText());
    }

    OrganizationDetail() {
        organizationState = OrganizationState.getInstance();
        organizationBloc = OrganizationBloc.getInstance();
        invitationBloc = InvitationBloc.getInstance();
        invitationState = InvitationState.getInstance();

        setListener();

        setPreferredSize(new Dimension(900, 700));
        setBorder(new EmptyBorder(10,10,10,10));
        var column = Box.createVerticalBox();
        column.setPreferredSize(new Dimension(900, 40));
        column.add(backButtonBuilder());
        layout = new FlowLayout();
        setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        add(column);
        add(nameBuilder());
        add(new SizedBox(900,20));
        add(invitationBuilder());
        add(new SizedBox(900,20));
        add(new MemberList());
    }
}
