package State;

import entity.enums.PageIndex;
import lombok.Data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Data
public class NavigationState {
    private static final NavigationState INSTANCE = new NavigationState();
    private PageIndex index;
    PropertyChangeSupport listeners;

    private NavigationState() {
        listeners = new PropertyChangeSupport(this);
        index = PageIndex.INVITATION_LIST;
    }

    public static NavigationState getInstance() { return INSTANCE; }

    public void setIndex(PageIndex index) {
        PageIndex old = this.index;
        this.index = index;
        firePropertyChange("changeIndex", old, index);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        listeners.addPropertyChangeListener(listener);
    }

    protected void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }

}
