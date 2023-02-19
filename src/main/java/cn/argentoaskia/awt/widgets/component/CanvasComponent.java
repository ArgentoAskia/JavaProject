package cn.argentoaskia.awt.widgets.component;

import cn.argentoaskia.awt.AWTEmptyWindows1;
import com.sun.deploy.uitoolkit.impl.awt.AWTAnimationPanel2;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * AWTAnimationPanel2,Canvas动画演示
 */
public class CanvasComponent extends Frame {
    private AWTAnimationPanel2 awtAnimationPanel2 = new AWTAnimationPanel2();
    public CanvasComponent(){
        setLayout(null);
        setResizable(false);
        awtAnimationPanel2.startAnimation();
        awtAnimationPanel2.setBounds(100, 30, 300, 300);
        add(awtAnimationPanel2);
        Button button = new Button("添加进度");
        button.setBounds(30,30,100,30);

        add(button);
        add(awtAnimationPanel2);
        // 设置窗口显示位置和大小
        setBounds(350, 300, 500, 500);
        // 设置标题
        setTitle("Canvas-AWTAnimationPanel2演示");
        // 默认情况下AWT窗口关闭按钮没反应，需要自己定义关闭按钮的行为，使用下面的语句可以实现关闭功能，实际上是一个事件监听，后面会介绍
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // 显示窗口，窗口可视
        setVisible(true);
        button.addActionListener(e -> {
            float progressValue = awtAnimationPanel2.getProgressValue();
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String progressValueStr = decimalFormat.format(progressValue);
            if (Float.valueOf(progressValueStr).equals(1.0f)){
                awtAnimationPanel2.setProgressValue(0.0f);
            } else {
                progressValue = progressValue + 0.1f;
                String format = decimalFormat.format(progressValue);
                Float aFloat = Float.valueOf(format);
                System.out.println(aFloat);
                awtAnimationPanel2.setProgressValue(aFloat);
            }
        });
    }

    public static void main(String[] args) {
        new CanvasComponent();
    }
}
