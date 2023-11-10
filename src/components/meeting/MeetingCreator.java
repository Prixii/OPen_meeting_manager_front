package components.meeting;

import bloc.MeetingBloc;
import entity.Member;
import entity.Organization;
import lombok.var;
import state.MeetingState;
import state.OrganizationState;
import util.CommonUtil;
import util.FontData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MeetingCreator extends JDialog{

    private final OrganizationState organizationState;
    private final MeetingBloc meetingBloc;
    private final MeetingState meetingState;

    Box memberListView;
    Box participantListView;
    Component memberPlaceHolder;
    Component participantPlaceHolder;
    JPanel comboBoxPanel;

    List<Organization> organizations;
    List<Member> members;
    List<Integer> participants;
    Map<Integer, Component> itemMap;

    void testDataGenerator() {
        organizations.add(new Organization(123,123,"testOrganization"));
        organizations.add(new Organization(123,123,"testOrganization"));
        organizations.add(new Organization(123,123,"testOrganization"));

        members.add(new Member(123,123,"testMember"));
        members.add(new Member(123,123,"testMember"));
        members.add(new Member(123,123,"testMember"));

    }

    void setListener() {
        setAddParticipantListener();
        setRemoveParticipantListener();
        setChooseOrganizationListener();
    }

    void setAddParticipantListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "add")) { return; }
            for (Member member:
                 members) {
                if (Objects.equals(member.getAccount(), evt.getNewValue())) {
                    participants.add(0, member.getAccount());
                    participantListView.remove(participantPlaceHolder);
                    var item = new ParticipantItem(member);
                    itemMap.put(member.getAccount(), item);
                    participantListView.add(item);
                    if (participants.size() <= 5) {
                        participantPlaceHolder = Box.createVerticalStrut(360 - 60 * participants.size());
                        participantListView.add(participantPlaceHolder);
                    }
                    CommonUtil.repaint(participantListView);
                    return;
                }
            }
        });
    }

    void setRemoveParticipantListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "remove")) { return; }
            var accountId = (Integer) evt.getNewValue();
            var target = itemMap.get(accountId);
            participantListView.remove(target);
            participants.remove(accountId);
            participantListView.remove(participantPlaceHolder);
            if (participants.size() <= 5) {
                participantPlaceHolder = Box.createVerticalStrut(360 - 60 * participants.size());
                participantListView.add(participantPlaceHolder);
            }
            CommonUtil.repaint(participantListView);
        });
    }

    void setChooseOrganizationListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "chooseOrganization")) { return; }
            members = (List<Member>) evt.getNewValue();
            memberListBuilder();
        });
    }

    Component panelBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setPreferredSize(new Dimension(800, 400));
        panel.add(contentBuilder());
        return panel;
    }

    Component contentBuilder() {
        var row = Box.createHorizontalBox();
        row.setPreferredSize(new Dimension(800, 400));
        row.setBorder(new EmptyBorder(10,0,10,0));
        row.add(memberSelector());
        row.add(Box.createHorizontalStrut(60));
        row.add(participantListBuilder());
        return row;
    }

    Component memberSelector() {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(360, 380));
        panel.setLayout(new BorderLayout());
        comboBoxBuilder();
        panel.add(comboBoxPanel, BorderLayout.NORTH);
        memberListView = Box.createVerticalBox();
        memberListView.add(new JPanel());

        var scrollPane = new JScrollPane(memberListView, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(360, 330));
        panel.add(scrollPane, BorderLayout.CENTER);

        memberListBuilder();

        return panel;
    }

    void comboBoxBuilder() {
        comboBoxPanel = new JPanel();
        comboBoxPanel.setPreferredSize(new Dimension(360, 30));
        comboBoxPanel.setLayout(new BorderLayout());
        var label = new JLabel("Organization: ");
        label.setBorder(new EmptyBorder(0,0,0,10));
        label.setFont(FontData.BODY);
        var comboBox = new JComboBox<>(new DefaultComboBoxModel<>());
        for (Organization organization:
                organizationState.getOrganizationsManaged()) {
            comboBox.addItem(organization.getName());
        }
        comboBox.addItem("sss");
        comboBox.addItem("123456");
        comboBox.addActionListener(e -> meetingBloc.chooseOrganization((String) comboBox.getSelectedItem()));

        comboBoxPanel.add(label, BorderLayout.WEST);
        comboBoxPanel.add(comboBox, BorderLayout.CENTER);
    }

    void memberListBuilder() {
        memberListView.removeAll();
        for (Member member:
             members) {
            memberListView.add(new MemberItem(member));
        }
        if (members.size() <= 4) {
            memberPlaceHolder = Box.createVerticalStrut(290 - 60 * members.size());
            memberListView.add(memberPlaceHolder);
        }
        CommonUtil.repaint(memberListView);
    }

    Component participantListBuilder() {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(360, 380));
        panel.setLayout(new BorderLayout());

        participantListView = Box.createVerticalBox();
        participantListView.add(new JPanel());
        participantPlaceHolder = Box.createVerticalStrut(360);
        participantListView.add(participantPlaceHolder);

        var scrollPane = new JScrollPane(participantListView, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(360, 330));
        panel.add(scrollPane, BorderLayout.CENTER);

        return scrollPane;
    }

    public MeetingCreator(JFrame frame, String title) {
        super(frame, title);

        organizations = new ArrayList<>();
        members = new ArrayList<>();
        participants = new ArrayList<>();
        itemMap = new HashMap<>();

        testDataGenerator();

        meetingBloc = MeetingBloc.getInstance();
        meetingState =MeetingState.getInstance();
        organizationState = OrganizationState.getInstance();

        setSize(new Dimension(800,400));
        setLocation(650, 380);
        setResizable(false);
        setModal(true);
        var layout = new BorderLayout();
        setLayout(layout);

        setListener();
        add(panelBuilder(), BorderLayout.CENTER);
        //        TODO offline
//        meetingBloc.chooseOrganization(organizations.get(0).getName());
    }
}
