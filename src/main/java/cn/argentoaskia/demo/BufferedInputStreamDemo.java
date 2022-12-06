package cn.argentoaskia.demo;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 带缓冲功能、标记重置功能的流。
 * 该流的基本API和InputStream一样。
 * 但是需要特别注意，在处理标记重置能力的时候，决定标记是否要被清除，mark方法的参数已经不是唯一的因素，
 * 具体运行下面的demo，去掉17行的注释并且注释上16行
 */
public class BufferedInputStreamDemo {
   public static void main(String[] args) throws Exception{
       InputStream data = InputStreamDemo.class.getResourceAsStream("/data.txt");
       BufferedInputStream bufData = new BufferedInputStream(data, 2);    // 如果使用这句就会抛异常
//       BufferedInputStream bufData = new BufferedInputStream(data);
       System.out.println("标记流，并且允许后面允许读入5个字节，超过5个就自动清除标记...");
       bufData.mark(5);
       System.out.println("读入10个字节...");
       byte[] bytes = new byte[10];
       int read = bufData.read(bytes);
       System.out.println("读入了" + read + "个字节");
       System.out.println(Arrays.toString(bytes));
       System.out.println("清除标记...");
       bufData.reset();
       System.out.println("清除标记成功!!!!");
       System.out.println("重新读入刚刚的10个字节...");
       int read1 = bufData.read(bytes);
       System.out.println("重新读入了" + read1 + "个字节");
       System.out.println(Arrays.toString(bytes));
       System.out.println("关闭流");
       bufData.close();
       System.out.println("因此在BufferedInputStream中证明了mark()中readlimit参数并不是决定标记是否被清除的唯一因素");
       System.out.println("标记是否被清除，还和缓冲区的大小有关！！！");
       System.out.println("参考资料：https://blog.csdn.net/liuxiao723846/article/details/117693340");
   }
}
