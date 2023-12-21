package state;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * State的基类
 */
public abstract class State {
    protected PropertyChangeSupport listeners;

    public State() {
        listeners = new PropertyChangeSupport(this);
    }

    /**
     * 添加监听器
     * @param listener 需要添加的监听器
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    /**
     * 广播变更事件
     * @param prop 事件名称
     * @param oldValue 事件涉及的旧值
     * @param newValue 事件涉及的新值
     */
    public void firePropertyChange(String prop, Object oldValue, Object newValue) {
        listeners.firePropertyChange(prop, oldValue, newValue);
    }
}
