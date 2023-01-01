package Java08.System;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        // 1.JVM可用的处理器核心数
        int i = runtime.availableProcessors();

        // 2.内存相关
        long l = runtime.freeMemory();
        long l1 = runtime.maxMemory();
        long l2 = runtime.totalMemory();


        // 3.参考System类
//        runtime.exit(0);
//        runtime.gc();

        // 4.执行CMD指令
        Process exec = runtime.exec("cmd /c echo %PATH%");
        InputStream inputStream = exec.getInputStream();
        byte[] bytes = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        while((inputStream.read(bytes)) != -1){
            String s = new String(bytes, "GBK");
            Arrays.fill(bytes, (byte)0);
            stringBuilder.append(s);
        }
        System.out.println(stringBuilder.toString());

    }
}
