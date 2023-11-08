package api.body.organization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DissolveBody {
    private Integer creator;
    private Integer organization;
}
