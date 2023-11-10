package bloc;

import api.RequestController;
import api.Response.meeting.MeetingListResponse;
import api.body.meeting.CancelBody;
import api.body.meeting.CreateBody;
import api.body.meeting.FinishBody;
import entity.Meeting;
import entity.Organization;
import lombok.var;
import state.GlobalState;
import state.MeetingState;
import state.OrganizationState;

import java.util.*;

public class MeetingBloc extends Bloc {
    private static final MeetingBloc INSTANCE = new MeetingBloc();
    private final MeetingState state;
    private final GlobalState globalState;
    private OrganizationState organizationState;

    private MeetingBloc() {
        super(MeetingState.getInstance());
        this.state = (MeetingState) super.state;
        globalState = GlobalState.getInstance();
        organizationState = OrganizationState.getInstance();
    }

    public static MeetingBloc getInstance() { return INSTANCE; }

    private int account() {
        return globalState.getUser().getId();
    }

    public void getMeeting() {
        RequestController.meetingApi().getList(account(), (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
                List<MeetingListResponse> records = result.getData();
                Collections.reverse(records);
                state.firePropertyChange("refresh", null, records);
            }
        });
    }

    public void onFinish(Integer meeting) {
        RequestController.meetingApi().finish(new FinishBody(account(), meeting), (result, res, rsp) -> {
            if (result.getCode() == 200) {
                state.firePropertyChange("finish", null, meeting);
            }
        });
    }

    public void onCancel(Integer meeting) {
        RequestController.meetingApi().cancel(new CancelBody(account(), meeting), (result, res, rsp) -> {
            state.firePropertyChange("finish", null, meeting);
        });
    }

    public void onAddParticipant(Integer account) {
        state.firePropertyChange("add", null, account);
    }

    public void onRemoveParticipant(Integer account) {

        state.firePropertyChange("remove", null, account);
    }

    public void chooseOrganization(String organizationName) {
        for (Organization organization:
             organizationState.getOrganizationsManaged()) {
            if (Objects.equals(organization.getName(), organizationName)) {
                RequestController.organizationApi().member(organization.getCreator(), organization.getId(), (result, res, rsp) -> {
                    System.out.println(result);
                    if (result.getCode() == 200) {
                        state.setCurrentOrganization(organization);
                        state.firePropertyChange("chooseOrganization", null, result.getData());
                    }
                });
                break;
            }
        }
    }

    public void onCreateMeeting(Set<Integer> participants, String  title, String start, String end) {
        List<Integer> participantList = new ArrayList<>(participants);
        participantList.add(account());
        RequestController.meetingApi().create(new CreateBody(account(), title, participantList, start, end), (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
                state.firePropertyChange("create", null, new Meeting(result.getData(), title, account(), 0, 0, start, end));
            }
        });
    }
}
