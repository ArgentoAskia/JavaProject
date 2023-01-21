package cn.argentoaskia.demo;


import Java08.homework.RandomUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

/**
 * byteArrayOutputStream：
 * 字节输出流
 */
public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) throws Exception{
        // 创建一个可以往里面写字节的流，ByteArrayOutputStream有个好处就是当一段大的字节被分段接收的时候，采用这个流可以陆陆续续把所有的段字节都给整合起来
        // 在网络传输中非常好用。
        Random random = new Random();
        // 模拟接收的总字节量
        int receiveSize = 10000;
        // 每段的大小
        int partSize = 0;

        // 使用ByteArrayOutputStream多段拼接字节，默认构造方法是初始化一个32字节的流，超过之后会自动扩容
        // 当然你也可以使用注释掉的这行代码来创建更大的流：
        //        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for (int i = 0; i < receiveSize; i = i + partSize) {
            partSize = RandomUtil.randomInt(20, 50);
            if (i + partSize > receiveSize){
                partSize = receiveSize - i;
            }
            byte[] bytes = RandomUtil.randomByteArray(partSize);
            byteArrayOutputStream.write(bytes);
            // 若想获取流当前存入了多少个字节，可以使用size()方法
            int size = byteArrayOutputStream.size();
            System.out.println("当前IO流内已存入：" + size + "个字节！");
        }

        // ByteArrayOutputStream还提供了toByteArray()将流内数据转为字节数组的能力
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(bytes));

        // 还可以使用WriteTo()将拼接好的字节直接送给另外一个流,并执行另外一个流的功能
        File file = new File("Java-IOStream/src/main/resources/ByteArrayStream/ByteArrayOutputText.txt");
        if (!file.exists())     file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byteArrayOutputStream.writeTo(fileOutputStream);


        // 如果是字符，还可以直接转为字符串
        // reset方法重置到流开头的地方，但是原本的数据理论上仍然保留，只是把读取指针（流内部维护了一个int的count变量代表写入了多少个字节）移动到流的第一个位置！
        // 无论是toString()方法还是toByteArray()方法，他们生成的范围永远都是0-流读取指针（也就是count），而不是内部维护的buf的大小，切记！！
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write("新年快乐！".getBytes(StandardCharsets.UTF_8));
        String s = byteArrayOutputStream.toString("UTF-8");
        System.out.println(s);



        // byteArrayOutputStream理论上无需要调用close方法和flush方法,它们不会执行任何代码
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
    }
}
