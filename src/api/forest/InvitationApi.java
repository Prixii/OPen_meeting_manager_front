package api.forest;

import api.body.invitation.AcceptBody;
import api.body.invitation.CreateBody;
import api.body.invitation.RefuseBody;
import com.dtflys.forest.annotation.*;
import com.dtflys.forest.callback.OnSuccess;
import entity.Invitation;
import entity.Organization;
import util.Result;

import java.util.List;

public interface InvitationApi extends BaseApi {

    @Post(
        url = "/invitation/create",
        async = true
    )
    void create(@JSONBody CreateBody body, OnSuccess<Result<Object>> onSuccess);

    @Get(
        url = "/invitation/list",
        async = true
    )
    void getList(@Query("account") Integer account, OnSuccess<Result<List<Invitation>>> onSuccess);

    @Post(
        url = "/invitation/accept",
        async = true
    )
    void accept(@JSONBody AcceptBody body, OnSuccess<Result<Organization>> onSuccess);

    @Post(
        url = "/invitation/refuse",
        async = true
    )
    void refuse(@JSONBody RefuseBody body, OnSuccess<Result<Object>> onSuccess);

}
