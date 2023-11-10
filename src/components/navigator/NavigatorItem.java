package components.navigator;


import bloc.NavigationBloc;
import entity.enums.PageIndex;
import lombok.var;
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

    void activateButton(JButton button) {
        button.setBackground(Color.WHITE);
    }

    void deactivateButton(JButton button) {
        button.setBackground(Color.GRAY);
    }

    private Component buttonBuilder(JButton functionButton, String title) {
        var label = new JLabel(title);
        label.setForeground(Color.WHITE);
        label.setFont(FontData.BODY);
        label.setBorder(new EmptyBorder(0,10,0,0));
        functionButton.add(label);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0,10,0,0));
        buttonPanel.setPreferredSize(new Dimension(300, 60));
        Border roundBorder = new LineBorder(Color.gray, 0, true);
        functionButton.setPreferredSize(new Dimension(290, 60));
        functionButton.setBorder(new EmptyBorder(0,0,0,0));
        functionButton.setBorder(roundBorder);
        functionButton.setBackground(Color.GRAY);
        functionButton.setFocusPainted(false);
        buttonPanel.add(functionButton);
        buttonPanel.setBackground(Color.GRAY);
        return  buttonPanel;
    }

    private Component meetingButtonBuilder() {
        meetingButton =  new JButton();
        meetingButton.addActionListener(action -> navigationBloc.setIndex(PageIndex.MEETING_LIST));
        return buttonBuilder(meetingButton, "Meetings");
    }

    private Component groupButtonBuilder() {
        groupButton =  new JButton();

        groupButton.addActionListener(action -> navigationBloc.setIndex(PageIndex.ORGANIZATION_LIST));
        return buttonBuilder(groupButton,"Organizations");
    }

    private Component inviteListButtonBuilder() {
        invitationButton =  new JButton();
        invitationButton.addActionListener(action -> navigationBloc.setIndex(PageIndex.INVITATION_LIST));
        return buttonBuilder(invitationButton, "Invitations");
    }

    public NavigatorItem() {
        navigationBloc = NavigationBloc.getInstance();

        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        var column = Box.createVerticalBox();
        column.setBackground(Color.GRAY);
        column.add(meetingButtonBuilder());
        column.add(groupButtonBuilder());
        column.add(inviteListButtonBuilder());
        column.add(Box.createVerticalGlue());
        add(column, BorderLayout.NORTH);
    }


}
