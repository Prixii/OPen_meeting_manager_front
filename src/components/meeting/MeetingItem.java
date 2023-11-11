package components.meeting;

import assets.IconAssets;
import bloc.MeetingBloc;
import entity.Meeting;
import lombok.var;
import state.GlobalState;
import util.ColorData;
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
        panel.setBackground(Color.WHITE);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(5,0,5,0));
        row.add(meetingTitleBuilder());
        row.add(Box.createHorizontalStrut(40));
        row.add(buildTimeLabel());
        row.add(Box.createHorizontalGlue());
        if (meeting.belongTo(globalState.getUser().getId())){
            row.add(finishButtonBuilder());
            row.add(Box.createHorizontalStrut(15));
            row.add(cancelButtonBuilder());
            row.add(Box.createHorizontalStrut(20));
        }
        return row;
    }

    Component buildTimeLabel() {
        var label = new Label(meeting.getStartTime() + " ~ " + meeting.getEndTime());
        label.setFont(FontData.BODY);
        label.setForeground(Color.gray);
        return label;
    }

    Component meetingTitleBuilder() {
        var label = new JLabel(meeting.getTitle());
        label.setFont(FontData.BODY);
        label.setForeground(Color.BLACK);
        return label;
    }

    Component finishButtonBuilder() {
        var acceptButton = CommonUtil.iconButton(IconAssets.CHECK_ONE, true);
        acceptButton.addActionListener(e -> onFinishPressed());
        acceptButton.setBackground(ColorData.ACCEPT);
        return acceptButton;
    }

    void onFinishPressed() {
        meetingBloc.onFinish(meeting.getId());
    }
    Component cancelButtonBuilder() {
        var refuseButton = CommonUtil.iconButton(IconAssets.CLOSE_ONE, true);
        refuseButton.setBackground(ColorData.WARNING);
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
