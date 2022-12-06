package cn.argentoaskia.demo;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
