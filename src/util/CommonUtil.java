package util;

import lombok.var;

import javax.swing.*;
import java.awt.*;

public class CommonUtil {
    public static void repaint(Component c) {
        c.repaint();
        c.revalidate();
    }

    public static JButton IconButton(Icon icon) {
        var button = new JButton();
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(30, 30));
        button.setOpaque(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }
}
