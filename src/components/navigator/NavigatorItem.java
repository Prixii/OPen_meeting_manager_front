package components.navigator;


import State.NavigationState;
import bloc.NavigationBloc;
import entity.enums.PageIndex;
import util.FontData;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NavigatorItem extends JPanel {
    NavigationBloc navigationBloc;

    private Component buttonBuilder(JButton functionButton) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        EmptyBorder border = new EmptyBorder(5,0,5,0);
        buttonPanel.setBorder(border);
        Border roundBorder = new LineBorder(Color.gray, 0, true);
        functionButton.setPreferredSize(new Dimension(280, 60));
        functionButton.setBorder(roundBorder);
        functionButton.setBackground(Color.gray);
        functionButton.setFont(FontData.body);
        functionButton.setFocusPainted(false);
        buttonPanel.add(functionButton);
        return  buttonPanel;
    }

    private Component meetingButtonBuilder() {
        JButton button =  new JButton("会议列表");
        button.addActionListener(action -> {
            navigationBloc.setIndex(PageIndex.MEETING_LIST);
        });
        return buttonBuilder(button);
    }

    private Component groupButtonBuilder() {
        JButton button =  new JButton("组织列表");
        button.addActionListener(action -> {
            navigationBloc.setIndex(PageIndex.ORGANIZATION_LIST);
        });
        return buttonBuilder(button);
    }

    private Component inviteListButtonBuilder() {
        JButton button =  new JButton("邀请列表");
        button.addActionListener(action -> {
            navigationBloc.setIndex(PageIndex.INVITATION_LIST);
        });
        return buttonBuilder(button);
    }

    private Component logOutButtonBuilder() {
        JButton button =  new JButton("退出");
        button.addActionListener(action -> {
            System.out.println("exit!");
        });
        return buttonBuilder(button);
    }

    public NavigatorItem() {
        navigationBloc = NavigationBloc.getInstance();

        setLayout(new GridLayout(10,1));
        setPreferredSize(new Dimension(280, 670));
        setBackground(Color.lightGray);

        add(meetingButtonBuilder());
        add(groupButtonBuilder());
        add(inviteListButtonBuilder());

        add(new Container());
        add(new Container());
        add(new Container());
        add(new Container());
        add(new Container());
        add(new Container());
        add(logOutButtonBuilder());
    }


}
