package api.body.invitation;

import lombok.Data;

@Data
public class RefuseBody {
    private Integer account;
    private Integer invitation;
}
