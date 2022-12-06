package cn.argentoaskia.demo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 用于对数据进行加密和解密的IO流，该流涉及到{@code Cipher}类的使用，这里只做简单介绍
 */
public class CipherInputStreamDemo {
    // 加密类型
    private static final String ENCRYPT_TYPE = "AES";
    // 加密秘钥，长度为24字节
    private static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        // 1.将内容创建成流
        InputStream dataInputStream = CipherOutputStream.class.getResourceAsStream("/data.txt");

        // 2.创建加密类，指定特殊的加密算法类型
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        // 创建加密密钥
        Key key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ENCRYPT_TYPE);
        // 初始化加密算法
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // 3.创建加密流
        CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, cipher);

        // cipherInputStream.available()永远是0，不知道为什么。
        // System.out.println(cipherInputStream.available());
        // 因此需要预先分配一个大空间来获取字节。
        byte[] encryptBytes = new byte[1024];
        List<byte[]> list = new ArrayList<>();
        int read = 0;
        int totalRead = 0;
        while((read = cipherInputStream.read(encryptBytes)) > 0){
            // 复制读取的部分
            totalRead = totalRead + read;
            byte[] bytes = Arrays.copyOf(encryptBytes, read);
            list.add(bytes);
            System.out.println(Arrays.toString(bytes));
            System.out.println(new String(bytes));
        }
        byte[] totalBytes = new byte[totalRead];
        int cursor = list.get(0).length;
        for (int i = 0; i < list.size(); i++) {
            if (i == 1){
                for (byte b :
                        list.get(i)) {
                    totalBytes[cursor++] = b;
                }
                continue;
            }
            int a = 0;
            for (byte b :
                    list.get(0)) {
                totalBytes[a++] = b;
            }
        }
        System.out.println(Arrays.toString(totalBytes));
        cipherInputStream.close();
        dataInputStream.close();
    }


}
