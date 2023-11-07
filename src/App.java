import pages.hello.Login;
import pages.navigator.Navigator;

import java.awt.*;

public class App {

    //入口主函数
    public static void main(String[] args) {
        //实例化一个showCalculaterFrame类的对象
        App showCal = new App();
        //调用计算器界面的方法
        showCal.initGUI();

    }
    //定义一个计算器界面的方法
    public void initGUI(){
        //实例化一个JFrame类的对象
        javax.swing.JFrame jf = new javax.swing.JFrame();
        //设置窗体的标题属性
        jf.setTitle("MeetingManager");
        //设置窗体的大小属性
        jf.setSize(800,600);
        //设置窗体的位置属性
        jf.setLocation(400,200);
        //设置窗体关闭时退出程序
        jf.setDefaultCloseOperation(3);
        //设置禁止调整窗体的大小
        jf.setResizable(false);

        //设置窗体的布局方式为网格布局
        BorderLayout borderLayout = new BorderLayout();
        jf.setLayout(borderLayout);
        //实例化十九个个JButton对象、一个JTextField对象

        jf.add(new Navigator(), BorderLayout.CENTER);

        jf.pack();
        jf.setVisible(true);
    }

}
