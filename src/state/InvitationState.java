package state;

import entity.Invitation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InvitationState extends State {
    List<Invitation> invitations;

    private static final InvitationState INSTANCE = new InvitationState();

    private InvitationState() {
        invitations = new ArrayList<>();
    }

    public static InvitationState getInstance() { return INSTANCE; }
}
