import lombok.var;
import pages.hello.Hello;
import pages.navigator.Navigator;
import state.GlobalState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class App {
    private GlobalState globalState;
    JFrame helloFrame;

    void setListener() {
        setLoginStateListener();
    }

    void setLoginStateListener() {
        globalState.addPropertyChangeListener(evt -> {
            if (!Objects.equals(evt.getPropertyName(), "loginSucceed")) { return; }
            helloFrame.dispose();
            showMain();
        });
    }

    public void showHello() {
        helloFrame = new JFrame();
        helloFrame.setTitle("Login MeetingManager");
        helloFrame.setSize(400, 1200);
        helloFrame.setLocationRelativeTo(null);
        helloFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        helloFrame.setResizable(false);

        helloFrame.add(new Hello());

        helloFrame.pack();
        helloFrame.setVisible(true);
    }

    public void showMain(){
        var mainFrame = new JFrame();

        globalState.setMainFrame(mainFrame);

        mainFrame.setTitle("MeetingManager");
        mainFrame.setSize(1200,900);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        BorderLayout borderLayout = new BorderLayout();
        mainFrame.setLayout(borderLayout);
        mainFrame.add(new Navigator(), BorderLayout.CENTER);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public App() {
        globalState = GlobalState.getInstance();
        setListener();
    }

    public static void main(String[] args) {
        App swingApp = new App();
        swingApp.showHello();
//        swingApp.showMain();
    }

}
