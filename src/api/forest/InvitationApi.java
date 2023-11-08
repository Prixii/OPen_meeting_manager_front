package api.forest;

import api.Response.invitation.ListResponse;
import api.body.invitation.AcceptBody;
import api.body.invitation.CreateBody;
import api.body.invitation.RefuseBody;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

@Address(host = AddressData.HOST + "/invitation", port = AddressData.PORT)
public interface InvitationApi {

    @Post("/create")
    Result<Object> create(@Body CreateBody body);

    @Get("/list")
    Result<List<ListResponse>> getList(@Query Integer account);

    @Post("/accept")
    Result<Object> accept(@Body AcceptBody body);

    @Post("/refuse")
    Result<Object> refuse(@Body RefuseBody body);

}
