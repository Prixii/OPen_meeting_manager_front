package components.meeting;

import assets.IconAssets;
import bloc.MeetingBloc;
import entity.Member;
import lombok.var;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MemberItem extends JPanel {
    private GridBagLayout layout;
    private Member member;
    private final MeetingBloc meetingBloc;

    private void panelBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(0,10,0,10));
        panel.setPreferredSize(new Dimension(350, 50));
        panel.setBackground(Color.WHITE);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(0,0,0,0));
        row.add(memberNameBuilder());
        row.add(Box.createHorizontalGlue());
        row.add(chooseButtonBuilder());
        row.add(Box.createHorizontalStrut(20));
        return row;
    }

    Component memberNameBuilder() {
        var label = new JLabel(member.getName());
        label.setFont(FontData.BODY);
        label.setForeground(Color.BLACK);
        return label;
    }

    Component chooseButtonBuilder() {
        var refuseButton = CommonUtil.iconButton(IconAssets.PEOPLE_PLUS, true);
        refuseButton.setPreferredSize(new Dimension(40,40));
        refuseButton.addActionListener(e -> onChoosePressed());
        return refuseButton;
    }


    void onChoosePressed() {
        meetingBloc.onAddParticipant(member.getAccount());
    }

    public MemberItem(Member member) {
        meetingBloc = MeetingBloc.getInstance();
        this.member = member;
        layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(350, 60));
        panelBuilder();
    }
}
