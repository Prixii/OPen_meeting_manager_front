package bloc;

import state.State;

import java.beans.PropertyChangeListener;

public abstract class Bloc {
    protected final State state;

    public Bloc(State state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        state.addPropertyChangeListener(listener);
    }
}
