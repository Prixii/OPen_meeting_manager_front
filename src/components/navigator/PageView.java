package components.navigator;

import javax.swing.*;
import java.awt.*;

public class PageView extends JPanel {
    public PageView() {
        setPreferredSize(new Dimension(900, 700));
        CardLayout layout = new CardLayout();
        setLayout(layout);

        //创建用于显示的面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        //在每个字面板插入标签
        jPanel1.add(new JLabel("第一个card"));
        jPanel2.add(new JLabel("第二个card"));
        jPanel3.add(new JLabel("第三个card"));

        add(jPanel1);
        add(jPanel2);
        add(jPanel3);
    }
}
