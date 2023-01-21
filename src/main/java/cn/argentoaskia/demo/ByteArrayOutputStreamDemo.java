package cn.argentoaskia.demo;


import Java08.homework.RandomUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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


        // 还可以使用WriteTo()将拼接好的字节直接送给另外一个流,并执行另外一个流的功能
        File file = new File("Java-IOStream/src/main/resources/ByteArrayStream/ByteArrayOutputText.txt");
        if (!file.exists())     file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byteArrayOutputStream.writeTo(fileOutputStream);



        // byteArrayOutputStream理论上无需要调用close方法和flush方法,它们不会执行任何代码
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
    }
}
