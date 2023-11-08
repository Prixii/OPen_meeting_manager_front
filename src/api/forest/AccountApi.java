package api.forest;

import api.Response.account.SearchResponse;
import api.TestData;
import api.body.account.LoginBody;
import api.body.account.RegisterBody;
import com.dtflys.forest.annotation.*;
import util.Result;

import java.util.List;

@Address(host = AddressData.HOST + "/account", port = AddressData.PORT)
public interface AccountApi {
    @Get("/list")
    Result<List<TestData>> testList();

    @Post("/login")
    Result<Object> login(@Body LoginBody body);

    @Post("/register")
    Result<Object> register(@Body RegisterBody body);

    @Get("/search")
    Result<SearchResponse> search(@Query String phone);
}
