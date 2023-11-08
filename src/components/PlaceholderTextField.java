package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField implements FocusListener {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        addFocusListener(this);
        setPlaceholderText();
    }

    private void setPlaceholderText() {
        setForeground(Color.GRAY);
        setText(placeholder);
    }

    private void removePlaceholderText() {
        setForeground(Color.BLACK);
        setText("");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(placeholder)) {
            removePlaceholderText();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setPlaceholderText();
        }
    }
}
