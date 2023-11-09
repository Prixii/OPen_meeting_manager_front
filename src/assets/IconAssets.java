package assets;

import javax.swing.*;
import java.awt.*;

public class IconAssets {
    final static int size = 30;
    public static ImageIcon DELETE = generateIcon("delete");
    public static ImageIcon EVERY_USER = generateIcon("every-user");
    public static ImageIcon ARROW_LEFT = generateIcon("arrow-circle-left");
    public static ImageIcon PEOPLE_PLUS = generateIcon("people-plus");
    public static ImageIcon PEOPLE_MINUS = generateIcon("people-minus");


    static ImageIcon generateIcon(String path) {
        return new ImageIcon(new ImageIcon("src/assets/"+path+".png").getImage().getScaledInstance(size,size, Image.SCALE_SMOOTH));
    }
}
