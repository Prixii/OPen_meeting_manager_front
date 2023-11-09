package api.body.invitation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBody {
    private String phone;
    private Integer creator;
    private Integer organization;
}
