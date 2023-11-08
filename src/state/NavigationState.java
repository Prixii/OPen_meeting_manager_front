package state;

import entity.enums.PageIndex;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@EqualsAndHashCode(callSuper = true)
@Data
public class NavigationState extends State {
    private static final NavigationState INSTANCE = new NavigationState();
    private PageIndex index;

    private NavigationState() {
        listeners = new PropertyChangeSupport(this);
        index = PageIndex.INVITATION_LIST;
    }

    public static NavigationState getInstance() { return INSTANCE; }

}
