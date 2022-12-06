package cn.argentoaskia.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class ByteArrayInputStreamDemo {
    public static void main(String[] args) throws IOException {
        // 1.先创建一个字节数组，模仿字节数据
        byte[] bytes = "生活就像海洋，只有意志坚强的人才能到达彼岸".getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        int read = byteArrayInputStream.read();
        System.out.println(read);
        int read1 = byteArrayInputStream.read();
        int read2 = byteArrayInputStream.read();
        System.out.println(read);
        System.out.println(read1);
        System.out.println(read2);
        byteArrayInputStream.close();
    }
}
