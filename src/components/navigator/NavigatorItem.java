package components.navigator;


import bloc.NavigationBloc;
import entity.enums.PageIndex;
import lombok.var;
import util.ColorData;
import util.FontData;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NavigatorItem extends JPanel {
    NavigationBloc navigationBloc;
    JButton meetingButton;
    JButton groupButton;
    JButton invitationButton;
    JLabel meetingLabel;
    JLabel groupLabel;
    JLabel invitationLabel;

    void activateButton(JButton button, JLabel label) {
        deactivateButton(meetingButton, meetingLabel);
        deactivateButton(groupButton, groupLabel);
        deactivateButton(invitationButton, invitationLabel);
        button.setBackground(ColorData.BG);
        label.setForeground(ColorData.PRIMARY);
    }

    void deactivateButton(JButton button, JLabel label) {
        button.setBackground(ColorData.PRIMARY);
        label.setForeground(ColorData.BG);
    }

    private Component buttonBuilder(JButton functionButton,JLabel label, String title) {
        label.setText(title);
        label.setForeground(ColorData.BG);
        label.setFont(FontData.BODY);
        label.setBorder(new EmptyBorder(0,10,0,0));
        functionButton.add(label);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0,10,0,0));
        buttonPanel.setPreferredSize(new Dimension(300, 60));
        Border roundBorder = new LineBorder(ColorData.PRIMARY, 0, true);
        functionButton.setPreferredSize(new Dimension(290, 60));
        functionButton.setBorder(new EmptyBorder(0,0,0,0));
        functionButton.setBorder(roundBorder);
        functionButton.setBackground(ColorData.PRIMARY);
        functionButton.setFocusPainted(false);
        buttonPanel.add(functionButton);
        buttonPanel.setBackground(ColorData.PRIMARY);
        return  buttonPanel;
    }

    private Component meetingButtonBuilder() {
        meetingButton =  new JButton();
        meetingButton.addActionListener(action -> {
            activateButton(meetingButton, meetingLabel);
            navigationBloc.setIndex(PageIndex.MEETING_LIST);
        });
        meetingLabel = new JLabel();
        return buttonBuilder(meetingButton, meetingLabel, "Meetings");
    }

    private Component groupButtonBuilder() {
        groupButton =  new JButton();

        groupButton.addActionListener(action -> {
            activateButton(groupButton, groupLabel);
            navigationBloc.setIndex(PageIndex.ORGANIZATION_LIST);
        });
        groupLabel = new JLabel();
        return buttonBuilder(groupButton,groupLabel,"Organizations");
    }

    private Component inviteListButtonBuilder() {
        invitationButton =  new JButton();
        invitationButton.addActionListener(action -> {
            activateButton(invitationButton, invitationLabel);
            navigationBloc.setIndex(PageIndex.INVITATION_LIST);
        });
        invitationLabel = new JLabel();
        return buttonBuilder(invitationButton,invitationLabel, "Invitations");
    }

    public NavigatorItem() {
        navigationBloc = NavigationBloc.getInstance();

        setLayout(new BorderLayout());
        setBackground(ColorData.PRIMARY);

        var column = Box.createVerticalBox();
        column.setBackground(ColorData.PRIMARY);
        column.add(meetingButtonBuilder());
        column.add(groupButtonBuilder());
        column.add(inviteListButtonBuilder());
        column.add(Box.createVerticalGlue());
        add(column, BorderLayout.NORTH);
        activateButton(meetingButton,meetingLabel);
    }


}
