package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting{
    private Integer id;
    private String title;
    private Integer creator;
    private Integer finished;
    private Integer canceled;
    private String startTime;
    private String endTime;

    public boolean belongTo(Integer account) { return Objects.equals(creator, account); }

    public boolean available() {
        return (canceled == 0) && (finished == 0);
    }

    public void finish() { finished = 1; }
    public void cancel() { canceled = 1; }
}
