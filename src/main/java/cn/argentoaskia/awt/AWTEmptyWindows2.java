package cn.argentoaskia.awt;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTEmptyWindows2 {
    private Frame windows;

    // 如果窗口组件比较多的时候，可以采用init方法进行分类初始化
    private void initWindows(){
        // 初始化窗口，并指定窗口标题
        windows = new Frame("第一个AWT窗口");
        // 设置窗口位置和大小
        windows.setBounds(200,200,500,500);
        // 设置窗口不可以被调节大小
        windows.setResizable(false);
        windows.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void visibleWindows(){
        windows.setVisible(true);
    }

    public AWTEmptyWindows2(){
        initWindows();
        visibleWindows();
    }

    public static void main(String[] args) {
        new AWTEmptyWindows2();
    }
}
