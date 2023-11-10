package bloc;

import api.RequestController;
import entity.Organization;
import state.GlobalState;
import state.MeetingState;
import state.OrganizationState;

import java.util.Currency;
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

    }

    public void onCancel(Integer meeting) {

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
}
