package state;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingState extends State{
    private static final MeetingState INSTANCE = new MeetingState();

    private MeetingState() {}

    public static MeetingState getInstance() { return INSTANCE; }


}
