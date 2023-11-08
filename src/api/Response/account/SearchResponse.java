package api.Response.account;

import lombok.Data;

@Data
public class SearchResponse {
    private Integer id;
    private String name;
    private String phone;
    private Integer dissolved;

}
