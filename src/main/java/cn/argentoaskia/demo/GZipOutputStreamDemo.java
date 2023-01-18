package cn.argentoaskia.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;

/**
 * GZIPOutputStream压缩文件：
 * 关于GZIP，参考：
 *  https://blog.csdn.net/gehong3641/article/details/127079600
 *  https://www.jianshu.com/p/4033028e5570
 *
 *  如果遇到运行时抛出NPE请再运行一遍，可能是读入文件是maven没反应过来
 *
 */
public class GZipOutputStreamDemo {
    public static void main(String[] args) throws Exception{
        // 1.读入待压缩的内容
        System.out.println("==========>  " + LocalDateTime.now() + "：1.读入待压缩的内容...");
        InputStream resource = GZipOutputStreamDemo.class.getResourceAsStream("/GZipStream/target-data.txt");
        int resourceDataCount = resource.available();
        byte[] data = new byte[resourceDataCount];
        int read = resource.read(data);
        if (read != resourceDataCount){
            System.out.println("==========>  " + LocalDateTime.now() + "：内容未读满！！");
            return;
        }
        System.out.println("==========>  " + LocalDateTime.now() + "：读入完毕！");

        // 2.压缩文件
        System.out.println("==========>  " + LocalDateTime.now() + "：2.开始压缩文件...");
        File file = new File("Java-IOStream/src/main/resources/GZipStream/compress-data.compress");
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        gzipOutputStream.write(data);
        gzipOutputStream.flush();
        gzipOutputStream.close();
        fileOutputStream.close();
        resource.close();
        System.out.println("==========>  " + LocalDateTime.now() + "：压缩完毕！请查看/GZipStream/compress-data.compress");

        // 3.读入被压缩的文件
        System.out.println("==========>  " + LocalDateTime.now() + "：3.统计压缩数据...");
        InputStream compressData = GZipOutputStreamDemo.class.getResourceAsStream("/GZipStream/compress-data.compress");
        int compressAvailable = compressData.available();
        byte[] compressBytes = new byte[compressAvailable];
        compressData.read(compressBytes);
        compressData.close();

        // 4.对比前后字节，计算压缩比：
        int targetFileSize = data.length;
        int compressFileSize = compressBytes.length;
        double percent = compressFileSize * 1.0 / targetFileSize;
        System.out.println("原文件大小：" + targetFileSize + "字节" + " = " + (targetFileSize / 1024.0) + "KB" + " = " + (targetFileSize / 1024.0 / 1024.0) + "MB");
        System.out.println("压缩后文件大小：" + compressFileSize + "字节" + " = " + (compressFileSize / 1024.0) + "KB" + " = " + (compressFileSize / 1024.0 / 1024.0) + "MB");
        System.out.printf("压缩率：%.2f" , percent * 100);
        System.out.print("%");
    }
}
