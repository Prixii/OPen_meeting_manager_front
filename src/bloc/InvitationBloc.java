package bloc;

import api.RequestController;
import api.body.invitation.AcceptBody;
import api.body.invitation.RefuseBody;
import lombok.var;
import state.GlobalState;
import state.InvitationState;

public class InvitationBloc extends Bloc{
    private static final InvitationBloc INSTANCE = new InvitationBloc();
    private final InvitationState state;
    private final GlobalState globalState;

    private InvitationBloc() {
        super(InvitationState.getInstance());
        state = (InvitationState) super.state;
        globalState = GlobalState.getInstance();
    }

    public static InvitationBloc getInstance() { return INSTANCE; }

    private int account() {
        return globalState.getUser().getId();
    }

    public void getInvitations() {
        var accountId = globalState.getUser().getId();
        RequestController.invitationAPi().getList(accountId, (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
                state.setInvitations(result.getData());
                state.firePropertyChange("invitationList", null, result.getData());
            }
        });
    }

    public void onInvite(Integer organization, String phone) {
        var body = new api.body.invitation.CreateBody(phone, account(), organization);
    }

    public void onAccept(Integer invitation) {
        RequestController.invitationAPi().accept(new AcceptBody(account(), invitation), (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
//                TODO
                state.firePropertyChange("remove", null, invitation);
            }
        });
    }

    public void onRefuse(Integer invitation) {
        RequestController.invitationAPi().refuse(new RefuseBody(account(), invitation), (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
//                TODO
                state.firePropertyChange("remove", null, invitation);
            }
        });
    }
}
