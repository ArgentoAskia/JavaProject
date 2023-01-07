package cn.argentoaskia.awt.layout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * 卡式布局演示，包括如何定位卡片，如何动态增减卡片。
 * @author Askia
 */
public class CardLayoutDemo {
    private static void repaint(Frame frame){
        int[] randomIndex = {-1, 1};
        int width = frame.getWidth();
        int height = frame.getHeight();
        int i = width % 2;
        width = width + randomIndex[i];
        frame.setSize(width, height);
    }
    private static int currentCardIndex = 1;
    private static Map<String, Label> labelMap = new HashMap<>();
    private static List<Integer> seqList = new LinkedList<>();


    public static void main(String[] args) {
        // 默认BorderLayout
        Frame frame = new Frame("CardLayout布局演示");
        frame.setSize(1000, 600);

        // 中间Panel
        Panel panel = new Panel();
        CardLayout cardLayout = new CardLayout();
        panel.setLayout(cardLayout);

        Label label1 = new Label("第1张卡片");
        label1.setAlignment(Label.CENTER);
        label1.setBackground(Color.green);
        Label label2 = new Label("第2张卡片");
        label2.setAlignment(Label.CENTER);
        label2.setBackground(Color.WHITE);
        Label label3 = new Label("第3张卡片");
        label3.setAlignment(Label.CENTER);
        label3.setBackground(Color.BLUE);
        labelMap.put("1", label1);
        labelMap.put("2", label2);
        labelMap.put("3", label3);
        panel.add("1", label1);
        panel.add("2", label2);
        panel.add("3", label3);

        // 顶部Panel
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 20, 0);
        Panel controlPanel = new Panel();
        controlPanel.setLayout(flowLayout);
        Button preBtn = new Button("上一张");
        Button nextBtn = new Button("下一张");
        Button firstBtn = new Button("第一张");
        Button lastBtn = new Button("最后一张");

        Panel choicePanel = new Panel();
        TextField field = new TextField(5);
        Button b5 = new Button("跳转到指定张");
        choicePanel.add(field);
        choicePanel.add(b5);

        controlPanel.add(preBtn);
        controlPanel.add(nextBtn);
        controlPanel.add(lastBtn);
        controlPanel.add(firstBtn);
        controlPanel.add(choicePanel);

        // 底部Panel
        Panel panel1 = new Panel();
        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.CENTER, 30, 0);
        Label label = new Label("当前有" + labelMap.size() + "张卡片");
        Button button = new Button("添加一张卡片");
        Button button1 = new Button("删除当前卡片");
        panel1.setLayout(flowLayout1);
        panel1.add(label);
        panel1.add(button);
        panel1.add(button1);

        // 扩展卡式布局
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                switch (command){
                    case "上一张":
                        cardLayout.previous(panel);
                        if (currentCardIndex > 1) {
                            currentCardIndex %= labelMap.size();
                            currentCardIndex--;
                        }
                        break;
                    case "下一张":
                        cardLayout.next(panel);
                        currentCardIndex %= labelMap.size();
                        currentCardIndex++;
                        break;
                    case "第一张":
                        cardLayout.first(panel);
                        currentCardIndex = 1;
                        break;
                    case "最后一张":
                        cardLayout.last(panel);
                        currentCardIndex = labelMap.size();
                        break;
                    default:
                        String text = field.getText();
                        int i = Integer.parseInt(text);
                        cardLayout.show(panel,i + "");
                        currentCardIndex = i;
                        break;
                }
            }
        };
        preBtn.addActionListener(actionListener);
        nextBtn.addActionListener(actionListener);
        lastBtn.addActionListener(actionListener);
        firstBtn.addActionListener(actionListener);
        b5.addActionListener(actionListener);

        ActionListener add = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = seqList.size() > 0? seqList.get(0):labelMap.size() + 1;
                Label label2 = new Label("第"+ size + "张卡片");
                labelMap.put("" + size, label2);
                label2.setAlignment(Label.CENTER);
                label2.setBackground(Color.getHSBColor((float) Math.random(), (float) Math.random(), (float) Math.random()));
                panel.add(size + "", label2);

                label.setText("当前有" + labelMap.size() + "张卡片");
                // 更新布局
                cardLayout.invalidateLayout(panel);
//                cardLayout.layoutContainer(panel);
            }
        };
        button.addActionListener(add);

        ActionListener delete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(currentCardIndex - 1);
                labelMap.remove("" + currentCardIndex);
                seqList.add(currentCardIndex);
                label.setText("当前有" + (labelMap.size()) + "张卡片");
            }
        };
        button1.addActionListener(delete);


        frame.add(panel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(800, 600);
        frame.setVisible(true);

    }
}
