package cn.argentoaskia.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * GZipInputStream解压文件
 */
public class GZipInputStreamDemo {
    public static void main(String[] args) throws Exception{
        // 1. 读取压缩文件
        InputStream compressData = GZipInputStreamDemo.class.getResourceAsStream("/GZipStream/compress-data.compress");

        // 2. 解压
        GZIPInputStream gzipInputStream = new GZIPInputStream(compressData);
        // buffer
        byte[] compressBytes = new byte[1024];
        ArrayList<byte[]> decompressData = new ArrayList<>();
        int totalSize = 0;
        int readSize = 0;
        // 解压的内容会被放在buffer——compressBytes字节数组中，每次获取特定的字节，直到没有字节
        while ((readSize = gzipInputStream.read(compressBytes)) > 0){
            totalSize += readSize;
            decompressData.add(Arrays.copyOf(compressBytes, readSize));
            // 清空buffer
            Arrays.fill(compressBytes, (byte)0);
        }

        int cursor = 0;
        byte[] decompressBytes = new byte[totalSize];
        for (byte[] bytes : decompressData) {
            // TODO: 2023/1/18  遍历数组的工具类、数组转List、Set等等
           System.arraycopy(bytes, 0 , decompressBytes, cursor, bytes.length);
           cursor = cursor + bytes.length;
        }
        // TODO: 2023/1/18  包装器数组转基本类型数组等

        // 3. 写出解压后的文件
        File file = new File("Java-IOStream/src/main/resources/GZipStream/decompress-data.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(decompressBytes);
        fileOutputStream.close();
    }
}
