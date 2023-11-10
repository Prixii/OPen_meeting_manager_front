package util;

import lombok.var;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CommonUtil {
    public static void repaint(Component c) {
        c.repaint();
        c.revalidate();
    }

    public static JButton iconButton(Icon icon, boolean filled) {
        var button = new JButton();
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(40, 40));

        button.setOpaque(filled);
        button.setContentAreaFilled(filled);
        button.setBackground(ColorData.PRIMARY);
        button.setBorderPainted(filled);
        return button;
    }

    public static Component divider(Integer width, Integer height) {
        var divider = new JPanel();
        divider.setPreferredSize(new Dimension(width, height));
        divider.setBackground(ColorData.DIVIDER);
        return divider;
    }
}
