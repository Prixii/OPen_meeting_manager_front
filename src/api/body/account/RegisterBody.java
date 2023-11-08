package api.body.account;

import lombok.Data;

@Data
public class RegisterBody {
    private String name;
    private String password;
    private String phone;
}
