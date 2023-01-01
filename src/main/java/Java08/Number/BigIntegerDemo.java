package Java08.Number;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerDemo {
    public static void main(String[] args) {
        // 1.构造BigInteger类的对象，最常用构造器：
        // BigInteger(String val);
        // BigInteger(String val, int radix);
        // radix:进制
        BigInteger bigInteger = new BigInteger("999999999999999999999999999");
        BigInteger bigInteger1 = new BigInteger("100", 16); // 256
        // BigInteger(int numBits, Random rnd);
        BigInteger bigInteger2 = new BigInteger(25, new Random());
        System.out.println(bigInteger2);

        // 2.基本加减乘除
        BigInteger add = bigInteger.add(bigInteger1);
        BigInteger subtract = bigInteger.subtract(bigInteger1);
        BigInteger multiply = bigInteger.multiply(bigInteger1);
        BigInteger divide = bigInteger.divide(bigInteger1);
        BigInteger[] bigIntegers = bigInteger.divideAndRemainder(bigInteger1);
        System.out.println("加法结果：" + add);
        System.out.println("减法结果：" + subtract);
        System.out.println("乘法结果：" + multiply);
        System.out.println("除法结果：" + divide);
        System.out.println("除数结果：" + bigIntegers[0] + ",余数结果：" + bigIntegers[1]);

        // 3.特殊运算
        BigInteger gcd = bigInteger.gcd(bigInteger2);
        BigInteger abs = bigInteger2.abs();
        BigInteger pow = bigInteger.pow(2);
        BigInteger max = bigInteger.max(bigInteger1);
        BigInteger min = bigInteger.min(bigInteger2);
        BigInteger prime = min.nextProbablePrime();
        System.out.println("取公约数:" + gcd);
        System.out.println("取绝对值:" + abs);
        System.out.println("幂运算:" + pow);
        System.out.println("比较获取最大数:" + max);
        System.out.println("比较获取最小数:" + min);
        System.out.println("获取距离最近的质数:" + prime);

        // 4.左移右移
        BigInteger bigInteger3 = bigInteger.shiftLeft(3);
        System.out.println(bigInteger + "左移三位=" + bigInteger3);
        BigInteger bigInteger4 = bigInteger.shiftRight(5);
        System.out.println(bigInteger + " 右移三位=" + bigInteger4);
    }
}
