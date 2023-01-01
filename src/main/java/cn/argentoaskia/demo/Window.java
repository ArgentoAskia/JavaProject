package cn.argentoaskia.demo;

import cn.argentoaskia.WindowsManager;
import cn.argentoaskia.demo.beans.Windows2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

// C++客户端（窗口）
// 继承性窗口

// 管理唯一实例的WindowsManager
// 单例windows对象
// 创建窗口对象方法
public class Window extends JFrame {
    private JFrame window;
    private JPanel panel;
    private JButton jButton;
    public static final Window window2 = new Window();

    // 单例模式
    public Window getWindow2(){
        return window2;
    }

    Window(){
        // 初始化组件
        init();
        // 未定义表达式
    }
    public void init(){
        jButton = new JButton();
        jButton.setText("按钮1");
        add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // WindowsManager对象池
                // 窗口管理器器，WindowsManager，做窗口管理，对象管理
                Windows2 windows2 = Windows2.showWindows();
                WindowsManager.registerWindows("Windows2",windows2);
            }
        });
    }

}

