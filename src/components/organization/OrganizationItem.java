package components.organization;

import assets.IconAssets;
import bloc.OrganizationBloc;
import entity.Organization;
import lombok.var;
import state.GlobalState;
import util.ColorData;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class OrganizationItem extends JPanel {
    private final Organization organization;
    private final OrganizationBloc organizationBloc;
    private final GlobalState globalState;


    private void panelBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(410, 70));
        panel.setBackground(Color.WHITE);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(5,0,5,0));
        row.add(nameBuilder());
        row.add(Box.createHorizontalGlue());
        row.add(buttonGroupBuilder());
        return row;
    }

    Component nameBuilder() {
        var name = new JLabel(organization.getName());
        name.setFont(FontData.BODY);
        return name;
    }

    Component buttonGroupBuilder() {
        var buttonGroup = Box.createHorizontalBox();
        if (Objects.equals(organization.getCreator(), globalState.getUser().getId())){
            var deleteButton = CommonUtil.iconButton(IconAssets.DELETE, true);
            deleteButton.setBackground(ColorData.WARNING);
            deleteButton.addActionListener(e -> onDissolvePressed());

            var detailButton = CommonUtil.iconButton(IconAssets.EVERY_USER, true);
            detailButton.addActionListener(e -> onDetailPressed());
            detailButton.setBackground(ColorData.PRIMARY);

            buttonGroup.add(detailButton);
            buttonGroup.add(Box.createHorizontalStrut(10));
            buttonGroup.add(deleteButton);
        } else {
            var logoutButton = CommonUtil.iconButton(IconAssets.LOGOUT, true);
            logoutButton.addActionListener(e -> onExitPressed());
            logoutButton.setBackground(ColorData.WARNING);
            buttonGroup.add(logoutButton);
        }

        return buttonGroup;
    }

    private void onDetailPressed() {
        organizationBloc.toOrganizationDetail(organization.getId());
    }

    private void onDissolvePressed() {
        int option = JOptionPane.showOptionDialog(
                null,
                "Dissolve this organization?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Confirm", "Cancel"},
                "Cancel");

        if (option == JOptionPane.YES_OPTION) {
            organizationBloc.dissolveOrganization(organization.getId());
        }
    }

    private void onExitPressed() {
        int option = JOptionPane.showOptionDialog(
                null,
                "Leave this Organization?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Confirm", "Cancel"},
                "Cancel");

        if (option == JOptionPane.YES_OPTION) {
            organizationBloc.leave(organization.getId());
        }
    }

    public OrganizationItem(Organization organization) {
        globalState = GlobalState.getInstance();
        organizationBloc = OrganizationBloc.getInstance();
        this.organization = organization;
        setPreferredSize(new Dimension(430, 80));
        panelBuilder();
    }
}
