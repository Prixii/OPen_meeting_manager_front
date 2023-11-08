package components.organization;

import bloc.OrganizationBloc;
import entity.Organization;
import state.OrganizationState;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationIndex extends JPanel {
    OrganizationBloc organizationBloc;
    OrganizationState organizationState;

    List<Organization> organizationsJoined;
    List<Organization> organizationsManaged;

    void setListener() {
        setOrganizationsJoinedListener();
        setOrganizationsManagedListener();
    }

    void setOrganizationsJoinedListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "joinedList")) { return; }
            organizationsJoined = (List<Organization>) evt.getNewValue();
        });
    }

    void setOrganizationsManagedListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "managedList")) { return; }
            organizationsManaged= (List<Organization>) evt.getNewValue();
        });
    }


    Component itemListCreator(List<Organization> items) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        JPanel listPanel = new JPanel();
        listPanel.setLayout(layout);
        listPanel.setPreferredSize(new Dimension(getWidth(),  80 + 80 * items.size() / 2));
        for (Organization organization: items) {
            listPanel.add(new OrganizationItem(organization));
        }
        return listPanel;
    }

    Component managedListBuilder() {
        return itemListCreator(organizationsManaged);
    }

    Component joinedListBuilder() {
        Box column = Box.createVerticalBox();
        column.setAlignmentY(TOP_ALIGNMENT);
        JLabel label = new JLabel("———— Managing ————");
        label.setFont(FontData.BODY);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        column.add(label);
        column.add(itemListCreator(organizationsJoined));
        return column;
    }

    public OrganizationIndex() {
        organizationBloc = OrganizationBloc.getInstance();
        organizationState = OrganizationState.getInstance();
        organizationsJoined = new ArrayList<Organization>();
        organizationsManaged = new ArrayList<Organization>();
        setListener();
        organizationBloc.getOrganizations();

        setPreferredSize(new Dimension(900, 700));

        Box column = Box.createVerticalBox();
        column.add(managedListBuilder());
        column.add(joinedListBuilder());

        JScrollPane scrollPane = new JScrollPane(column, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(900, 700));
        add(scrollPane);
    }
}
