package components.meeting;

import bloc.MeetingBloc;
import components.PlaceholderTextField;
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
    PlaceholderTextField titleBox;
    PlaceholderTextField startTimeBox;
    PlaceholderTextField endTimeBox;

    List<Organization> organizations;
    List<Member> members;
    Set<Integer> participants;
    Map<Integer, Component> itemMap;


    void setListener() {
        setAddParticipantListener();
        setRemoveParticipantListener();
        setChooseOrganizationListener();
        setOnCreateListener();
    }

    void setAddParticipantListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "add")) { return; }
            for (Member member:
                 members) {
                if (Objects.equals(member.getAccount(), evt.getNewValue())) {
                    if (participants.contains(member.getAccount())) {return;}
                    participants.add(member.getAccount());
                    participantListView.remove(participantPlaceHolder);
                    var item = new ParticipantItem(member);
                    itemMap.put(member.getAccount(), item);
                    participantListView.add(item);
                    if (participants.size() <= 5) {
                        participantPlaceHolder = Box.createVerticalStrut(310 - 60 * participants.size());
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
                participantPlaceHolder = Box.createVerticalStrut(310 - 60 * participants.size());
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

    void setOnCreateListener() {
        meetingState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "create")) { return; }
            reset();
            this.setVisible(false);
        });

    }
    Component baseEditor() {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 80));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        var column = Box.createVerticalBox();

        titleBox = new PlaceholderTextField("Title:");
        startTimeBox = new PlaceholderTextField("StartTime");
        endTimeBox = new PlaceholderTextField("EndTime");

        var titlePanel = formBuilder(titleBox, "Title");
        titlePanel.setPreferredSize(new Dimension(800, 40));

        var startTimePanel = formBuilder(startTimeBox, "Start Time");
        titlePanel.setPreferredSize(new Dimension(390, 40));

        var endTimePanel = formBuilder(endTimeBox, "End Time");
        titlePanel.setPreferredSize(new Dimension(390, 40));

        var timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setLayout(new GridLayout(1, 2));

        var timeContainer = new JPanel();
        timeContainer.setPreferredSize(new Dimension(800, 40));
        timeContainer.setLayout(new BoxLayout(timeContainer, BoxLayout.X_AXIS));
        var timeEditor = Box.createHorizontalBox();
        timeContainer.add(timeEditor);
        timeEditor.add(startTimePanel);
        timeEditor.add(Box.createHorizontalStrut(20));
        timeEditor.add(endTimePanel);

        timePanel.add(timeContainer);

        column.add(titlePanel);
        column.add(Box.createVerticalStrut(20));
        column.add(timePanel);
        panel.add(column);
        return panel;
    }

    Component formBuilder(PlaceholderTextField textField, String title) {
        var titlePanel = Box.createHorizontalBox();
        var titleLabel = new JLabel(title);
        titleLabel.setFont(FontData.BODY);
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(10));
        titlePanel.add(textField);
        return titlePanel;
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        var column = Box.createVerticalBox();
        comboBoxBuilder();
        column.add(comboBoxPanel, BorderLayout.NORTH);
        memberListView = Box.createVerticalBox();
        memberListView.add(new JPanel());

        var scrollPane = new JScrollPane(memberListView, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setPreferredSize(new Dimension(360, 310));
        column.add(Box.createVerticalStrut(20));
        column.add(scrollPane, BorderLayout.CENTER);
        panel.add(column);
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
        comboBox.setFont(FontData.BODY);
        for (Organization organization:
                organizationState.getOrganizationsManaged()) {
            comboBox.addItem(organization.getName());
        }
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

    Component buttonGroupBuilder() {
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 40));
        panel.setBorder(new EmptyBorder(10,0,10,0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        var row = Box.createHorizontalBox();
        row.setBackground(Color.CYAN);
        var confirmButton = new JButton("Create");
        var cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            reset();
            this.setVisible(false);
        });

//        TODO Confirm
        confirmButton.addActionListener(e -> meetingBloc.onCreateMeeting(participants, titleBox.getText(), startTimeBox.getText(), endTimeBox.getText()));

        row.add(Box.createHorizontalGlue());
        row.add(cancelButton);
        row.add(Box.createHorizontalGlue());
        row.add(confirmButton);
        row.add(Box.createHorizontalGlue());

        panel.add(row);
        return panel;
    }

    void reset() {
        titleBox.setText("");
        participants.clear();
        members.clear();
        startTimeBox.setText("");
        endTimeBox.setText("");
    }

    public MeetingCreator(JFrame frame, String title) {
        super(frame, title);

        members = new ArrayList<>();
        participants = new HashSet<>();
        itemMap = new HashMap<>();

        meetingBloc = MeetingBloc.getInstance();
        meetingState =MeetingState.getInstance();
        organizationState = OrganizationState.getInstance();

        organizations = organizationState.getOrganizationsManaged();

        setSize(new Dimension(800,520));

        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 520));

        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        var layout = new BorderLayout();
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10,10,10,10));

        setListener();
        panel.add(baseEditor(), BorderLayout.NORTH);
        panel.add(panelBuilder(), BorderLayout.CENTER);
        panel.add(buttonGroupBuilder(), BorderLayout.SOUTH);

        add(panel);

        meetingBloc.chooseOrganization(organizations.get(0).getName());
    }

}
