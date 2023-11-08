package state;

import entity.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalState extends State {
    private static final GlobalState INSTANCE = new GlobalState();
    private boolean login;
    private Account user;


    GlobalState() {
        login = false;
        user = new Account();
    }

    public static GlobalState getInstance() { return INSTANCE; }

}
