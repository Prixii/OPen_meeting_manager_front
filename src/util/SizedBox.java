package util;

import javax.swing.*;
import java.awt.*;

public class SizedBox extends JPanel {
    private int width;
    private int height;

    public SizedBox(int width, int height) {
        Container container = new Container();
        container.setPreferredSize(new Dimension(width, height));
        add(container);
    }
}
