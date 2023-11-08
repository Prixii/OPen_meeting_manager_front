package api.forest;

import api.Response.account.LoginResponse;
import api.Response.account.SearchResponse;
import api.TestData;
import api.body.account.LoginBody;
import api.body.account.RegisterBody;
import com.dtflys.forest.annotation.*;
import com.dtflys.forest.callback.OnSuccess;
import util.Result;

import java.util.List;

public interface AccountApi extends BaseApi {
    @Get("/account/list")
    Result<List<TestData>> testList();

    @Post(
        url = "/account/login",
        async = true
    )
    void login(@JSONBody LoginBody body, OnSuccess<Result<LoginResponse>> onSuccess);

    @Post(
        url = "/account/register",
        async = true
    )
    void register(@JSONBody RegisterBody body, OnSuccess<Result<Integer> > onSuccess);

    @Get(
        url = "/account/search",
        async = true
    )
    Result<SearchResponse> search(@Query("phone") String phone, OnSuccess<String > onSuccess);
}
