package components.organization;

import assets.IconAssets;
import bloc.OrganizationBloc;
import entity.Organization;
import lombok.var;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class OrganizationItem extends JPanel {
    private Organization organization;
    private JPanel panel;
    private Map<Integer, Component> itemMap;
    private final OrganizationBloc organizationBloc;


    private void panelBuilder() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(410, 70));
        panel.setBackground(Color.GRAY);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(10,0,10,0));
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
        var deleteButton = new JButton();
        deleteButton.setIcon(IconAssets.DELETE);
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.setOpaque(false);
        deleteButton.setOpaque(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(e -> onDeleteOrganization());

        var memberButton = new JButton();
        memberButton.setPreferredSize(new Dimension(30, 30));
        memberButton.setIcon(IconAssets.EVERY_USER);
        memberButton.setOpaque(false);
        memberButton.setContentAreaFilled(false);
        memberButton.setBorderPainted(false);

        buttonGroup.add(memberButton);
        buttonGroup.add(Box.createHorizontalStrut(10));
        buttonGroup.add(deleteButton);
        return buttonGroup;
    }

    private void onDeleteOrganization() {
        int option = JOptionPane.showOptionDialog(
                null,
                "这是一个警告对话框",
                "警告",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"确认", "取消"},
                "确认");

        if (option == JOptionPane.YES_OPTION) {
            organizationBloc.dissolveOrganization(organization.getId());
        }
    }

    public OrganizationItem(Organization organization) {
        organizationBloc = OrganizationBloc.getInstance();
        this.organization = organization;
        setPreferredSize(new Dimension(430, 80));
        panelBuilder();
    }
}
