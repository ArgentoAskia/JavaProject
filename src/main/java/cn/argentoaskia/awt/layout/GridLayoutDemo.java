package cn.argentoaskia.awt.layout;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridLayoutDemo {
    public static void main(String[] args) {
        Frame frame = new Frame("计算器");
        // frame上部分
        Panel pl = new Panel();
        pl.add(new TextField(30));

        frame.add(pl, BorderLayout.NORTH);
        Panel p2 = new Panel();
        // 创建GridLayout,3行，5列，单元格与单元格之间水平间距4，垂直间距4
        GridLayout gridLayout = new GridLayout(3, 5, 4,4);
        p2.setLayout(gridLayout);

        // 添加0-9、+、-、*、/、.到panel中
        // 添加过程先排满一行，再换行
        for (int i = 0; i < 10; i++) {
            p2.add(new Button(String.valueOf(i)));
        }
        Button button = new Button("+");
        p2.add(button);
        p2.add(new Button("-"));
        p2.add(new Button("*"));
        p2.add(new Button("/"));
        p2.add(new Button("."));
        frame.add(p2, BorderLayout.CENTER);

        // 设置最佳大小
        frame.pack();
        // 设置Frame可见
        frame.setVisible(true);
        // 设置窗口大小不可改变
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
