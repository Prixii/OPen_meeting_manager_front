package components.navigator;


import util.FontData;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NavigatorItem extends JPanel {

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
            System.out.println("click!");
        });
        return buttonBuilder(button);
    }

    private Component groupButtonBuilder() {
        JButton button =  new JButton("组织列表");
        button.addActionListener(action -> {
            System.out.println("group!");
        });
        return buttonBuilder(button);
    }

    private Component inviteListButtonBuilder() {
        JButton button =  new JButton("邀请列表");
        button.addActionListener(action -> {
            System.out.println("inviteList!");
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
