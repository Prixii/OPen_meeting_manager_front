package state;

import entity.Invitation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvitationState extends State {
    List<Invitation> invitations;

    private static final InvitationState INSTANCE = new InvitationState();

    private InvitationState() {
        invitations = new ArrayList<>();
    }

    public static InvitationState getInstance() { return INSTANCE; }
}
