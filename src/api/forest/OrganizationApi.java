package api.forest;

import api.body.organization.*;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

@Address(host = AddressData.HOST + "/organization", port = AddressData.PORT)
public interface OrganizationApi {
    @Post("/create")
    Result<Integer> create(@Body CreateBody body);

    @Post("/dissolve")
    Result<Object> dissolve(@Body DissolveBody body);

    @Post("/kick")
    Result<Object> kick(@Body KickBody body);

    @Post("/leave")
    Result<Object> leave(@Body LeaveBody body);

    @Get("/list")
    Result<List<ListResponse>> getList(@Query Integer account);

    @Get("/manage")
    Result<List<ListResponse>> manage(@Query Integer account);

    @Get("/member")
    Result<List<MemberResponse>> member(@Query Integer creator, @Query Integer organization);
}
