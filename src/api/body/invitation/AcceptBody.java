package api.body.invitation;

import lombok.Data;

@Data
public class AcceptBody {
    private Integer account;
    private Integer invitation;
}
