package api.forest;

import api.body.organization.*;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

public interface OrganizationApi extends BaseApi{
    @Post("/organization/create")
    Result<Integer> create(@Body CreateBody body);

    @Post("/organization/dissolve")
    Result<Object> dissolve(@Body DissolveBody body);

    @Post("/organization/kick")
    Result<Object> kick(@Body KickBody body);

    @Post("/organization/leave")
    Result<Object> leave(@Body LeaveBody body);

    @Get("/organization/list")
    Result<List<ListResponse>> getList(@Query Integer account);

    @Get("/organization/manage")
    Result<List<ListResponse>> manage(@Query Integer account);

    @Get("/organization/member")
    Result<List<MemberResponse>> member(@Query Integer creator, @Query Integer organization);
}
