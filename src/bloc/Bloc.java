package bloc;

import state.State;

/**
 * Bloc的基类
 */
public abstract class Bloc {
    protected final State state;

    public Bloc(State state) {
        this.state = state;
    }
}
