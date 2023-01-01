package cn.argentoaskia.demo.beans;

import cn.argentoaskia.WindowsManager;

import javax.swing.*;

public class Windows2 extends JFrame {
    private String name;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    // 显示窗口
    public static Windows2 showWindows(){
        Windows2 windows2 = new Windows2();
        windows2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        windows2.setBounds(100, 100 , 200, 300);
        windows2.setVisible(true);
        // MVC --> MVVM
        JFrame window2 = WindowsManager.getFrame("window2");
        return windows2;
    }
}
