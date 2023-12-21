package pages;

import components.organization.OrganizationDetail;
import components.organization.OrganizationOverView;
import state.OrganizationState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class OrganizationIndex extends JPanel {
    private final OrganizationState organizationState;
    private CardLayout layout;

    void setListener() {
      setToDetailListener();
      setToOverViewListener();
    }

    void setToDetailListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "toDetail")) { return; }
            layout.show(this,"Detail");
        });
    }

    void setToOverViewListener() {
        organizationState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "toOverView")) { return; }
            layout.show(this,"OverView");
        });
    }


    public OrganizationIndex() {
        organizationState = OrganizationState.getInstance();

        setListener();

        layout = new CardLayout();
        setLayout(layout);

        setPreferredSize(new Dimension(900, 700));

        add("OverView",new OrganizationOverView());
        add("Detail", new OrganizationDetail());
    }
}
