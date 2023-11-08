package state;

import lombok.Data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class State {
    protected PropertyChangeSupport listeners;

    public State() {
        listeners = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    public void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }
}
