package cn.argentoaskia.utils;

import cn.argentoaskia.demo.InputStreamDemo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InputStreamUtils {
    public static byte[] readAllBytes(InputStream inputStream){
        return null;
    }
    public static InputStream peekBytes(InputStream inputStream, int bufferSize, byte[] data) throws IOException {

        try(PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, data.length)){
            LinkedList<byte[]> list = new LinkedList<>();
            byte[] buffer = new byte[bufferSize];
            int totalBytesCount = 0;
            int reading = 0;
            // 读入所有字节
            // 最后一次读取会清空掉pos，指针为0，导致显示Push back buffer is full异常，代表读满了，没法会回退
            while((reading = pushbackInputStream.read(buffer)) > 0){
                byte[] bytes = Arrays.copyOf(buffer, reading);
                list.add(bytes);
                totalBytesCount += reading;
                Arrays.fill(buffer, (byte) 0);
                // 回退字节
                pushbackInputStream.unread(bytes);
                // 跳过已经读取了的字节
                pushbackInputStream.skip(totalBytesCount);
            }
            // 复制字节到数组并返回
            byte[] totalBytes = new byte[totalBytesCount];
            int cursor = 0;
            for (byte[] bytes : list) {
                System.arraycopy(bytes, 0, totalBytes, cursor, bytes.length);
                cursor += bytes.length;
            }
            System.arraycopy(totalBytes,0, data, 0, data.length);
            return new ByteArrayInputStream(totalBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return inputStream;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = InputStreamDemo.class.getResourceAsStream("/data.txt");
        byte[] bytes1 = new byte[resourceAsStream.available()];
        InputStream bytes = peekBytes(resourceAsStream, 1024, bytes1);
        int read = bytes.read();
        System.out.println(read);
        System.out.println(Arrays.toString(bytes1));
    }
}
