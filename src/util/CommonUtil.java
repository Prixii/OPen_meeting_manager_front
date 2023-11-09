package util;

import java.awt.*;

public class CommonUtil {
    public static void repaint(Component c) {
        c.repaint();
        c.revalidate();
    }
}
