package cn.argentoaskia.awt.layout;


import java.awt.*;
import java.awt.event.*;

/**
 * 这是一个{@code FlowLayout}的Demo,旨在介绍这个布局的作用。<br>
 * {@code FlowLayout}也叫流式布局，所有FlowLayout里面的组件都将会被横排，直到当前窗口可视层无法再融入一个组件为止。<br>
 * <p>
 *     在{@code FlowLayout}中值得关注的参数有三个：
 *     <ul>
 *         <li>align(对齐方式)：{@link FlowLayout#setAlignment(int)}</li>
 *         <li>hGap(水平间距)：{@link FlowLayout#setHgap(int)}</li>
 *         <li>vGap(垂直间距)：{@link FlowLayout#setVgap(int)}</li>
 *     </ul>
 *     FlowLayout构造器内直接配置这三个参数：
 *     <ul>
 *         <li>{@code public FlowLayout(int align, int hgap, int vgap)}</li>
 *         <li>{@code public FlowLayout(int align)}</li>
 *         <li>{@code public FlowLayout()}</li>
 *     </ul>
 *
 */
public class FlowLayoutDemo {

    public static void main(String[] args) {
        // Frame默认BorderLayout布局
        Frame frame = new Frame("FlowLayout布局演示");
        // 画出上下面板
        // Panel默认FlowLayout布局
        Panel buttonPanel = new Panel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10 ,5);
        Panel controlPanel = new Panel(flowLayout);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        for (int i = 0; i < 200; i++) {
            Button button1 = new Button("button" + i);
            buttonPanel.add(button1);
        }


        // 创建分组
        Panel hGapPanel = new Panel();
        Panel vGapPanel = new Panel();
        Panel alignPanel = new Panel();
        Panel alignOnBaseLinePanel = new Panel();

        // 创建组件
        Label hGapLabel = new Label("水平间距(hGap)");
        Label vGapLabel = new Label("垂直间距(vGap)");
        Label alignLabel = new Label("对齐方式(align)");


        TextField hGapTextField = new TextField(5);
        TextField vGapTextField = new TextField(5);
        Choice alignChoice = new Choice();
        alignChoice.add("CENTER");
        alignChoice.add("LEADING");
        alignChoice.add("LEFT");
        alignChoice.add("RIGHT");
        alignChoice.add("TRAILING");
        Checkbox checkbox = new Checkbox("对齐到基线(alignOnBaseLine)");

        hGapPanel.add(hGapLabel);
        hGapPanel.add(hGapTextField);
        vGapPanel.add(vGapLabel);
        vGapPanel.add(vGapTextField);
        alignPanel.add(alignLabel);
        alignPanel.add(alignChoice);
        alignOnBaseLinePanel.add(checkbox);

        Button button = new Button("update");
        controlPanel.add(alignPanel);
        controlPanel.add(hGapPanel);
        controlPanel.add(vGapPanel);
        controlPanel.add(alignOnBaseLinePanel);
        controlPanel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dynamic button
//                buttonPanel.add(new Button());
//                buttonPanel.validate();


                String hGapText = hGapTextField.getText();
                String vGapText = vGapTextField.getText();
                int hGap = 0;
                int vGap = 0;
                // 获取布局管理器
                FlowLayout layout = (FlowLayout) buttonPanel.getLayout();
                if (hGapText.equals("")){
                    hGap = layout.getHgap();
                }else{
                    hGap = Integer.parseInt(hGapText);
                }
                if (vGapText.equals("")){
                    vGap = layout.getVgap();
                }else{
                    vGap = Integer.parseInt(vGapText);
                }
                boolean state = checkbox.getState();
                String align = alignChoice.getSelectedItem();
                FlowLayoutAlign flowLayoutAlign = Enum.valueOf(FlowLayoutAlign.class, align);
                int align1 = flowLayoutAlign.getAlign();
                layout.setAlignment(align1);
                layout.setHgap(hGap);
                layout.setVgap(vGap);
                layout.setAlignOnBaseline(state);

                // 改变窗口大小，触发重绘，更新布局
                int width = frame.getWidth();
                int height = frame.getHeight();
                frame.setSize(width + 1, height);
            }
        });
        // 初始化窗口大小
        frame.setSize(1000, 500);

        // 关闭窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);


    }

}
enum FlowLayoutAlign{
    CENTER(FlowLayout.CENTER),LEADING(FlowLayout.LEADING),LEFT(FlowLayout.LEFT),
    RIGHT(FlowLayout.RIGHT),TRAILING(FlowLayout.TRAILING);

    private int align;

    FlowLayoutAlign(int val) {
        align = val;
    }

    public int getAlign() {
        return align;
    }
}

