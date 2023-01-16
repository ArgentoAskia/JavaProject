package cn.argentoaskia.demo;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

public class AudioInputStreamDemo {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        InputStream flacInputStream = AudioInputStreamDemo.class.getResourceAsStream("/AudioInputStream/demo.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(flacInputStream);
        // 获取音频文件有多少采样帧
        long frameLength = audioInputStream.getFrameLength();
        // 获取音频文件格式
        AudioFormat format = audioInputStream.getFormat();
        // 获取帧字节大小
        int frameSize = format.getFrameSize();
        long inputStreamSize = frameSize * frameLength;
        System.out.println("音频文件有多少帧：" + frameLength);
        System.out.println("音频文件格式：" + format);
        System.out.println("字节流字节数：" + inputStreamSize);
    }
}
