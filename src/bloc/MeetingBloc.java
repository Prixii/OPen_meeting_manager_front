package bloc;

import api.RequestController;
import api.body.meeting.CancelBody;
import api.body.meeting.CreateBody;
import api.body.meeting.FinishBody;
import entity.Organization;
import state.GlobalState;
import state.MeetingState;
import state.OrganizationState;

import java.util.List;
import java.util.Objects;

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
                state.firePropertyChange("refresh", null, result.getData());
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

    public void onCreateMeeting(List<Integer> participants, String  title, String start, String end) {
        RequestController.meetingApi().create(new CreateBody(account(), title, participants, start, end), (result, res, rsp) -> {

        });
    }
}
