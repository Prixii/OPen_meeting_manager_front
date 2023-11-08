package api.body.meeting;

import lombok.Data;

@Data
public class CancelBody {
    private Integer creator;
    private Integer meeting;
}
