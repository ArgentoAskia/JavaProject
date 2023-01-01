package cn.argentoaskia.demo;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用于信息摘密相关的流，和CipherInputStream差不多，但是CipherInputStream一般用于网络传输多点，并且他们之间采用的摘密算法也有区别
 * CipherInputStream一般都是采用CRC32等
 * DigestInputStream一般采用类似于MD5，SHA-1等
 */
public class DigestInputStreamDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        InputStream dataStream = DigestInputStreamDemo.class.getResourceAsStream("/data.txt");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        DigestInputStream digestInputStream = new DigestInputStream(dataStream, md5);
        int available = digestInputStream.available();
        byte[] bytes = new byte[available];
        int read = digestInputStream.read(bytes);
        if (read == available){
            byte[] digest = digestInputStream.getMessageDigest().digest();
            System.out.println("MD5:" + new String(digest));
        }
    }
}
