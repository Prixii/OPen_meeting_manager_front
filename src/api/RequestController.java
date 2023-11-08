package api;

import api.forest.AccountApi;
import api.forest.InvitationApi;
import api.forest.MeetingApi;
import api.forest.OrganizationApi;
import com.dtflys.forest.Forest;

public class RequestController {
    private static final AccountApi accountApiImpl = Forest.client(AccountApi.class);
    private static final InvitationApi invitationApiImpl = Forest.client(InvitationApi.class);
    private static final MeetingApi meetingApiImpl = Forest.client(MeetingApi.class);
    private static final OrganizationApi organizationApiImpl = Forest.client(OrganizationApi.class);

    public static AccountApi accountApi() { return accountApiImpl; }
    public static InvitationApi invitationAPi() { return invitationApiImpl; }
    public static MeetingApi meetingApi() { return meetingApiImpl; }
    public static OrganizationApi organizationApi() { return organizationApiImpl; }
}
