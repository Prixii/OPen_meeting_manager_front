package api.body.organization;

import lombok.Data;

@Data
public class ListResponse {
    private String name;
    private Integer creator;
    private Integer id;
}
