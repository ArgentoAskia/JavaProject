package cn.argentoaskia.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * InputStream的基本能力介绍.
 * 包括：
 *      - public abstract int read():       // 读入一个字节，并返回，如果文件尾则返回-1
 *      - public int read(byte[] b);
 *      - public int read(byte[] b, int off, int len);
 *      - public byte[] readAllBytes();
 *      - public int transferTo(OutputStream out);
 *      - public long skip(long n);
 */
public class InputStreamDemo {
    public static void main(String[] args) throws IOException {
        InputStream data = InputStreamDemo.class.getResourceAsStream("/data.txt");
        // 第一个字节
        int read = data.read();
        System.out.println("data.read()：" + read);
        System.out.println("=================================================");

        // 读入指定字节,返回读入的字符数量
        byte[] bytes = new byte[3];
        int read1 = data.read(bytes);
        System.out.println("读入了" + read1 + "个字节");
        System.out.println(Arrays.toString(bytes));
        System.out.println("=================================================");
        System.out.println("跳过3个字节...");
        long skip = data.skip(3);
        System.out.println("实际上跳过了" + skip + "个字节");
        System.out.println("=================================================");
        // 判断流是否有标记和重置的能力
        boolean b = data.markSupported();
        if (b){
            System.out.println("该流拥有标记和重置能力");
            // 标记当前位置，并指定自从标志之后能读取多少个字节（readlimit）,
            // 一旦超过readlimit之后将自动清除标记,但注意在BufferedInputStream，该行为可能会失效
            System.out.println("标记当前读取的位置...");
            data.mark(5);
            byte[] bytes1 = new byte[10];
            int read2 = data.read(bytes1);
            System.out.println("读入了" + read2 + "个字节");
            System.out.println(Arrays.toString(bytes1));
            System.out.println("重置标记，回到标记处...");
            data.reset();
            System.out.println("准备读取剩余全部的字节...");
            System.out.println("先看看剩余还有多少个字节可以读：" + data.available());
            byte[] bytes2 = new byte[data.available()];
            System.out.println("读取剩余的全部字节");
            int read3 = data.read(bytes2);
            System.out.println("读入了" + read3 + "个字节");
            System.out.println(Arrays.toString(bytes2));
        }else{
            System.out.println("该流没有标记和重置能力");
        }
        System.out.println("关闭流");
        data.close();
    }
}
