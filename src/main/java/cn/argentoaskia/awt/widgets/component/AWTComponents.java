package cn.argentoaskia.awt.widgets.component;

import com.sun.deploy.uitoolkit.impl.awt.AWTAnimationPanel2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * AWT全组件Demo演示，共9大组件（太少了！！！）
 *
 * @author Askia
 * @version 1.0
 * @since 1.0
 */
public class AWTComponents extends Frame {
    // 组件容器
    private Panel buttonsPanel;
    private Panel checkBoxPanel;
    private Panel choicesPanel;
    private Panel labelsPanel;
    private Panel listPanel;
    private Panel scrollbarsPanel;
    private Panel textAreaPanel;
    private Panel textFieldPanel;
    // 第一步，创建所有组件
    private void createPanels(){
        buttonsPanel = new Panel();
        checkBoxPanel = new Panel();
        choicesPanel = new Panel();
        labelsPanel = new Panel();
        listPanel = new Panel();
        scrollbarsPanel = new Panel();
        textAreaPanel = new Panel();
        textFieldPanel = new Panel();
    }
    // 第二步，初始化所有组件
    private void initPanel(){
        createPanels();
        buttonsPanel.list();
        checkBoxPanel.list();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        choicesPanel.list();
        labelsPanel.list();
        listPanel.list();

        scrollbarsPanel.list();
        textAreaPanel.list();
        textFieldPanel.list();
    }
    // 第三步，布局所有组件
    private void layoutPanel(){
        initPanel();
        buttonsPanel.setBounds(10,30, 300, 200);
        checkBoxPanel.setBounds(630, 30, 300, 200);

        choicesPanel.setBounds(10, 240, 300, 200);
        labelsPanel.setBounds(320, 240, 300, 200);
        listPanel.setBounds(630, 240, 300, 200);

        scrollbarsPanel.setBounds(10, 450, 300, 400);
        textAreaPanel.setBounds(320, 450, 300, 400);
        textFieldPanel.setBounds(630, 450, 300, 400);
    }

    // 9大组件库：button、canvas、checkbox、choice、label、list、scrollbar、textarea、textfield
    private Random random;
    private Button[] buttons;
    private Canvas canvas;
    private Timer timer;
    private CheckboxGroup checkboxGroup;
    private Checkbox[] checkbox;
    private Choice[] choices;
    private Label[] labels;
    private List[] lists;
    private Scrollbar[] scrollbars;
    private TextArea textArea;
    private TextField[] textFields;

