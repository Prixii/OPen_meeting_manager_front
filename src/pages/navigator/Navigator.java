package pages.navigator;

import javax.swing.*;
import java.awt.*;

public class Navigator extends JPanel {
    public Navigator() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        leftPanel.setPreferredSize(new Dimension(200, 400));
        leftPanel.setBackground(Color.orange);
        rightPanel.setPreferredSize(new Dimension(600, 400));
        rightPanel.setBackground(Color.pink);

        add(leftPanel);
        add(rightPanel);

    }
}
