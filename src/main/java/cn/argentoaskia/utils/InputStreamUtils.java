package cn.argentoaskia.utils;

import cn.argentoaskia.demo.InputStreamDemo;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InputStreamUtils {

    public static final int BUFFER_1KB_SIZE = 1024;
    public static final int BUFFER_2KB_SIZE = BUFFER_1KB_SIZE * 2;
    public static final int BUFFER_1MB_SIZE = BUFFER_1KB_SIZE * BUFFER_1KB_SIZE;
    public static final int BUFFER_1GB_SIZE = BUFFER_1MB_SIZE * 1024;
    public static final int BUFFER_4KB_SIZE = BUFFER_1KB_SIZE * 4;
    public static final int BUFFER_8KB_SIZE = BUFFER_1KB_SIZE * 8;
    public static byte[] readAllBytes(InputStream inputStream) {
        return null;
    }

    public static InputStream peekBytes(InputStream inputStream, int bufferSize, byte[] data) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            LinkedList<byte[]> list = new LinkedList<>();
            byte[] buffer = new byte[bufferSize];
            int totalBytesCount = 0;
            int reading = 0;
            while ((reading = bufferedInputStream.read(buffer)) > 0) {
                byte[] partOfBytes = Arrays.copyOf(buffer, reading);
                list.add(partOfBytes);
                totalBytesCount += reading;
                Arrays.fill(buffer, (byte) 0);
            }
            // 复制字节到数组并返回
            byte[] totalBytes = new byte[totalBytesCount];
            int cursor = 0;
            for (byte[] bytes : list) {
                System.arraycopy(bytes, 0, totalBytes, cursor, bytes.length);
                cursor += bytes.length;
            }
            System.arraycopy(totalBytes, 0, data, 0, data.length);
            return new ByteArrayInputStream(totalBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return inputStream;
        }
    }

    public static InputStream peekBytes(InputStream inputStream, byte[] data){
        return peekBytes(inputStream, BUFFER_1KB_SIZE, data);
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
