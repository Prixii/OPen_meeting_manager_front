package api.body.organization;

import lombok.Data;

@Data
public class KickBody {
    private Integer account;
    private Integer organization;
    private Integer creator;
}
