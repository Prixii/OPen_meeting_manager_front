package components;

import lombok.var;
import util.ColorData;
import util.CommonUtil;
import util.FontData;
import util.SizedBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ListTitle extends JPanel {
    private final String title;

    Component labelBuilder() {
        var label = new JLabel(title);
        label.setFont(FontData.BODY);
        label.setForeground(Color.BLACK);
        label.setFont(FontData.LARGE_TITLE);
        return label;
    }

    public ListTitle(String title, JButton functionButton) {
        this.title = title;
        setPreferredSize(new Dimension(900, 80));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        var row = Box.createHorizontalBox();
        row.setBorder(new EmptyBorder(0,20,0,40));

        row.add(labelBuilder());
        row.add(Box.createHorizontalGlue());
        if (functionButton != null) {
            functionButton.setPreferredSize(new Dimension(90, 70));
            functionButton.setBackground(ColorData.PRIMARY);
            row.add(functionButton);
        }
        add(row);


    }
}
