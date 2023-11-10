package pages;

import components.navigator.NavigatorItem;
import components.navigator.PageView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Navigator extends JPanel {


    private Component navigator() {
        JPanel navigatorPanel = new JPanel();

        navigatorPanel.setPreferredSize(new Dimension(300, 700));
        navigatorPanel.setBackground(Color.GRAY);
        navigatorPanel.add(new NavigatorItem());

        return navigatorPanel;
    }

    private Component page() {
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
