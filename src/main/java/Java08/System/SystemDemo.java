package Java08.System;


import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * System类常用方法.
 */
public class SystemDemo {
    public static void main(String[] args) {
        // 1.从本地实现的方式复制一个数组，注意该数组复制方法有比较多的限制。
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array2 = new int[9];
        System.out.println("复制前array2：" + Arrays.toString(array2));
        System.arraycopy(array1, 0, array2, 0, array1.length);
        System.out.println("复制后array2：" + Arrays.toString(array2));
        System.out.println("=================================================");

        // 2.获取环境变量
        Map<String, String> getenv = System.getenv();
        // 获取JVM内部的属性键值对
        Properties properties = System.getProperties();
        System.out.println("properties：");
        properties.list(System.out);
        System.out.println("---------------------------------------");
        System.out.println("envs:");
        Set<String> set = getenv.keySet();
        for (String key :
                set) {
            String s = getenv.get(key);
            System.out.println(key + "=" + s);
        }

        System.out.println("=================================================");
        // 3.获取当时前时间的毫秒数
        long l = System.currentTimeMillis();
        System.out.println(l);
        // 获取虚拟机启动之后的运行了多少纳秒，此方法只能用于测量经过了多少时间，提供纳秒级别的经过时间测量
        long l1 = System.nanoTime();
        long l2 = System.nanoTime();
        System.out.println("经过了多少纳秒：" + (l2-l1));

        // 4.获取对象地址的Hash值
        int i = System.identityHashCode(array2);
        System.out.println(i);

        // 5.获取系统分隔符
        String s = System.lineSeparator();
        System.out.println(s);

        // 6.JNI方法加载DLL、SO
//        System.load();
//        System.loadLibrary();

        // 7.强制GC
        System.gc();

        // 8.强制结束虚拟机
        System.exit(0);
    }
}
