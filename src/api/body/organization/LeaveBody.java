package api.body.organization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveBody {
    private Integer account;
    private Integer organization;
}
