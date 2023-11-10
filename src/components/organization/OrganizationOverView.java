package components.organization;

import bloc.OrganizationBloc;
import components.ListTitle;
import entity.Organization;
import lombok.var;
import state.OrganizationState;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

public class OrganizationOverView extends JPanel {
    OrganizationBloc organizationBloc;
    OrganizationState organizationState;

    List<Organization> organizationsJoined;
    List<Organization> organizationsManaged;

    Map<Integer, Component> itemMap;

    JPanel joinedPanel;
    JPanel managedPanel;


    void setListener() {
        setOrganizationsJoinedListener();
        setOrganizationsManagedListener();
        setCreateListener();
        setDissolveListener();
        setOrganizationsLeaveListener();
    }

    void setOrganizationsJoinedListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "joinedList")) { return; }
            organizationsJoined = (List<Organization>) evt.getNewValue();
            joinedPanel.removeAll();
            for (Organization item:
                    organizationsJoined) {
                var newItem = new OrganizationItem(item);
                itemMap.put(item.getId(), newItem);
                joinedPanel.add(newItem);
            }
        });
    }
    void setOrganizationsLeaveListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "leave")) { return; }
            var organizationId = (Integer) evt.getNewValue();
            organizationsJoined.removeIf(organization -> Objects.equals(organization.getId(), organizationId));
            var target = itemMap.get(organizationId);
            joinedPanel.remove(target);
            CommonUtil.repaint(joinedPanel);
        });
    }

    void setOrganizationsManagedListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "managedList")) { return; }
            organizationsManaged= (List<Organization>) evt.getNewValue();
            managedPanel.removeAll();
            for (Organization item:
                    organizationsManaged) {
                var newItem = new OrganizationItem(item);
                itemMap.put(item.getId(), newItem);
                managedPanel.add(newItem);
            }
        });
    }

    void setCreateListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "create")) {return;}
            var newOrganization =(Organization) evt.getNewValue();
            organizationsManaged.add(0,newOrganization );
            var newItem = new OrganizationItem(newOrganization);
            itemMap.put(newOrganization.getId(), newItem);
            managedPanel.add(newItem, 0);

            managedPanel.repaint();
            managedPanel.revalidate();
        });
    }

    void setDissolveListener() {
        organizationState.addPropertyChangeListener(evt -> {
            System.out.println("Try target!");
            if (!Objects.equals(evt.getPropertyName(), "dissolve")) {return;}
            var target = itemMap.get((Integer) evt.getNewValue());
            if (target != null) {
                System.out.println("target!");
                managedPanel.remove(target);
            }

            managedPanel.repaint();
            managedPanel.revalidate();
        });
    }


    Component itemListCreator( JPanel listPanel, List<Organization> items) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        listPanel.setLayout(layout);
        listPanel.setPreferredSize(new Dimension(getWidth(),  80 + 80 * items.size() / 2));
        for (Organization organization: items) {
            var newItem = new OrganizationItem(organization);
            itemMap.put(organization.getId(), newItem);
            listPanel.add(newItem);
        }
        return listPanel;
    }

    Component joinedBuilder() {
        joinedPanel = new JPanel();
        Box column = Box.createVerticalBox();
        column.setAlignmentY(TOP_ALIGNMENT);

        column.add(new ListTitle("Joined",null));
        column.add(itemListCreator(joinedPanel, organizationsJoined));
        return column;
    }

    Component managedBuilder() {
        managedPanel = new JPanel();
        Box column = Box.createVerticalBox();
        column.setAlignmentY(TOP_ALIGNMENT);

        var createOrganization = new JButton();
        var label = new JLabel("Create");
        label.setFont(FontData.BODY);
        label.setForeground(Color.white);
        createOrganization.add(label);
        createOrganization.addActionListener(e -> onCreateOrganization());

        column.add(new ListTitle("Managing", createOrganization));
        column.add(itemListCreator(managedPanel, organizationsManaged));

        return column;
    }

    private void onCreateOrganization() {
        String input = JOptionPane.showInputDialog(this, "Organization Name:");
        if (input != null) {
            organizationBloc.createOrganization(input);
        }
    }

    public OrganizationOverView() {
        organizationBloc = OrganizationBloc.getInstance();
        organizationState = OrganizationState.getInstance();
        organizationsJoined = new ArrayList<>();
        organizationsManaged = new ArrayList<>();
        itemMap = new HashMap<>();

        setListener();
//        TODO offline
        organizationBloc.getOrganizations();

        setPreferredSize(new Dimension(900, 700));

        Box column = Box.createVerticalBox();
        column.add(managedBuilder());
        column.add(joinedBuilder());

        JScrollPane scrollPane = new JScrollPane(column, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(900, 700));
        add(scrollPane);
    }
}
