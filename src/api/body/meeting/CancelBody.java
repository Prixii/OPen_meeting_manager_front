package api.body.meeting;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelBody {
    private Integer creator;
    private Integer meeting;
}
