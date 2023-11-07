package pages.navigator;

import components.navigator.NavigatorItem;
import components.navigator.PageView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Navigator extends JPanel {

    private Component navigator() {
        JPanel navigatorPanel = new JPanel();

        navigatorPanel.setPreferredSize(new Dimension(300, 700));
        navigatorPanel.setBorder( new EmptyBorder(10,10,10,10));
        navigatorPanel.setBackground(Color.lightGray);
        navigatorPanel.add(new NavigatorItem());

        return navigatorPanel;
    }

    private Component page() {
        EmptyBorder border = new EmptyBorder(10,10,10,10);
        JPanel pagePanel = new JPanel();
        pagePanel.setPreferredSize(new Dimension(900, 700));

        pagePanel.add( new PageView());

        return pagePanel;
    }

    public Navigator() {
        setLayout(new GridBagLayout());

        add(navigator());
        add(page());

    }
}
