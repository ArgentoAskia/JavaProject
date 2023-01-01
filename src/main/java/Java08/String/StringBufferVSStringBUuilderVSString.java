package Java08.String;

import java.util.UUID;

public class StringBufferVSStringBUuilderVSString {
    //VS StringBuilder 线程安全，线程非安全
    public static void main(String[] args) {
        // StringBuffer 用来拼接字符串
        // String 不可变对象
        long l = System.currentTimeMillis();
        String s = "456";
        for (int i = 0; i < 100000; i++) {
            // 慢在创建对象的过程
            // UUID.randomUUID().toString()一般用来生成一个随机的字符串（唯一序列，UID）
            s = s + UUID.randomUUID().toString();
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        StringBuffer stringBuffer = new StringBuffer();
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            stringBuffer.append(UUID.randomUUID().toString());
        }
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2);

        StringBuilder stringBuilder = new StringBuilder();
        long l4 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            stringBuilder.append(UUID.randomUUID().toString());
        }
        long l5 = System.currentTimeMillis();
        System.out.println(l5 - l4);
    }
}
