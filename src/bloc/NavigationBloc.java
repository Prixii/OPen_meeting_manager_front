package bloc;

import State.NavigationState;
import entity.enums.PageIndex;

import java.beans.PropertyChangeListener;

public class NavigationBloc {
    private final NavigationState state;
    private static final NavigationBloc INSTANCE = new NavigationBloc();
    public static NavigationBloc getInstance() { return INSTANCE; }

    private NavigationBloc() {
        state = NavigationState.getInstance();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        state.addPropertyChangeListener(listener);
    }

    public void setIndex(PageIndex index) {
        PageIndex old = state.getIndex();
        state.setIndex(index);
        state.firePropertyChange("index", old, index);
    }
}
