package components.navigator;

import State.NavigationState;
import entity.enums.PageIndex;

import javax.swing.*;
import java.awt.*;

public class PageView extends JPanel {
    private final NavigationState navigationState;
    CardLayout layout;


    void setIndexListener() {
        navigationState.addPropertyChangeListener(evt -> {
            if (evt.getOldValue() != evt.getNewValue() ){
                System.out.println("switch!" + evt.getNewValue());
                layout.show(this, evt.getNewValue().toString());
            }
        });

    }

    public PageView() {
        navigationState = NavigationState.getInstance();
        setIndexListener();

        setPreferredSize(new Dimension(900, 700));
        layout = new CardLayout();
        setLayout(layout);

        //创建用于显示的面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        //在每个字面板插入标签
        jPanel1.add(new JLabel("第一个card"));
        jPanel2.add(new JLabel("第二个card"));
        jPanel3.add(new JLabel("第三个card"));

        add(PageIndex.MEETING_LIST.toString(), jPanel1);
        add(PageIndex.ORGANIZATION_LIST.toString(), jPanel2);
        add(PageIndex.INVITATION_LIST.toString(), jPanel3);
    }
}
