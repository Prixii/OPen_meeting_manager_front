import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutDemo {

    public static void main(String[] args) {
        //1.创建Frame对象
        Frame frame = new Frame("这里测试CardLayout");

        //2.创建一个String数组，存储不同卡片的名字
        String[] names = {"1","12","123","1234","12345"};

        //3.创建一个Panel容器p1，并设置其布局管理器为CardLayout,用来存放多张卡片
        CardLayout cardLayout = new CardLayout();
        Panel p1 = new Panel();
        p1.setLayout(cardLayout);

        //4.往p1中存储5个Button按钮，名字从String数组中取
        for (int i = 0; i < 5; i++) {
            p1.add(names[i],new Button(names[i]));
        }

        //5.创建一个Panel容器p2,用来存储5个按钮，完成卡片的切换
        Panel p2 = new Panel();

        //6.创建5个按钮，并给按钮设置监听器
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                switch (command){
                    case "上一张":
                        cardLayout.previous(p1);
                        break;
                    case "下一张":
                        cardLayout.next(p1);
                        break;
                    case "第一张":
                        cardLayout.first(p1);
                        break;
                    case "最后一张":
                        cardLayout.last(p1);
                        break;
                    case "第三张":
                        cardLayout.show(p1,"第三张");
                        break;
                }
            }
        };

        Button b1 = new Button("上一张");
        Button b2 = new Button("下一张");
        Button b3 = new Button("第一张");
        Button b4 = new Button("最后一张");
        Button b5 = new Button("第三张");
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        b3.addActionListener(listener);
        b4.addActionListener(listener);
        b5.addActionListener(listener);

        //7.把5个按钮添加到p2中
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);


        //8.把p1添加到frame的中间区域
        frame.add(p1);


        //9.把p2添加到frame的底部区域
        frame.add(p2,BorderLayout.SOUTH);

        //10设置frame最佳大小并可见
        frame.pack();
        frame.setVisible(true);
    }
}

