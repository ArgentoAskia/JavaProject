package cn.argentoaskia.awt.widgets.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonDemo {
    private Frame frame;
    private Panel northPanel;
    private Panel southPanel;
    private JSplitPane centerPanel;
    private Panel westPanel;
    private Panel eastPanel;



    // northPanel
    private Label titleLabel;

    // westPanel
    private Label labelActionCommand;
    private Label labelBackground;
    private Label labelBoundsX;
    private Label labelBoundsY;
    private Label labelBoundsWidth;
    private Label labelBoundsHeight;

    // eastPanel
    private Button sampleButton1;
    private Button sampleButton2;
    private Button sampleButton3;
    private Button sampleButton4;
    private Button sampleButton5;
    private Button sampleButton6;
    private Button sampleButton7;
    private Button sampleButton8;
    private Button sampleButton9;
    private Button sampleButton10;
    private Button sampleButton11;
    private Button sampleButton12;
    private Button sampleButton13;
    private Button sampleButton14;
    private Button sampleButton15;

    // south panel
    private Button updateButton;

    public ButtonDemo(){
        init();
        events();
    }

    private void events(){
        updateButtonEvent();
    }
    private void updateButtonEvent(){
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sampleButton1.setLabel("123");
                sampleButton1.setPreferredSize(new Dimension(500, 600));
                sampleButton1.invalidate();
            }
        });
    }

    public void init(){
        initWidgets();
        initNorthLabel();
        initEastButton();
        initSouthButton();
        initWindows();
    }

    public void initWidgets(){
        frame = new Frame("按钮属性设置窗口");

        northPanel = new Panel();
        southPanel = new Panel();
        eastPanel = new Panel();
        westPanel = new Panel();
        centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPanel,eastPanel);
        centerPanel.setOneTouchExpandable(false);
        centerPanel.setDividerLocation(500);

        // north
        titleLabel = new Label("AWT Button Widget Attributes Show");

        // west
        labelActionCommand = new Label();
        labelBackground = new Label();
        labelBoundsHeight = new Label();
        labelBoundsWidth = new Label();
        labelBoundsX = new Label();
        labelBoundsY = new Label();

        // east
        sampleButton1 = new Button("button1");
        sampleButton2 = new Button("button2");
        sampleButton3 = new Button("button3");
        sampleButton4 = new Button("button4");
        sampleButton5 = new Button("button5");
        sampleButton6 = new Button("button6");
        sampleButton7 = new Button("button7");
        sampleButton8 = new Button("button8");
        sampleButton9 = new Button("button9");
        sampleButton10 = new Button("button10");
        sampleButton11 = new Button("button11");
        sampleButton12 = new Button("button12");
        sampleButton13 = new Button("button13");
        sampleButton14 = new Button("button14");
        sampleButton15 = new Button("button15");

        // south
        updateButton = new Button("update");
    }
    public void initNorthLabel(){
        titleLabel.setAlignment(Label.CENTER);
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        northPanel.add(titleLabel);
    }
    public void initEastButton(){
        GridLayout gridLayout = new GridLayout(5, 3, 25, 40);
        eastPanel.setLayout(gridLayout);
        eastPanel.getPreferredSize().width = 500;
        eastPanel.add(sampleButton1);
        eastPanel.add(sampleButton2);
        eastPanel.add(sampleButton3);
        eastPanel.add(sampleButton4);
        eastPanel.add(sampleButton5);
        eastPanel.add(sampleButton6);
        eastPanel.add(sampleButton7);
        eastPanel.add(sampleButton8);
        eastPanel.add(sampleButton9);
        eastPanel.add(sampleButton10);
        eastPanel.add(sampleButton11);
        eastPanel.add(sampleButton12);
        eastPanel.add(sampleButton13);
        eastPanel.add(sampleButton14);
        eastPanel.add(sampleButton15);
    }

    public void initSouthButton(){
        southPanel.add(updateButton);
    }

    private void initWindows(){
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);
    }

    private void bindEvents(){

    }

    public static void main(String[] args) {
        ButtonDemo buttonDemo = new ButtonDemo();
        buttonDemo.frame.setBounds(0, 100 ,800, 500);
        buttonDemo.frame.setVisible(true);

    }
    




    public void test(){
        Button button = new Button();
//        button.setActionCommand();
//        button.setBackground();
//        button.setBounds();
//        button.setCursor();
//        button.setDropTarget();
//        button.setEnabled();
//        button.setFocusable();
//        button.setFocusTraversalKeys();
//        button.setFocusTraversalKeysEnabled();
//        button.setFont();
//        button.setForeground();
//        button.setIgnoreRepaint();
//        button.setLabel();
//        button.setLocale();
//        button.setLocation();
//        button.setMaximumSize();
//        button.setMinimumSize();
//        button.setName();
//        button.setPreferredSize();
//        button.setSize();
//        button.setVisible();
//
//        button.setComponentOrientation();
//
//        button.isBackgroundSet();
//        button.isCursorSet();
//        button.isDisplayable();
//        button.isDoubleBuffered();
//        button.isEnabled();
//        button.isFocusable();
//        button.isFocusCycleRoot();
//        button.isFocusOwner();
//        button.isFontSet();
//        button.isForegroundSet();
//        button.isLightweight();
//        button.isMaximumSizeSet();
//        button.isMinimumSizeSet();
//        button.isOpaque();
//        button.isPreferredSizeSet();
//        button.isShowing();
//        button.isValid();
//        button.isVisible();
//
//        button.getAccessibleContext();
//        button.getActionCommand();
//        button.getActionListeners();
//        button.getAlignmentX();
//        button.getAlignmentY();
//        button.getBackground();
//        button.getBaseline();
//        button.getBaselineResizeBehavior();
//        button.getBounds();

    }
}
