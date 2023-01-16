package cn.argentoaskia.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 用于信息摘密相关的流，和CheckedInputStream差不多，但是CheckedInputStream一般用于网络传输多点，而DigestInputStream常用于校验文件完整性
 *
 * 并且他们之间采用的摘密算法也有区别：
 * CheckedInputStream一般都是采用CRC32等
 * DigestInputStream一般采用类似于MD5，SHA-1等
 */
public class DigestInputStreamDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // 0.判断data-download是否存在：
        File download = new File("Java-IOStream/src/main/resources/DigestStream/data-download.txt");
        if (!download.exists()){
            System.out.println("不存在下载文件data-download.txt，请先运行DigestOutputStreamDemo");
            return;
        }
        // 1.先打印原始文件内容和它对应的Hash字节
        System.out.println("===================== 原始文件的内容及Hash值 =====================");
        InputStream dataStream = DigestInputStreamDemo.class.getResourceAsStream("/data.txt");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        DigestInputStream digestInputStream = new DigestInputStream(dataStream, md5);
        int available = digestInputStream.available();
        byte[] bytes = new byte[available];
        int read = digestInputStream.read(bytes);
        // 读入原始内容的Hash值
        InputStream dataDigest = DigestInputStreamDemo.class.getResourceAsStream("/DigestStream/data-digest.txt");
        byte[] dataDigestBytes = new byte[dataDigest.available()];
        dataDigest.read(dataDigestBytes);
        if (read == available){
            System.out.println("data.txt内容(字节)：" + Arrays.toString(bytes));
            System.out.println("data.txt内容(文本)：" + new String(bytes));
            System.out.println();

            byte[] digest = digestInputStream.getMessageDigest().digest();
            System.out.println("MD5Hash（字节）：" + Arrays.toString(digest));
            System.out.println("MD5Hash（字符，可能会乱码）:" + new String(digest, StandardCharsets.US_ASCII));
            System.out.println();

            System.out.println("data-digest.txt：" + Arrays.toString(dataDigestBytes));
            dataDigest.close();
        }
        digestInputStream.close();
        dataStream.close();
        System.out.println("============================================================");
        System.out.println();
        // 2.读入下载的文件，并开始校验文件完整性
        InputStream downloadFile = DigestInputStreamDemo.class.getResourceAsStream("/DigestStream/data-download.txt");
        DigestInputStream downloadFileDigest = new DigestInputStream(downloadFile, md5);
        byte[] bytes1 = new byte[downloadFile.available()];
        int read1 = downloadFileDigest.read(bytes1);
        if (read1 == bytes1.length){
            System.out.println("data-download.txt的内容（字节）：" + Arrays.toString(bytes1));
            System.out.println("data-download.txt的内容（字符）：" + new String(bytes1));
            System.out.println();
            byte[] digest = downloadFileDigest.getMessageDigest().digest();
            System.out.println("data-download.txt的Hash值（字节）：" + Arrays.toString(digest));
            System.out.println("data-download.txt的Hash值（字符）：" + new String(digest));
            System.out.println();
            System.out.println("data.txt和data-download.txt是否相同：" + Arrays.equals(dataDigestBytes, digest));
            if (Arrays.equals(dataDigestBytes, digest))
                System.out.println("可以看出当前后文件内容相同的时候，生成的MD5Hash值也相同！");
            else
                System.out.println("当因为网络环境等原因，导致文件出现缺失，两个文件生成的MD5Hash值就会不一样！因此这个时候就会知道下载的文件不全");
        }
        downloadFileDigest.close();
        downloadFile.close();
    }
}
