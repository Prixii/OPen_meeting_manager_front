package api.forest;

import api.Response.meeting.ListResponse;
import api.body.meeting.CancelBody;
import api.body.meeting.CreateBody;
import api.body.meeting.FinishBody;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

@Address(host = AddressData.HOST + "/meeting", port = AddressData.PORT)
public interface MeetingApi {
    @Post("/cancel")
    Result<Object> cancel(@Body CancelBody body);

    @Post("/create")
    Result<Integer> create(@Body CreateBody body);

    @Post("/finish")
    Result<Object> finish(@Body FinishBody body);

    @Get("/list")
    Result<List<ListResponse>> getList(@Query Integer account);

    @Get("/manage")
    Result<List<ListResponse>> manage(@Query Integer account);
}
