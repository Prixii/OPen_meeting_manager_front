package assets;

import javax.swing.*;
import java.awt.*;

public class IconAssets {
    final static int size = 30;
    public static ImageIcon DELETE = generateIcon("delete");
    public static ImageIcon EVERY_USER = generateIcon("every-user");

    static ImageIcon generateIcon(String path) {
        return new ImageIcon(new ImageIcon("src/assets/"+path+".png").getImage().getScaledInstance(size,size, Image.SCALE_SMOOTH));
    }
}
