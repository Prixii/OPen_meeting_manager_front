package api.body.invitation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefuseBody {
    private Integer account;
    private Integer invitation;
}
