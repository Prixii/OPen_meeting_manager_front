package bloc;

import state.NavigationState;
import entity.enums.PageIndex;

public class NavigationBloc extends Bloc {
    private final NavigationState state;
    private static final NavigationBloc INSTANCE = new NavigationBloc();
    public static NavigationBloc getInstance() { return INSTANCE; }

    private NavigationBloc() {
        super(NavigationState.getInstance());
        state = (NavigationState) super.state;
    }

    public void setIndex(PageIndex index) {
        PageIndex old = state.getIndex();
        state.setIndex(index);
        state.firePropertyChange("index", old, index);
    }
}
