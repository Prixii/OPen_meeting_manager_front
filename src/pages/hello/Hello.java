package pages.hello;


import bloc.HelloBloc;
import components.hello.Login;
import components.hello.Register;
import lombok.var;

import javax.swing.*;
import java.awt.*;

public class Hello extends JPanel {
    private final HelloBloc helloBloc;

    public Hello(){
        helloBloc = HelloBloc.getInstance();
        var cardLayout = new CardLayout();
        setLayout(cardLayout);

        add("LOGIN", new Login());
        add("REGISTER", new Register());

    };
}
