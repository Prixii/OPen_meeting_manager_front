package components.organization;

import assets.IconAssets;
import bloc.OrganizationBloc;
import entity.Member;
import lombok.var;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MemberItem extends JPanel {
    private Member member;

    private GridBagLayout layout;
    private JPanel panel;
    private final OrganizationBloc organizationBloc;


    private void panelBuilder() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(820, 70));
        panel.setBackground(Color.GRAY);
        panel.add(contentBuilder());
        add(panel);
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(10,0,10,0));
        row.add(memberNameBuilder());
        row.add(Box.createHorizontalGlue());
        row.add(kickButtonBuilder());
        row.add(Box.createHorizontalStrut(20));
        return row;
    }

    Component memberNameBuilder() {
        var label = new JLabel(member.getName());
        label.setFont(FontData.BODY);
        label.setForeground(Color.white);
        return label;
    }

    Component kickButtonBuilder() {
        var kickButton = CommonUtil.IconButton(IconAssets.DELETE);
        kickButton.addActionListener(e -> onKickPressed());
        return kickButton;
    }

    void onKickPressed() {
        int option = JOptionPane.showOptionDialog(
                null,
                "确认要踢出该成员吗",
                "警告",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"确认", "取消"},
                "确认");
        if (option == JOptionPane.YES_OPTION ) {
            organizationBloc.kick(member.getOrganization(), member.getAccount());
        }
    }

    public MemberItem(Member member) {
        this.member = member;

        organizationBloc = OrganizationBloc.getInstance();
        layout = new GridBagLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(840, 80));
        panelBuilder();
    }
}
