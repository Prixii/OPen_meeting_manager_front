package api.Response.meeting;

import entity.Meeting;
import lombok.Data;

@Data
public class MeetingListResponse {
    private Integer id;
    private String title;
    private Integer creator;
    private Integer finished;
    private Integer canceled;
    private String startTime;
    private String endTime;

    public Meeting toMeeting() {
        return new Meeting(id, title, creator, finished, canceled, startTime, endTime);
    }
}
