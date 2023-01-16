package cn.argentoaskia.demo;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CipherOutputStreamDemo {
    // 加密类型
    private static final String ENCRYPT_TYPE = "AES";
    // 加密秘钥，长度为24字节
    private static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        // 1.创建写出流，写出解密的内容
        File output = new File("Java-IOStream/src/main/resources/CipherStream/data-decipher.txt");
        if (!output.exists()){
            output.createNewFile();
        }
        FileOutputStream deCipherData = new FileOutputStream(output);
        // 读入加密内容
        InputStream cipherData = CipherOutputStreamDemo.class.getResourceAsStream("/CipherStream/data-cipher.txt");
        if (cipherData == null){
            System.out.println("请先运行CipherInputStreamDemo生成加密文件");
            return;
        }
        byte[] cipherDataBytes = new byte[cipherData.available()];
        cipherData.read(cipherDataBytes);
        System.out.println("加密后的字节：" + Arrays.toString(cipherDataBytes) + new String(cipherDataBytes, StandardCharsets.UTF_8));


        // 2.创建加密类，指定特殊的加密算法类型
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        // 创建加密密钥
        Key key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ENCRYPT_TYPE);
        // 初始化加密算法，注意解密的话要指定DECRYPT_MODE常量
        cipher.init(Cipher.DECRYPT_MODE, key);

        // 3.创建解密流
        CipherOutputStream cipherOutputStream = new CipherOutputStream(deCipherData, cipher);
        cipherOutputStream.write(cipherDataBytes);
        cipherOutputStream.flush();

        cipherOutputStream.close();
        cipherData.close();
        deCipherData.close();


        // 4.读取解密后的内容
        InputStream decipherData = CipherOutputStream.class.getResourceAsStream("/CipherStream/data-decipher.txt");
        byte[] bytes = new byte[decipherData.available()];
        decipherData.read(bytes);
        System.out.println("解密后的数据：" + Arrays.toString(bytes) + new String(bytes, StandardCharsets.UTF_8));
        decipherData.close();
        System.err.println("如果Demo抛出NPE，请检查下target文件夹是否有data-decipher.txt");
    }
}
