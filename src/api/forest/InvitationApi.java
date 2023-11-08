package api.forest;

import api.Response.invitation.ListResponse;
import api.body.invitation.AcceptBody;
import api.body.invitation.CreateBody;
import api.body.invitation.RefuseBody;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

public interface InvitationApi extends BaseApi {

    @Post("/invitation/create")
    Result<Object> create(@Body CreateBody body);

    @Get("/invitation/list")
    Result<List<ListResponse>> getList(@Query Integer account);

    @Post("/invitation/accept")
    Result<Object> accept(@Body AcceptBody body);

    @Post("/invitation/refuse")
    Result<Object> refuse(@Body RefuseBody body);

}