    // 第四步 创建用户组件库
    private void createComponents(){
        buttons = new Button[25];
        canvas = new AWTAnimationPanel2();
        checkboxGroup = new CheckboxGroup();
        checkbox = new Checkbox[24];
        choices = new Choice[9 + 3 + 3];
        labels = new Label[9 + 3 + 3];
        lists = new List[4];
        scrollbars = new Scrollbar[9];
        textArea = new TextArea();
        textFields = new TextField[9];
    }
    // 第五步 初始化组件
    private void initComponents(){
        createComponents();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("按钮" + i);
            buttons[i].setPreferredSize(new Dimension(50, 30));
        }
        random = new Random();
        for (int i = 0; i < checkbox.length; i++) {
            checkbox[i] = new Checkbox("选择框" + i, random.nextBoolean());
            checkbox[i].setCheckboxGroup(checkboxGroup);
        }
        for (int i = 0; i < choices.length; i++) {
            choices[i] = new Choice();
            choices[i].setPreferredSize(new Dimension(90, 30));
            choices[i].setName("下拉列表框" + i);
            choices[i].add("选项0");
            choices[i].add("选项1");
            choices[i].add("选项2");
            choices[i].add("选项3");
            choices[i].add("选项4");
            choices[i].add("选项5");
            choices[i].add("选项6");
            choices[i].add("选项7");
            choices[i].add("选项8");
            choices[i].add("选项9");
        }
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label();
            labels[i].setText("标签" + i);
            labels[i].setPreferredSize(new Dimension(90, 30));
        }
        for (int i = 0; i < scrollbars.length; i++) {
            scrollbars[i] = new Scrollbar();
            scrollbars[i].setMaximum(100);
            scrollbars[i].setMinimum(0);
            scrollbars[i].setUnitIncrement(1);
            if (i <= 4){
                scrollbars[i].setOrientation(Scrollbar.HORIZONTAL);
                scrollbars[i].setPreferredSize(new Dimension(300, 20));
            }else{
                scrollbars[i].setOrientation(Scrollbar.VERTICAL);
                scrollbars[i].setPreferredSize(new Dimension(20, 120));
            }
        }
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new TextField();
            textFields[i].setColumns(30);
            textFields[i].setText("ABCDEFGHIJK");
        }
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new List();
            lists[i].setMinimumSize(new Dimension(200, 300));
            lists[i].add("选项0");
            lists[i].add("选项1");
            lists[i].add("选项2");
            lists[i].add("选项3");
            lists[i].add("选项4");
            lists[i].add("选项5");
            lists[i].add("选项6");
            lists[i].add("选项7");
            lists[i].add("选项8");
            lists[i].add("选项9");
            lists[i].add("选项10");
            lists[i].add("选项11");
            lists[i].add("选项12");
            lists[i].add("选项13");
            lists[i].add("选项14");
            lists[i].add("选项15");
        }
        ((AWTAnimationPanel2)canvas).startAnimation();
        textArea.setColumns(20);
        textArea.setPreferredSize(new Dimension(300, 300));
    }

    // 第六步 布局组件
    private void layoutComponents(){
        initComponents();
        for (int i = 0; i < buttons.length; i++) {
            buttonsPanel.add(buttons[i]);
        }
        for (int i = 0; i < checkbox.length; i++) {
            checkBoxPanel.add(checkbox[i]);
        }
        for (int i = 0; i < choices.length; i++) {
            choicesPanel.add(choices[i]);
        }
        for (int i = 0; i < scrollbars.length; i++) {
            scrollbarsPanel.add(scrollbars[i]);
        }
        for (int i = 0; i < textFields.length; i++) {
            textFieldPanel.add(textFields[i]);
        }
        for (int i = 0; i < lists.length; i++) {
            listPanel.add(lists[i]);
        }
        for (int i = 0; i < labels.length; i++) {
            labelsPanel.add(labels[i]);
        }
        textAreaPanel.add(textArea);
        canvas.setBounds(320, 30, 300, 200);
    }

    // 第七步 绘画组件
    private void drawComponents(){
        setBounds(100, 100 ,950, 900);
        setLayout(null);
        add(buttonsPanel);
        add(canvas);
        add(checkBoxPanel);
        add(choicesPanel);
        add(labelsPanel);
        add(listPanel);
        add(scrollbarsPanel);
        add(textAreaPanel);
        add(textFieldPanel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((AWTAnimationPanel2)canvas).stopAnimation();
                timer.cancel();
                System.exit(0);
            }
        });
        setTitle("AWT组件一览, 共有9种组件, 一共9种组件：按钮、画布、选择框、下拉列表框、标签、列表框、文本编辑框、文本编辑域、滑块条");
        setResizable(false);
        setVisible(true);
    }

    public AWTComponents(){
        layoutPanel();
        layoutComponents();
        drawComponents();
        runAnimation();
    }

    // 运行动画
    private void runAnimation(){
        // 定时器，让Canvas动画动起来
        timer = new Timer();
        MyCanvasController myCanvasController = new MyCanvasController((AWTAnimationPanel2) canvas);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                myCanvasController.run();
            }
        };
        int run = 300 + random.nextInt(700);
        System.out.println(run);
        timer.schedule(timerTask, 100, run);
    }


    public static void main(String[] args) {
        new AWTComponents();
    }
}



// canvas动画控制器
class MyCanvasController implements Runnable{
    private AWTAnimationPanel2 awtAnimationPanel2;

    public MyCanvasController(AWTAnimationPanel2 awtAnimationPanel2){
        this.awtAnimationPanel2 = awtAnimationPanel2;
    }
    @Override
    public void run() {
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
    }
}
