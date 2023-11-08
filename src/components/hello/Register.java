package components.hello;

import javax.swing.*;

public class Register extends JPanel {
    private JButton button;
    private JLabel label;

    public Register() {
        button = new JButton("Click");
        label = new JLabel("REGISTER");
        add(button);
        add(label);
    }
}
