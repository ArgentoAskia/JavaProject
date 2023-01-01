package cn.argentoaskia.demo;

import cn.argentoaskia.demo.beans.Windows2;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        // 当我点击按钮，显示我们的windows2
        // MVC、Button
        Window window = new Window();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setBounds(100, 100 , 200, 300);
        window.setVisible(true);
    }
}
