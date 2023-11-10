package components.hello;

import assets.IconAssets;
import bloc.HelloBloc;
import components.PlaceholderTextField;
import lombok.var;
import util.CommonUtil;
import util.FontData;
import util.SizedBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class Register extends JPanel {
    private HelloBloc helloBloc;
    private PlaceholderTextField nameBox;
    private PlaceholderTextField phoneBox;
    private PlaceholderTextField passwordBox;
    private PlaceholderTextField confirmPasswordBox;

    Component titleBuilder() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        var row = Box.createHorizontalBox();
        JLabel label = new JLabel("Register");
        label.setFont(FontData.TITLE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(400, 40));
        row.add(backButtonBuilder());
        row.add(label);
        row.add(Box.createHorizontalStrut(40));

        return row;
    }

    Component backButtonBuilder() {
        var backButton = CommonUtil.iconButton(IconAssets.ARROW_LEFT, false);
        backButton.addActionListener(e -> {
            helloBloc.toLoginPage();
        });

        return backButton;
    }

    Component formBuilder() {
        var row = Box.createHorizontalBox();
        var column = Box.createVerticalBox();

        nameBox = new PlaceholderTextField("Name");
        nameBox.setFont(FontData.BODY);
        phoneBox = new PlaceholderTextField("Phone");
        phoneBox.setFont(FontData.BODY);
        passwordBox = new PlaceholderTextField("Password");
        passwordBox.setFont(FontData.BODY);
        confirmPasswordBox = new PlaceholderTextField("ConfirmPassword");
        confirmPasswordBox.setFont(FontData.BODY);

        column.add(nameBox);
        column.add(new SizedBox(400, 5));
        column.add(phoneBox);
        column.add(new SizedBox(400, 5));
        column.add(passwordBox);
        column.add(new SizedBox(400, 5));
        column.add(confirmPasswordBox);
        column.add(new SizedBox(400, 5));

        row.add(new SizedBox(40, 200));
        row.add(column);
        row.add(new SizedBox(40, 200));

        return row;
    }

    Component registerButtonBuilder() {
        JButton button = new JButton("Register →");
        button.setFont(FontData.TITLE);
        button.setBackground(Color.lightGray);
        button.setBorder(new EmptyBorder(0,0,0,0));
        button.setPreferredSize(new Dimension(400, 60));
        button.setFocusPainted(false);
        button.addActionListener(e ->{
            if (Objects.equals(nameBox.getText(), "Name") ||nameBox.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Illegal Name.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!Objects.equals(passwordBox.getText(), confirmPasswordBox.getText())) {
                JOptionPane.showMessageDialog(null, "Please confirm your password.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            helloBloc.register(nameBox.getText(), phoneBox.getText(), passwordBox.getText());
        });
        return button;
    }

    public Register() {
        helloBloc = HelloBloc.getInstance();
        setPreferredSize(new Dimension(400, 300));
        helloBloc = HelloBloc.getInstance();
        setLayout(new BorderLayout());
        add(titleBuilder(), BorderLayout.NORTH);
        add(formBuilder(), BorderLayout.CENTER);
        add(registerButtonBuilder(), BorderLayout.SOUTH);
    }
}
