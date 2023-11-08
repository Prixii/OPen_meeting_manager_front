package api.body.invitation;

import lombok.Data;

@Data
public class CreateBody {
    private Integer account;
    private Integer creator;
    private Integer organization;
}
