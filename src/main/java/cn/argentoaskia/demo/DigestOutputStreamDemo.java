package cn.argentoaskia.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * DigestOutputStream可以实现在创建内容或者文件的时候，生成该文件的对应Hash值，这样当对方下载完文件的时候，通过DigestInputStream
 * 就可以知道自己下载的文件是否完整。（理论上可行）
 *
 */
public class DigestOutputStreamDemo {
    public static void main(String[] args) throws Exception {
        // 写出内容的Hash值放在data-digest.txt
        File output = new File("Java-IOStream/src/main/resources/data-digest.txt");
        if (!output.exists()){
            output.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(output);

        // 读入内容
        InputStream data = DigestOutputStreamDemo.class.getResourceAsStream("/data.txt");
        int available = data.available();
        byte[] dataBuf = new byte[available];
        data.read(dataBuf);
        data.close();

        // 创建md5算法实例，并写出data.txt的内容到控制台
        MessageDigest md5 = MessageDigest.getInstance("md5");
        System.out.println("data.txt中的内容：");
        DigestOutputStream digestOutputStream = new DigestOutputStream(System.out, md5);
        digestOutputStream.write(dataBuf);
        System.out.println();
        System.out.println();

        // 获取摘密Hash值,并写到文件中
        byte[] digest = digestOutputStream.getMessageDigest().digest();
        System.out.println("md5值（字节）：");
        System.out.println(Arrays.toString(digest));
        System.out.println();
        System.out.println("md5值（字符，可能会显示乱码）：");
        System.out.println(new String(digest));
        fileOutputStream.write(digest);

        // 关闭摘密流和数据输出流
        fileOutputStream.close();
        digestOutputStream.close();

    }
}
