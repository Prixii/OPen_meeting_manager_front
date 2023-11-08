package api.body.organization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBody {
    private Integer creator;
    private String name;
}
