package pages.hello;

import javax.swing.*;

public class Login extends JPanel {
    private JButton button;
    private JLabel label;

    public Login() {
        button = new JButton("Click");
        label = new JLabel("Label!");
        add(button);
        add(label);
    }
}
