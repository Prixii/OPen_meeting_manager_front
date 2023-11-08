package api.body.meeting;

import lombok.Data;

import java.util.List;

@Data
public class CreateBody {
    private Integer creator;
    private String title;
    private List<Integer> participants;
    private String startTime;
    private String endTime;
}
