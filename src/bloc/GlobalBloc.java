package bloc;

import entity.Account;
import state.GlobalState;

public class GlobalBloc extends Bloc{
    private final GlobalState globalState;
    private static final GlobalBloc INSTANCE = new GlobalBloc();

    public GlobalBloc() {
        super(GlobalState.getInstance());
        globalState = (GlobalState) super.state;
    }

    public static GlobalBloc getInstance() { return INSTANCE; }

    public void onLoginSucceed(Account account) {
        globalState.setLogin(true);
        globalState.setUser(account);
        state.firePropertyChange("loginSucceed",null, null);
    }
}
