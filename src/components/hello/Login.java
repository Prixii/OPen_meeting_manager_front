package components.hello;

import bloc.HelloBloc;
import components.PlaceholderTextField;
import lombok.var;
import util.FontData;
import util.SizedBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends JPanel {
    private HelloBloc helloBloc;
    private PlaceholderTextField phoneBox;
    private PlaceholderTextField passwordBox;

    Component titleBuilder() {
        JLabel label = new JLabel("Login");
        label.setFont(FontData.TITLE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(400, 40));
        return label;
    }

    Component registerButtonBuilder() {
        var row = Box.createHorizontalBox();
        var button = new JButton("No account? Register !");
        button.addActionListener(e -> {
            helloBloc.toRegisterPage();
        });
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        row.add(new Container());
        row.add(button);
        return row;
    }

    Component formBuilder() {
        Box row = Box.createHorizontalBox();
        Box column = Box.createVerticalBox();

        phoneBox = new PlaceholderTextField("Phone");
        phoneBox.setFont(FontData.BODY);
        passwordBox = new PlaceholderTextField("Password");
        passwordBox.setFont(FontData.BODY);

        column.add(new SizedBox(400, 25));
        column.add(phoneBox);
        column.add(new SizedBox(400, 25));
        column.add(passwordBox);
        column.add(new SizedBox(400, 10));
        column.add(registerButtonBuilder());
        column.add(new SizedBox(400, 5));

        row.add(new SizedBox(40, 200));
        row.add(column);
        row.add(new SizedBox(40, 200));

        return row;
    }

    Component loginButtonBuilder() {
        JButton button = new JButton("Login →");
        button.setFont(FontData.TITLE);
        button.setBackground(Color.lightGray);
        button.setBorder(new EmptyBorder(0,0,0,0));
        button.setPreferredSize(new Dimension(400, 60));
        button.setFocusPainted(false);
        button.addActionListener(e -> helloBloc.login(phoneBox.getText(), passwordBox.getText()));
        return button;
    }


    public Login() {
        setPreferredSize(new Dimension(400, 300));
        helloBloc = HelloBloc.getInstance();
        setLayout(new BorderLayout());
        add(titleBuilder(), BorderLayout.NORTH);
        add(formBuilder(), BorderLayout.CENTER);
        add(loginButtonBuilder(), BorderLayout.SOUTH);
    }
}
