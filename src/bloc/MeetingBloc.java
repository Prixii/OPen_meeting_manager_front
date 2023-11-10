package bloc;

import api.RequestController;
import state.GlobalState;
import state.MeetingState;

public class MeetingBloc extends Bloc {
    private static final MeetingBloc INSTANCE = new MeetingBloc();
    private final MeetingState state;
    private final GlobalState globalState;

    private MeetingBloc() {
        super(MeetingState.getInstance());
        this.state = (MeetingState) super.state;
        globalState = GlobalState.getInstance();
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
}
