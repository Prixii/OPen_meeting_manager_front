package components.invitation;

import assets.IconAssets;
import bloc.InvitationBloc;
import entity.Invitation;
import lombok.var;
import util.ColorData;
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
        panel.setBackground(Color.WHITE);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(5,0,5,0));
        row.add(invitationNameBuilder());
        row.add(Box.createHorizontalGlue());
        row.add(acceptButtonBuilder());
        row.add(Box.createHorizontalStrut(15));
        row.add(refuseButtonBuilder());
        row.add(Box.createHorizontalStrut(20));
        return row;
    }

    Component invitationNameBuilder() {
        var label = new JLabel(invitation.getOrganizationName());
        label.setFont(FontData.BODY);
        label.setForeground(Color.BLACK);
        return label;
    }

    Component acceptButtonBuilder() {
        var acceptButton = CommonUtil.iconButton(IconAssets.CHECK_ONE, true);
        acceptButton.addActionListener(e -> onAcceptPressed());
        acceptButton.setBackground(ColorData.ACCEPT);

        return acceptButton;
    }

    void onAcceptPressed() {
        invitationBloc.onAccept(invitation.getId());
    }
    Component refuseButtonBuilder() {
        var refuseButton = CommonUtil.iconButton(IconAssets.CLOSE_ONE, true);
        refuseButton.addActionListener(e -> onRefusePressed());
        refuseButton.setBackground(ColorData.WARNING);
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
