package api.body.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterBody {
    private String name;
    private String phone;
    private String password;
}
