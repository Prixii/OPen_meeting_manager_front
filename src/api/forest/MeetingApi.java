package api.forest;

import api.Response.meeting.ListResponse;
import api.body.meeting.CancelBody;
import api.body.meeting.CreateBody;
import api.body.meeting.FinishBody;
import com.dtflys.forest.annotation.*;
import com.dtflys.forest.callback.OnSuccess;
import util.Result;

import java.util.List;

public interface MeetingApi extends BaseApi {
    @Post(
        url = "/meeting/cancel",
        async = true
    )
    void cancel(@JSONBody CancelBody body, OnSuccess<Result<Object>> onSuccess);

    @Post(
        url = "/meeting/create",
        async = true
    )
    void create(@JSONBody CreateBody body, OnSuccess<Result<Integer>> onSuccess);

    @Post(
        url = "/meeting/finish",
        async = true
    )
    void finish(@JSONBody FinishBody body, OnSuccess<Result<Object>> onSuccess);

    @Get(
        url = "/meeting/list",
        async = true
    )
    void getList(@Query Integer account, OnSuccess<Result<List<ListResponse>>> onSuccess);

    @Get(
        url = "/meeting/manage",
        async = true
    )
    void manage(@Query Integer account, OnSuccess<Result<List<ListResponse>>> onSuccess);
}
