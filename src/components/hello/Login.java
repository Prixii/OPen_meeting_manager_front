package components.hello;

import javax.swing.*;

public class Login extends JPanel {
    private JButton button;
    private JLabel label;

    public Login() {
        button = new JButton("Click");
        label = new JLabel("LOGIN");
        add(button);
        add(label);
    }
}
