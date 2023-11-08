package api.forest;

import api.Response.meeting.ListResponse;
import api.body.meeting.CancelBody;
import api.body.meeting.CreateBody;
import api.body.meeting.FinishBody;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

public interface MeetingApi extends BaseApi {
    @Post("/meeting/cancel")
    Result<Object> cancel(@Body CancelBody body);

    @Post("/meeting/create")
    Result<Integer> create(@Body CreateBody body);

    @Post("/meeting/finish")
    Result<Object> finish(@Body FinishBody body);

    @Get("/meeting/list")
    Result<List<ListResponse>> getList(@Query Integer account);

    @Get("/meeting/manage")
    Result<List<ListResponse>> manage(@Query Integer account);
}
