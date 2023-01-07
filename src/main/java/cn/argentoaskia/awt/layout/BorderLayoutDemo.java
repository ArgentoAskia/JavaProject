package cn.argentoaskia.awt.layout;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorderLayoutDemo {
    private static void repaint(Frame frame){
        int[] randomIndex = {-1, 1};
        int width = frame.getWidth();
        int height = frame.getHeight();
        int i = width % 2;
        width = width + randomIndex[i];
        frame.setSize(width, height);
    }
    public static void main(String[] args) {
        // frame默认BorderLayout
        Frame frame = new Frame("BorderLayout布局演示");

        // Panel设置borderLayout布局
        Panel panel = new Panel();
        BorderLayout borderLayout = new BorderLayout(0, 0);
        panel.setLayout(borderLayout);

        // 组件
        Button buttonNorth = new Button("North Button");
        Button buttonSouth = new Button("South Button");
        Button buttonWest = new Button("West Button");
        Button buttonEast = new Button("East Button");
        Button buttonCenter = new Button("Center Button");

        panel.add(buttonNorth, BorderLayout.NORTH);
        panel.add(buttonSouth, BorderLayout.SOUTH);
        panel.add(buttonEast, BorderLayout.EAST);
        panel.add(buttonWest, BorderLayout.WEST);
        panel.add(buttonCenter, BorderLayout.CENTER);

        // 设置窗口的中间部分内容
        frame.add(panel, BorderLayout.CENTER);

        // Panel默认FlowLayout布局
        Panel controlPanel = new Panel();


        Checkbox showLeftButtonCheckBox = new Checkbox("显示左边按钮");
        Checkbox showRightButtonCheckBox = new Checkbox("显示右边按钮");
        Checkbox showUpButtonCheckBox = new Checkbox("显示顶部按钮");
        Checkbox showDownButtonCheckBox = new Checkbox("显示底部按钮");
        showLeftButtonCheckBox.setState(true);
        showRightButtonCheckBox.setState(true);
        showUpButtonCheckBox.setState(true);
        showDownButtonCheckBox.setState(true);
        controlPanel.add(showDownButtonCheckBox);
        controlPanel.add(showUpButtonCheckBox);
        controlPanel.add(showRightButtonCheckBox);
        controlPanel.add(showLeftButtonCheckBox);


        Panel hGapPanel = new Panel();
        Label hGapLabel = new Label("水平间距(hGap)");
        TextField hGapTextField = new TextField(5);
        hGapTextField.setText("0");
        hGapPanel.add(hGapLabel);
        hGapPanel.add(hGapTextField);

        Panel vGapPanel = new Panel();
        Label vGapLabel = new Label("垂直间距(vGap)");
        TextField vGapTextField = new TextField(5);
        vGapTextField.setText("0");
        vGapPanel.add(vGapLabel);
        vGapPanel.add(vGapTextField);

        Button button = new Button("update");
        controlPanel.add(hGapPanel);
        controlPanel.add(vGapPanel);
        controlPanel.add(button);

        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setSize(1000, 600);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hGapStr = hGapTextField.getText();
                String vGapStr = vGapTextField.getText();
                int hGap = Integer.parseInt(hGapStr);
                int vGap = Integer.parseInt(vGapStr);
                BorderLayout layout = (BorderLayout) panel.getLayout();
                layout.setHgap(hGap);
                layout.setVgap(vGap);
                // 显示隐藏SOUTH按钮
                if (!showDownButtonCheckBox.getState()){
                    layout.removeLayoutComponent(buttonSouth);
                    // TODO: 2022/10/13 ！！！！！
                    // 布局管理器并不会决定组件是否还能显示在窗口上，
                    // 他只决定布局位置，因此必须手动移除掉组件。
                    // 否则会发现布局虽然已经变化了，但是组件还存在。
                    panel.remove(buttonSouth);
                }else{
                    layout.addLayoutComponent(buttonSouth, BorderLayout.SOUTH);
                    panel.add(buttonSouth, BorderLayout.SOUTH);
                }
                // 显示隐藏NORTH按钮
                if (!showUpButtonCheckBox.getState()){
                    panel.remove(buttonNorth);
                    layout.removeLayoutComponent(buttonNorth);
                }else{
                    layout.addLayoutComponent(buttonNorth, BorderLayout.NORTH);
                    panel.add(buttonNorth, BorderLayout.NORTH);
                }
                // 显示隐藏WEST按钮
                if (!showLeftButtonCheckBox.getState()){
                    layout.removeLayoutComponent(buttonWest);
                    panel.remove(buttonWest);
                }else{
                    layout.addLayoutComponent(buttonWest, BorderLayout.WEST);
                    panel.add(buttonWest, BorderLayout.WEST);
                }
                // 显示隐藏EAST按钮
                if (!showRightButtonCheckBox.getState()){
                    layout.removeLayoutComponent(buttonEast);
                    panel.remove(buttonEast);
                }else{
                    layout.addLayoutComponent(buttonEast, BorderLayout.EAST);
                    panel.add(buttonEast, BorderLayout.EAST);
                }
                // 只会更新组件，不会更新布局
//                panel.invalidate();
                repaint(frame);
            }
        });

    }
}
