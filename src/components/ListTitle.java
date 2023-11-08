package components;

import lombok.var;
import util.FontData;
import util.SizedBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ListTitle extends JPanel {
    private final String title;

    Component labelBuilder() {
        var label = new JLabel(title);
        label.setFont(FontData.BODY);
        label.setForeground(Color.white);
        return label;
    }

    public ListTitle(String title, JButton functionButton) {
        this.title = title;
        setPreferredSize(new Dimension(900, 40));
        setBackground(Color.DARK_GRAY);


        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(0,20,0,40));

        row.add(labelBuilder());
        row.add(Box.createHorizontalGlue());
        if (functionButton != null) {
            row.add(functionButton);
        }

        add(row);
    }
}
