package components.organization;

import assets.IconAssets;
import bloc.OrganizationBloc;
import components.PlaceholderTextField;
import entity.Organization;
import lombok.var;
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
    Organization organization = new Organization(-1,-1,"");

    void setListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "toDetail")) { return; }
            organization = (Organization) evt.getNewValue();
            nameLabel.setText(organization.getName());
            CommonUtil.repaint(this);
            CommonUtil.repaint(nameLabel);

        });
    }

    Component backButtonBuilder() {
        var panel = new Panel();

        var backButton = new JButton(IconAssets.ARROW_LEFT);
        backButton.setPreferredSize(new Dimension(30,30));
        backButton.addActionListener(e -> organizationBloc.toOverView());
        backButton.setOpaque(false);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBorder(new EmptyBorder(0,0,0,0));
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

        var inviteButton = new JButton();
        inviteButton.setIcon(IconAssets.PEOPLE_PLUS);
        inviteButton.setPreferredSize(new Dimension(60, 30));
        inviteButton.setOpaque(false);
        inviteButton.setOpaque(false);
        inviteButton.setContentAreaFilled(false);
        inviteButton.setBorderPainted(false);
        inviteButton.setFocusPainted(false);
        inviteButton.setBorder(new EmptyBorder(0,20,0,20));

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

    OrganizationDetail() {
        organizationState = OrganizationState.getInstance();
        organizationBloc = OrganizationBloc.getInstance();
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
