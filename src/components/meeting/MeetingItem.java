package components.meeting;

import assets.IconAssets;
import bloc.MeetingBloc;
import entity.Meeting;
import lombok.var;
import state.GlobalState;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MeetingItem extends JPanel {
    private GridBagLayout layout;
    private Meeting meeting;
    private final MeetingBloc meetingBloc;
    private final GlobalState globalState;

    private void panelBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(840, 70));
        panel.setBackground(Color.GRAY);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(10,0,10,0));
        row.add(meetingTitleBuilder());
        row.add(Box.createHorizontalGlue());
        if (meeting.belongTo(globalState.getUser().getId())){
            row.add(finishButtonBuilder());
            row.add(Box.createHorizontalStrut(15));
            row.add(cancelButtonBuilder());
            row.add(Box.createHorizontalStrut(20));
        }
        return row;
    }

    Component meetingTitleBuilder() {
        var label = new JLabel(meeting.getTitle());
        label.setFont(FontData.BODY);
        label.setForeground(Color.white);
        return label;
    }

    Component finishButtonBuilder() {
        var acceptButton = CommonUtil.IconButton(IconAssets.CHECK_ONE);
        acceptButton.addActionListener(e -> onFinishPressed());
        return acceptButton;
    }

    void onFinishPressed() {
        meetingBloc.onFinish(meeting.getId());
    }
    Component cancelButtonBuilder() {
        var refuseButton = CommonUtil.IconButton(IconAssets.CLOSE_ONE);
        refuseButton.addActionListener(e -> onCancelPressed());
        return refuseButton;
    }


    void onCancelPressed() {
        meetingBloc.onCancel(meeting.getId());
    }

    public MeetingItem(Meeting meeting) {
        meetingBloc = MeetingBloc.getInstance();
        globalState = GlobalState.getInstance();
        this.meeting = meeting;
        layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(840, 80));
        panelBuilder();
    }
}
