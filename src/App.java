import lombok.var;
import pages.hello.Hello;
import pages.navigator.Navigator;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        App swingApp = new App();
//        swingApp.showHello();
        swingApp.showMain();
    }

    public void showHello() {
        var helloFrame = new JFrame();
        helloFrame.setTitle("Login MeetingManager");
        helloFrame.setSize(400, 1200);
        helloFrame.setLocation(400, 200);
        helloFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        helloFrame.setResizable(false);

        helloFrame.add(new Hello());

        helloFrame.pack();
        helloFrame.setVisible(true);
    }

    public void showMain(){
        var mainFrame = new JFrame();
        mainFrame.setTitle("MeetingManager");
        mainFrame.setSize(1200,900);
        mainFrame.setLocation(400,200);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        BorderLayout borderLayout = new BorderLayout();
        mainFrame.setLayout(borderLayout);
        mainFrame.add(new Navigator(), BorderLayout.CENTER);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
