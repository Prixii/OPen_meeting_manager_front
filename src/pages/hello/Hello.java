package pages.hello;


import bloc.HelloBloc;
import components.hello.Login;
import components.hello.Register;
import state.GlobalState;
import state.HelloState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Hello extends JPanel {
    private final HelloBloc helloBloc;
    private final HelloState helloState;
    CardLayout cardLayout;


    void setIndexListener() {
        helloState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "index")) { return; }
            if (evt.getOldValue() != evt.getNewValue()) {
                cardLayout.show(this, (String) evt.getNewValue());
            }
        });
    }

    public Hello(){
        helloBloc = HelloBloc.getInstance();
        helloState = HelloState.getInstance();

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setIndexListener();

        add("LOGIN", new Login());
        add("REGISTER", new Register());
    };
}
