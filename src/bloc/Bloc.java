package bloc;

import state.State;

public abstract class Bloc {
    protected final State state;

    public Bloc(State state) {
        this.state = state;
    }
}
