package bloc;


import state.HelloState;

import java.beans.PropertyChangeListener;

public class HelloBloc  extends Bloc{
    private final HelloState state;
    private static final HelloBloc INSTANCE = new HelloBloc();

    private HelloBloc() {
        super(HelloState.getInstance());
        this.state = (HelloState) super.state;
    }

    public static HelloBloc getInstance() { return INSTANCE; }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        state.addPropertyChangeListener(listener);
    }


}
