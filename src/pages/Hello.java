package pages;

import components.hello.Login;
import components.hello.Register;
import state.HelloState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Hello extends JPanel {
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
        helloState = HelloState.getInstance();

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setIndexListener();

        add("LOGIN", new Login());
        add("REGISTER", new Register());
    };
}
