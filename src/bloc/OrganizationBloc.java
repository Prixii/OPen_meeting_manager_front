package bloc;

import api.RequestController;
import api.body.organization.CreateBody;
import api.body.organization.DissolveBody;
import api.body.organization.KickBody;
import api.body.organization.LeaveBody;
import entity.Organization;
import lombok.var;
import state.GlobalState;
import state.OrganizationState;

import java.util.Collections;
import java.util.Objects;

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

    private int account() {
        return globalState.getUser().getId();
    }

    public void getOrganizations() {
        getManagedOrganizations();
        getJoinedOrganizations();
    }

    public void getManagedOrganizations() {
        RequestController.organizationApi().manage(account(), (result, res, rsp) -> {
            if (result.getCode() == 200) {
                var records = result.getData();
                Collections.reverse(records);
                state.setOrganizationsManaged(records);
                state.firePropertyChange("managedList", null, result.getData());
            }
        });
    }

    public void getJoinedOrganizations() {
        RequestController.organizationApi().getList(account(), (result, res, rsp) -> {
            if (result.getCode() == 200) {
                var records = result.getData();
                Collections.reverse(records);
                state.setOrganizationsJoined(records);
                state.firePropertyChange("joinedList", null, result.getData());
            }
        });
    }

    public void createOrganization(String name) {
        RequestController.organizationApi().create(new CreateBody(account(), name), (result, res, rsp) -> {
            if (result.getCode() == 200) {
                var organizationId = result.getData();
                var newOrganization = new Organization(organizationId, account(), name);
                state.addOrganizationManaged(newOrganization);
                state.firePropertyChange("create", null, newOrganization);
            }
        });
    }

    public void dissolveOrganization(Integer organization) {
        RequestController.organizationApi().dissolve(new DissolveBody(account(), organization), (result, rep, rsp) -> {
            if (result.getCode() == 200) {
                state.removeOrganization(organization);
                state.firePropertyChange("dissolve", null, organization);
            }
        });
    }

    public void toOrganizationDetail(Integer organization) {
        Organization target = state.findOrganization(organization);
        RequestController.organizationApi().member(account(),organization, (result, res, rsp) -> {
            if (result.getCode() == 200) {
                state.firePropertyChange("toDetail", result.getData(), target);
            }
        });
    }

    public void toOverView() {
        state.firePropertyChange("toOverView", null, null);
    }

    public void kick(Integer organization, Integer account) {
        RequestController.organizationApi().kick(new KickBody(account, organization, account()),(result, res, rsp) -> {
            if (result.getCode() == 200) {
                state.firePropertyChange("remove", null, account);
            }
        });
    }

    public void leave(Integer organization) {
        RequestController.organizationApi().leave(new LeaveBody(account(), organization), (result, res, rsp) -> {
            state.firePropertyChange("leave", null, organization);
        });
    }


}
