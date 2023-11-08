package bloc;


import api.RequestController;
import api.body.account.LoginBody;
import api.body.account.RegisterBody;
import entity.Account;
import lombok.var;
import state.HelloState;

public class HelloBloc extends Bloc{
    private final HelloState state;
    private final GlobalBloc globalBloc;
    private static final HelloBloc INSTANCE = new HelloBloc();

    private HelloBloc() {
        super(HelloState.getInstance());
        this.state = (HelloState) super.state;
        globalBloc = GlobalBloc.getInstance();
    }

    public static HelloBloc getInstance() { return INSTANCE; }

    public void toRegisterPage() {
        String old = state.getIndex();
        state.setIndex("REGISTER");
        state.firePropertyChange("index", old, "REGISTER");
    }

    public void toLoginPage() {
        String old = state.getIndex();
        state.setIndex("LOGIN");
        state.firePropertyChange("index", old, "LOGIN");
    }

    public void register(String name, String phone, String password) {
        RequestController.accountApi().register(new RegisterBody(name, phone, password), (result, req, res) -> {
            if (result.getCode() == 200) {
                var id = result.getData();
                globalBloc.onLoginSucceed(new Account(id, name, phone, password));
            }
            System.out.println(result);
        });
    }

    public void login(String phone, String password) {
        RequestController.accountApi().login(new LoginBody(phone, password), (result, req, res) -> {
            if (result.getCode() == 200) {
                var response = result.getData();
                globalBloc.onLoginSucceed(new Account(response.getId(),response.getName(),phone,password));

            }
        });
    }
}
