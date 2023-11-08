package api.body.organization;

import lombok.Data;

@Data
public class CreateBody {
    private Integer creator;
    private String name;
}
