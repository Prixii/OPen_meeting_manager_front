package api.Response.meeting;

import lombok.Data;

@Data
public class ListResponse {
    private Integer id;
    private String title;
    private Integer creator;
    private Integer finished;
    private Integer canceled;
    private String startTime;
    private String endTime;
}
