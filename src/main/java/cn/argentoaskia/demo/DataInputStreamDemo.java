package cn.argentoaskia.demo;

import java.io.*;
import java.util.Arrays;

/**
 * DataInputStream可以读入Java数据类型的数据，包括char、int、long等等，当然需要注意，要想安全读写，需要在写和读的时候保证类型统一！
 * 比如说你在写入数据的时候，调用了writeLong(9999999999999999999999999999L);但是读的时候确实readInt()，这样读出来的数就不是9999999999999999999999999999L。
 * 这个涉及到字节的读取，实际上readInt()的原理是读取32个字节（Java中Int占32字节），然后返回int。
 */
public class DataInputStreamDemo {
    // 可以封装
    public static void main(String[] args) throws IOException {
        InputStream datafile = DataInputStream.class.getResourceAsStream("/DataFile.txt");
        DataInputStream dataInputStream = new DataInputStream(datafile);
        byte[] bytes = new byte[12];
        int read = dataInputStream.read(bytes);
        boolean b = dataInputStream.readBoolean();
        byte b1 = dataInputStream.readByte();
        byte[] bytes1 = new byte[11];
        // read()和readFully()的区别是一个会阻塞等待有字节读入就ok。而readFully以读满参数的数组为准。
        // 在网络传输中的断断续续传输过程中，这两个方法的区别就比较明显。
        dataInputStream.readFully(bytes1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            char c = dataInputStream.readChar();
            stringBuilder.append(c);
        }
        double v = dataInputStream.readDouble();
        int i = dataInputStream.readInt();
        long l = dataInputStream.readLong();
        String s = dataInputStream.readUTF();

        System.out.println(read);
        System.out.println(Arrays.toString(bytes) + "=" + new String(bytes));
        System.out.println(b);
        System.out.println(b1);
        System.out.println(Arrays.toString(bytes1) + "=" + new String(bytes1));
        System.out.println(stringBuilder.toString());
        System.out.println(v);
        System.out.println(i);
        System.out.println(l);
        System.out.println(s);

        datafile.close();
        dataInputStream.close();
    }
}
