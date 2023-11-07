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
        jf.setTitle("计算器");
        //设置窗体的大小属性
        jf.setSize(250,350);
        //设置窗体的位置属性
        jf.setLocation(400,200);
        //设置窗体关闭时退出程序
        jf.setDefaultCloseOperation(3);
        //设置禁止调整窗体的大小
        jf.setResizable(false);

        //实例化一个JTextField文本框类的对象
        //javax.swing.JTextField jteName = new javax.swing.JTextField(20);
        //将jteName添加到容器JFrame上
        //jf.add(jteName);
        //实例化一个布局类对象
        java.awt.GridLayout gr = new java.awt.GridLayout(5,4);
        //设置窗体的布局方式为网格布局
        jf.setLayout(gr);
        //实例化十九个个JButton对象、一个JTextField对象
        javax.swing.JButton jb1 = new javax.swing.JButton("1");
        javax.swing.JButton jb2 = new javax.swing.JButton("2");
        javax.swing.JButton jb3 = new javax.swing.JButton("3");
        javax.swing.JButton jb4 = new javax.swing.JButton("4");
        javax.swing.JButton jb5 = new javax.swing.JButton("5");
        javax.swing.JButton jb6 = new javax.swing.JButton("6");
        javax.swing.JButton jb7 = new javax.swing.JButton("7");
        javax.swing.JButton jb8 = new javax.swing.JButton("8");
        javax.swing.JButton jb9 = new javax.swing.JButton("9");
        javax.swing.JButton jb0 = new javax.swing.JButton("0");
        javax.swing.JButton jba = new javax.swing.JButton("+");
        javax.swing.JButton jbb = new javax.swing.JButton("-");
        javax.swing.JButton jbc = new javax.swing.JButton("*");
        javax.swing.JButton jbd = new javax.swing.JButton("/");
        javax.swing.JButton jbe = new javax.swing.JButton("=");
        javax.swing.JButton jbf = new javax.swing.JButton("AC");
        javax.swing.JButton jbg = new javax.swing.JButton("sqrt");
        javax.swing.JButton jbh = new javax.swing.JButton("1/x");
        javax.swing.JButton jbi = new javax.swing.JButton(".");
        javax.swing.JTextField jte = new javax.swing.JTextField();
        //将JButton对象、JTextField对象添加到容器JFrame上
        jf.add(jte);
        jf.add(jbf);
        jf.add(jbg);
        jf.add(jbh);
        jf.add(jb1);
        jf.add(jb2);
        jf.add(jb3);
        jf.add(jba);
        jf.add(jb4);
        jf.add(jb5);
        jf.add(jb6);
        jf.add(jbb);
        jf.add(jb7);
        jf.add(jb8);
        jf.add(jb9);
        jf.add(jbc);
        jf.add(jb0);
        jf.add(jbi);
        jf.add(jbe);
        jf.add(jbd);

        //设置窗体是否可见
        jf.setVisible(true);



    }

}
