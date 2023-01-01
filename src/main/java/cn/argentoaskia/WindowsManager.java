package cn.argentoaskia;

import javax.swing.*;
import java.util.HashMap;

public class WindowsManager {
    // 邻接矩阵
    // X X X X X

    private static HashMap<String, JFrame> manager = new HashMap<>();
    public static JFrame getFrame(String frameName){

        JFrame jFrame = manager.get(frameName);
        return jFrame;
    }

    public static void registerWindows(String frameName, JFrame jFrame){
        manager.put(frameName, jFrame);
    }
}
