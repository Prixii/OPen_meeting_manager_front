package api;

import com.dtflys.forest.annotation.Request;
import util.Result;

import java.util.List;

public interface AccountApi {
    @Request("http://localhost:8080/account/list")
    Result<List<TestData>> testList();
}
