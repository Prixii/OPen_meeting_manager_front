package components.organization;

import assets.IconAssets;
import bloc.OrganizationBloc;
import lombok.var;
import util.FontData;

import javax.swing.*;
import java.awt.*;

public class OrganizationDetail extends JPanel {

    FlowLayout layout;
    private final OrganizationBloc organizationBloc;

    Component backButtonBuilder() {
        var panel = new Panel();

        var backButton = new JButton(IconAssets.ARROW_LEFT);
        backButton.setPreferredSize(new Dimension(30,30));
        backButton.addActionListener(e -> organizationBloc.toOverView() );
        backButton.setOpaque(false);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        var flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        panel.add(backButton);
        return panel;
    }

    Component nameBuilder() {
        var nameLabel = new JLabel("Name!");
        nameLabel.setFont(FontData.LARGE_TITLE);
        return nameLabel;

    }

    Component invitationCreator() {
        return new JPanel();
    }

    Component memberListBuilder() {
        return new JPanel();

    }


    OrganizationDetail() {
        organizationBloc = OrganizationBloc.getInstance();
        setPreferredSize(new Dimension(900, 700));
        var column = Box.createVerticalBox();
        column.setPreferredSize(new Dimension(900, 40));
        column.add(backButtonBuilder());
        layout = new FlowLayout();
        setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        add(column);
        add(nameBuilder());
    }
}
