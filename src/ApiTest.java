import api.AccountApi;
import com.dtflys.forest.Forest;
import lombok.var;
import org.junit.jupiter.api.Test;


public class ApiTest {

    @Test
    public static void main(String[] args) {
        AccountApi myApi = Forest.client(AccountApi.class);
        var result =myApi.testList();
        assert result.getCode() == 200;
        System.out.println(result);
    }
}

