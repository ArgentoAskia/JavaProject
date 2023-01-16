package cn.argentoaskia.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.*;
import java.util.function.Function;

/**
 * DigestOutputStream可以实现在创建内容或者文件的时候，生成该文件的对应Hash值，这样当对方下载完文件的时候，通过DigestInputStream
 * 就可以知道自己下载的文件是否完整。（理论上可行）
 *
 */
public class DigestOutputStreamDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("============ 下面模拟文件打包时生成MD5Hash的过程 ============");
        // 1.写出内容的Hash值放在data-digest.txt
        File output = new File("Java-IOStream/src/main/resources/DigestStream/data-digest.txt");
        if (!output.exists()){
            output.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(output);

        // 2.读入内容
        InputStream data = DigestOutputStreamDemo.class.getResourceAsStream("/data.txt");
        int available = data.available();
        byte[] dataBuf = new byte[available];
        data.read(dataBuf);

        // 3.创建md5算法实例，并写出data.txt的内容到控制台
        MessageDigest md5 = MessageDigest.getInstance("md5");
        System.out.println("data.txt中的内容：");
        DigestOutputStream digestOutputStream = new DigestOutputStream(System.out, md5);
        digestOutputStream.write(dataBuf);
        // 冲刷输出流
        digestOutputStream.flush();
        System.out.println();
        System.out.println();

        // 4.获取摘密Hash值,并写到文件中
        byte[] digest = digestOutputStream.getMessageDigest().digest();
        System.out.println("md5值（字节）：");
        System.out.println(Arrays.toString(digest));
        System.out.println();
        System.out.println("md5值（字符，可能会显示乱码）：");
        System.out.println(new String(digest));
        fileOutputStream.write(digest);
        // 冲刷输出流
        digestOutputStream.flush();

        System.out.println();
        System.out.println("==================== 华丽丽 ===================");
        System.out.println();
        System.out.println("==================== 下面模拟文件下载过程 ====================");
        System.out.println("1.文件下载完成之后会在resources/DigestStream下面创建一个data-download.txt！");
        System.out.println("2.根据系统随机来判定创建的文件是否和源文件data.txt相同，以模拟下载过程中的网络传输的不稳定性！");
        Random random = new Random();
        File downloadFile = new File("Java-IOStream/src/main/resources/DigestStream/data-download.txt");
        if (!downloadFile.exists()){
            downloadFile.createNewFile();
        }
        FileOutputStream downloadFileStream = new FileOutputStream(downloadFile);
        if (random.nextBoolean()){
            // 创建相同的文件
            downloadFileStream.write(dataBuf);
            // 冲刷输出流
            digestOutputStream.flush();
        }else{
            // 创建不同的文件
            // 字符数组转包装器数组utils
            List<Byte> byteList = new ArrayList<>();
            for (byte b :
                    dataBuf) {
                byteList.add(b);
            }
            // 去掉其中几个字节，代表网络下载时部分字节下载不回来或者网络卡顿导致延迟等
            for (int i = 0; i < random.nextInt(byteList.size() / 2); i++) {
                byteList.remove(random.nextInt(byteList.size()));
            }
            byte[] bytes = new byte[byteList.size()];
            int i = 0;
            for (Byte b : byteList) bytes[i++] = b;
            downloadFileStream.write(bytes);
            // 冲刷输出流
            digestOutputStream.flush();
        }
        System.out.println("文件下载完成，后期请运行DigestInputStreamDemo来查看文件是否完整！！");
        // 切记流的关闭要放在最后，因为文中第三步会导致System.out关闭，也就无法输出任何内容到控制台
        data.close();
        fileOutputStream.close();
        digestOutputStream.close();
        downloadFileStream.close();

    }
}
