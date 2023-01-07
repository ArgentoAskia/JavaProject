package cn.argentoaskia.awt;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AWTEmptyWindows1 extends Frame {
    public AWTEmptyWindows1(){
        // 设置窗口显示位置和大小
        setBounds(200, 200, 500, 500);
        // 设置标题
        setTitle("第一个AWT窗口");
        // 默认情况下AWT窗口关闭按钮没反应，需要自己定义关闭按钮的行为，使用下面的语句可以实现关闭功能，实际上是一个事件监听，后面会介绍
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // 显示窗口，窗口可视
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTEmptyWindows1();
    }
}
