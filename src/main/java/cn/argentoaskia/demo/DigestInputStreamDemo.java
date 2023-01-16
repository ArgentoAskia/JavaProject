package cn.argentoaskia.demo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
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
        // 你可以修改一下Data.txt里面的内容，表示网络下载中，文件字节因为网络波动等问题产生缺失，会看到不同的结果
        InputStream dataStream = DigestInputStreamDemo.class.getResourceAsStream("/data.txt");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        DigestInputStream digestInputStream = new DigestInputStream(dataStream, md5);
        int available = digestInputStream.available();
        byte[] bytes = new byte[available];
        int read = digestInputStream.read(bytes);
        if (read == available){
            byte[] digest = digestInputStream.getMessageDigest().digest();
            System.out.println("MD5（字节）：" + Arrays.toString(digest));
            System.out.println();
            System.out.println("MD5（字符，可能会乱码）:" + new String(digest, StandardCharsets.US_ASCII));
            System.out.println();
            System.out.println("===================== 校验文件前后 =========================");
            InputStream dataDigest = DigestInputStreamDemo.class.getResourceAsStream("/data-digest.txt");
            byte[] dataDigestBytes = new byte[dataDigest.available()];
            dataDigest.read(dataDigestBytes);
            System.out.println("生成文件时产生的Hash字节：" + Arrays.toString(dataDigestBytes));
            System.out.println("输入流读入文件时产生的Hash字节" + Arrays.toString(digest));
            System.out.println("两个字节集是否相等：" + Arrays.equals(dataDigestBytes, digest));
            if (Arrays.equals(dataDigestBytes, digest))
                System.out.println("可以看出当前后文件内容相同的时候，生成的MD5Hash值也相同！");
            else
                System.out.println("当因为网络环境等原因，导致文件出现缺失，两个文件生成的MD5Hash值就会不一样！因此这个时候就会知道下载的文件不全");
            dataDigest.close();
        }
        digestInputStream.close();
        dataStream.close();
    }
}
