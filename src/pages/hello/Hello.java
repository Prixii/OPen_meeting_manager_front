package pages.hello;


import components.hello.Login;
import components.hello.Register;
import lombok.var;

import javax.swing.*;
import java.awt.*;

public class Hello extends JPanel {
    public Hello(){
        var cardLayout = new CardLayout();
        setLayout(cardLayout);

        add("LOGIN", new Login());
        add("REGISTER", new Register());

    };
}
