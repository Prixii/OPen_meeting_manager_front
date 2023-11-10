package api.body.meeting;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateBody {
    private Integer creator;
    private String title;
    private List<Integer> participants;
    private String startTime;
    private String endTime;
}
