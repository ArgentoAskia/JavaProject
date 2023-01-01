package Java08.Number;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class RandomDemo {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("--> 3.1 随机数类使用 <--");
        System.out.println("=================================================");
        // 1-1.创建随机数对象
        Random random = new Random();
        // 1-2.随机boolean值，要么true、要么false
        boolean b = random.nextBoolean();
        // 1-3.随机字节数组，每个字节范围在-128-127
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        // 1-4.随机Double,范围在Double.MIN_VALUE - Double.MAX_VALUE
        double v = random.nextDouble();
        // 1-5.随机Float,范围在Float.MIN_VALUE - Float.MAX_VALUE
        float v1 = random.nextFloat();
        // 1-6.随机Gaussian，0.0-1.0
        double v2 = random.nextGaussian();
        // 1-7.随机Integer，范围在Integer.MIN_VALUE - Integer.MAX_VALUE
        int i = random.nextInt();
        // 1-8.范围内取随机Integer，范围在0-bound(不包括bound)
        int i1 = random.nextInt(5);
        // 1-9.随机Long,范围在Long.MIN_VALUE-MAX.VALUE
        long l = random.nextLong();
        System.out.println("随机布尔值：" + b);
        System.out.println("随机字节组：" + Arrays.toString(bytes));
        System.out.println("随机double：" + v);
        System.out.println("随机float：" + v1);
        System.out.println("随机Gaussian：" + v2);
        System.out.println("随机Int：" + i);
        System.out.println("随机Int2：" + i1);
        System.out.println("随机Long：" + l);

        // 2-1.Math方法随机0-1值，注意区别nextGaussian()（好像也没区别）
        double random1 = Math.random();
        System.out.println("0-1随机：" + random1);

        // 3-1.UUID
        // 随机生成一个32位字符串序列
        // 一般用于登录令牌的token
        String s = UUID.randomUUID().toString();
        System.out.println(s);


    }
}
