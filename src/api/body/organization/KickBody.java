package api.body.organization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KickBody {
    private Integer account;
    private Integer organization;
    private Integer creator;
}
