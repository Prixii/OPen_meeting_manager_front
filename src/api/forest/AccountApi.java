package api.forest;

import api.Response.account.SearchResponse;
import api.TestData;
import api.body.account.LoginBody;
import api.body.account.RegisterBody;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

public interface AccountApi extends BaseApi {
    @Get("/account/list")
    Result<List<TestData>> testList();

    @Post("/account/login")
    Result<Object> login(@Body LoginBody body);

    @Post("/account/register")
    Result<Object> register(@Body RegisterBody body);

    @Get("/account/search")
    Result<SearchResponse> search(@Query String phone);
}
