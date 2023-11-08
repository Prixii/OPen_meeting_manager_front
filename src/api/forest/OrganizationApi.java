package api.forest;

import api.body.organization.*;
import com.dtflys.forest.annotation.*;
import com.dtflys.forest.callback.OnSuccess;
import util.Result;

import java.util.List;

public interface OrganizationApi extends BaseApi{
    @Post(
        url = "/organization/create",
        async = true
    )
    void create(@JSONBody CreateBody body, OnSuccess<Result<Integer>> onSuccess);

    @Post(
        url = "/organization/dissolve",
        async = true
    )
    void dissolve(@JSONBody DissolveBody body, OnSuccess<Result<Object>> onSuccess);

    @Post(
        url = "/organization/kick",
        async = true
    )
    void kick(@JSONBody KickBody body, OnSuccess<Result<Object>> onSuccess);

    @Post(
        url = "/organization/leave",
        async = true
    )
    void leave(@JSONBody LeaveBody body, OnSuccess<Result<Object>> onSuccess);

    @Get(
        url = "/organization/list",
        async = true
    )
    void getList(@Query Integer account, OnSuccess<Result<List<ListResponse>>> onSuccess);

    @Get(
        url = "/organization/manage",
        async = true
    )
    void manage(@Query Integer account, OnSuccess<Result<List<ListResponse>>> onSuccess);

    @Get(
        url = "/organization/member",
        async = true
    )
    void member(@Query Integer creator, @Query Integer organization, OnSuccess<Result<List<MemberResponse>>> onSuccess);
}
