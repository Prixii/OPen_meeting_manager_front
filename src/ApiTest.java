import api.forest.AccountApi;
import bloc.*;
import com.dtflys.forest.Forest;
import entity.Invitation;
import entity.Organization;
import lombok.var;
import org.junit.jupiter.api.Test;


public class ApiTest {
    final GlobalBloc globalBloc;
    final HelloBloc helloBloc;
    final MeetingBloc meetingBloc;
    final OrganizationBloc organizationBloc;
    final InvitationBloc invitationBloc;

    @Test
    public static void main(String[] args) {
        ApiTest apiTest = new ApiTest();

        AccountApi myApi = Forest.client(AccountApi.class);
        var result =myApi.testList();
        assert result.getCode() == 200;

        apiTest.tryApi();
    }

    ApiTest() {
        globalBloc = GlobalBloc.getInstance();
        helloBloc = HelloBloc.getInstance();
        meetingBloc = MeetingBloc.getInstance();
        organizationBloc = OrganizationBloc.getInstance();
        invitationBloc = InvitationBloc.getInstance();
    }

    void tryApi() {
        helloBloc.login("13549872457", "123456");
        meetingBloc.getMeeting();
        organizationBloc.getJoinedOrganizations();
        invitationBloc.getInvitations();
    }
}

