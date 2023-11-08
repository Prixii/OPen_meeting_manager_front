package components.hello;

import bloc.HelloBloc;

import javax.swing.*;
import java.awt.*;

public class Register extends JPanel {
    private HelloBloc helloBloc;
    private JButton button;
    private JLabel label;

    public Register() {
        helloBloc = HelloBloc.getInstance();
        setPreferredSize(new Dimension(400, 300));
        button = new JButton("Click");
        button.addActionListener(e -> {
            helloBloc.toLoginPage();
        });
        label = new JLabel("REGISTER");
        add(button);
        add(label);
    }
}
