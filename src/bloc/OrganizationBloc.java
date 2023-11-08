package bloc;

import api.RequestController;
import lombok.var;
import state.GlobalState;
import state.OrganizationState;

public class OrganizationBloc extends Bloc{
    private static final OrganizationBloc INSTANCE = new OrganizationBloc();
    private final GlobalState globalState;
    private final OrganizationState state;

    private OrganizationBloc() {
        super(OrganizationState.getInstance());
        state = (OrganizationState) super.state;
        globalState = GlobalState.getInstance();
    }

    public static OrganizationBloc getInstance() { return INSTANCE; }

    public void getOrganizations() {
        var accountId = globalState.getUser().getId();
        RequestController.organizationApi().manage(accountId, (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
                state.setOrganizationsManaged(result.getData());
                state.firePropertyChange("managedList", null, result.getData());
            }
        });
        RequestController.organizationApi().getList(accountId, (result, res, rsp) -> {
            System.out.println(result);
            if (result.getCode() == 200) {
                state.setOrganizationsJoined(result.getData());
                state.firePropertyChange("joinedList", null, result.getData());
            }
        });
    }

}
