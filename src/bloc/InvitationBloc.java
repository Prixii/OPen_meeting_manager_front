package bloc;

import api.RequestController;
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
}
