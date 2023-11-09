package components.invitation;

import assets.IconAssets;
import bloc.InvitationBloc;
import entity.Invitation;
import lombok.var;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InvitationItem extends JPanel{
    private Invitation invitation;
    private InvitationBloc invitationBloc;

    private void panelBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(840, 70));
        panel.setBackground(Color.GRAY);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(10,0,10,0));
        row.add(memberNameBuilder());
        row.add(Box.createHorizontalGlue());
        row.add(acceptButtonBuilder());
        row.add(Box.createHorizontalStrut(15));
        row.add(refuseButtonBuilder());
        row.add(Box.createHorizontalStrut(20));
        return row;
    }

    Component memberNameBuilder() {
        var label = new JLabel(invitation.getOrganizationName());
        label.setFont(FontData.BODY);
        label.setForeground(Color.white);
        return label;
    }

    Component acceptButtonBuilder() {
        var acceptButton = CommonUtil.IconButton(IconAssets.CHECK_ONE);
        acceptButton.addActionListener(e -> onAcceptPressed());
        return acceptButton;
    }

    void onAcceptPressed() {
        invitationBloc.onAccept(invitation.getId());
    }
    Component refuseButtonBuilder() {
        var refuseButton = CommonUtil.IconButton(IconAssets.CLOSE_ONE);
        refuseButton.addActionListener(e -> onRefusePressed());
        return refuseButton;
    }


    void onRefusePressed() {
        invitationBloc.onRefuse(invitation.getId());
    }

    public InvitationItem(Invitation invitation) {
        invitationBloc = InvitationBloc.getInstance();
        this.invitation = invitation;
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(840, 80));
        panelBuilder();
    }
}
