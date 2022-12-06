package cn.argentoaskia.demo;

import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

/**
 * 具有能够校验内容的完整性的能力的流
 */
public class CheckedInputStreamDemo {
    public static void main(String[] args) throws Exception{
        // 1.参数1：inputstream 2.参数Checksum，检测方法：Adler32、CRC32
        InputStream data = InputStreamDemo.class.getResourceAsStream("/data.txt");
        Adler32 adler32 = new Adler32();
        CheckedInputStream checkedInputStream = new CheckedInputStream(data, adler32);
        Checksum checksum = checkedInputStream.getChecksum();

        // 2.需要先读了字节，才会有冗余检验值，否则值检验值是1
        // 读出去的字节如果不同，则生成的的checksum也不一样！！！
        // 冗余校验值是可以一直更新的，根据当前读取的字节数据来决定生成，每调用一次read()更新一次
        // 具体生成的校验值是多少，有内容来决定。
        // 如你可以更改下面的字节数组的大小，查看生成的冗余值都不一样
        int available = checkedInputStream.available();
        checkedInputStream.mark(available);
        byte[] context = new byte[available];
        int readResult = checkedInputStream.read(context);
        System.out.println("共读入了" + readResult + "个字节");
        System.out.println(Arrays.toString(context));


        // 3.获取所有内容的对应的检验值
        long value = checksum.getValue();
        System.out.println("冗余检测对照值：" + value);
        System.out.println("冗余检测对照值（binary）：" + Long.toBinaryString(value));
        System.out.println("冗余检测对照值（hex）：" + Long.toHexString(value));

        // 重置读取位置
        checkedInputStream.reset();

        System.out.println("重新读字节，预计读入7次，每次10个字节，总共62个字节");
        int i = available / 10;
        // 最后一次的字节数
        int least = available % 10;
        byte[] bytes = new byte[10];
        for (int j = 0; j < i; j++) {
            int read = checkedInputStream.read(bytes);
            System.out.println("第" + j + "次读取，共读入了" + read + "个字节");
            System.out.println(Arrays.toString(bytes));
            long value2 = checksum.getValue();
            System.out.println("第" + j + "次读取的冗余检测对照值：" + value2);
        }
        Arrays.fill(bytes, (byte)0);
        int read = checkedInputStream.read(bytes,0, least);
        System.out.println("第7次读取，共读入了" + read + "个字节");
        System.out.println(Arrays.toString(bytes));
        long value2 = checksum.getValue();
        System.out.println("第7次读取的冗余检测对照值：" + value2);

        checkedInputStream.close();
        data.close();
        System.err.println("如结果所示，分开读取的结果和一次性读取的结果是不同的，证明每次读取的字节不同生成的校验值也不同！");
        System.err.println("只有当双方都读入了相同的字节，生成的冗余码才会相同！！");
    }
}
