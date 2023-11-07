package components.navigator;

import State.NavigationState;
import bloc.NavigationBloc;
import components.meeting.MeetingIndex;
import entity.enums.PageIndex;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PageView extends JPanel {
    NavigationBloc navigationBloc;
    CardLayout layout;


    void setIndexListener() {
        navigationBloc.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "index")) { return; }
            if (evt.getOldValue() != evt.getNewValue()){
                layout.show(this, evt.getNewValue().toString());
            }
        });
    }

    public PageView() {
        navigationBloc = NavigationBloc.getInstance();
        setIndexListener();

        setPreferredSize(new Dimension(900, 700));
        layout = new CardLayout();
        setLayout(layout);

        //创建用于显示的面板
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        //在每个字面板插入标签
        jPanel2.add(new JLabel("第二个card"));
        jPanel3.add(new JLabel("第三个card"));

        add(PageIndex.MEETING_LIST.toString(), new MeetingIndex());
        add(PageIndex.ORGANIZATION_LIST.toString(), jPanel2);
        add(PageIndex.INVITATION_LIST.toString(), jPanel3);
    }
}
