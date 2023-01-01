package Java08.Number;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class PackageNumberClass {

    /**
     * 包装器类演示Demo
     */
    public void NumberPackageClassDemo() {
        System.out.println("=================================================");
        System.out.println("--> 1.1 包装器类支持自动装拆箱，能够直接将基本类型赋值给包装器类，也能够把包装器类直接拆箱成基本类型 <--");
        System.out.println("!-- 1.2 注意，当包装器类为null时不能转为基本类型，会抛出NullPointerException--!");
        // nullPackageTypeCastPrimitiveType();
        // packageTypeInBox();
        // packageTypeOutBox();
        System.out.println("=================================================");
        System.out.println();
        System.out.println("=================================================");
        System.out.println("--> 2.1 包装器类API演示-对象级别 <--");
        packageTypeAPIShow();
        integerPackageTypeAPIs();
        System.out.println("=================================================");


    }

    /**
     * Integer API
     */
    public void integerPackageTypeAPIs() {
        System.out.println("Integer相关：");
        // 返回指定int值的二进制补码表示形式中位数为1的个数。
        // 整数的补码是本身，负数的补码是 {原码符号位不变} + {数值位按位取反后+1}
        /*
            +97原码 = 0110_0001b
            +97补码 = 0110_0001b
            -97原码  = 1110_0001b
            -97补码  = 1001_1111b
            0 ： 00000000000000000000000000000
            -1补码：1000000000000000000000000000001(32位) --> 111111111111111111111111111111110(32位) --> 11111111111111111111111111111111(32位)
         */
        int bitCount = Integer.bitCount(99);
        int bitCount1 = Integer.bitCount(-1);
        System.out.println("（int 4字节，总共32位）统计99的补码有多少个1位：" + bitCount);
        System.out.println("（int 4字节，总共32位）统计-1有多少个1位：" + bitCount1);

        // 如果返回1，代表 23大于52，返回0，代表23等于52，返回-1，代表小于52。
        int compare = Integer.compare(23, 52);
        System.out.println("23和52比较大小，结果:" + compare);
        // 先将-23取反，然后再比较，实际上是23和12比较。
        int compareUnsigned = Integer.compareUnsigned(-23, 12);
        System.out.println("-23和12进行无符号比较大小，结果:" + compareUnsigned);
        // 将八进制，十进制，16禁止数值转成10进制
        Integer hex = Integer.decode("0x23");
        Integer dec = Integer.decode("1000");
        Integer oct = Integer.decode("0752101");
        System.out.println("0x23转10：" + hex);
        System.out.println("1000转10：" + dec);
        System.out.println("0752101转10：" + oct);

        // 无符号整除, dividend除以divisor，dividend和divisor都要是正数，否则返回0
        int divideUnsigned = Integer.divideUnsigned(6, 3);
        System.out.println("6 / 3 = 2? " + divideUnsigned);
        // 获取整数形式Java环境变量(包括部分Windows的环境变量，如Path)
        Integer sun_arch_data_model = Integer.getInteger("sun.arch.data.model");
        System.out.println("sun_arch_data_model=" + sun_arch_data_model);
        // 获取一个整数的HashCode，通常返回自己本身
        int hashCode = Integer.hashCode(-123);
        System.out.println("hashcode:" + hashCode);

        // 获取位是1所在的最高位出现的位置，如：
        // 20: 00010100 获取高位的1：00010000=16
        int highestOneBit = Integer.highestOneBit(20);
        System.out.println("20最高位的1是=" + Integer.toString(highestOneBit, 2));
        // 获取低位
        // 21: 00010101 --> 00000001    20: 00010100 --> 00000100
        int lowestOneBit = Integer.lowestOneBit(20);
        System.out.println("20最低位的1是=" + Integer.toString(lowestOneBit, 2));

        int max = Integer.max(32, 21);
        int min = Integer.min(32, 21);
        System.out.println("32和21比较谁大："  + max);
        System.out.println("32和21比较谁小："  + min);
        // 从高位开始遇到第一个位是1之前有多少个连续的0
        // 00000000 00000000 00000000 00000100 = 29
        // 00000000 00000000 00000000 00000011 = 30
        // 00000000 00000000 00000000 00000000 = 32
        int i = Integer.numberOfLeadingZeros(4);
        int i1 = Integer.numberOfLeadingZeros(3);
        System.out.println(Integer.toString(4,2) + "最高位往右走第一次出现1位之前有" + i + "个0");
        System.out.println(Integer.toString(3,2) + "最高位往右走第一次出现1位之前有" + i1 + "个0");
        // 从低位开始遇到第一个位是1之前有多少个连续的0
        // 00000000 00000000 00000000 00000100 = 2
        // 00000000 00000000 00000000 00000011 = 0
        // 00000000 00000000 00000000 00000000 = 32
        int i2 = Integer.numberOfTrailingZeros(4);
        int i3 = Integer.numberOfTrailingZeros(3);
        System.out.println(Integer.toString(4,2) + "最低位往左走第一次出现1位之前有" + i2 + "个0");
        System.out.println(Integer.toString(3,2) + "最低位往左走第一次出现1位之前有" + i3 + "个0");
        // 将一个字符串整数转为整数
        int i4 = Integer.parseInt("213");
        System.out.println("字符串‘213’转为整数是：" + i4);
        // 将一个字符串数，按照radix来转为十进制整数
        int i5 = Integer.parseInt("AE", 16);
        int i5_2 = Integer.parseInt("AE", 15);
        System.out.println("指定为16进制的‘AE’转为十进制等于：" + i5);
        System.out.println("指定为15进制的‘AE’转为十进制等于：" + i5_2);
        // 注意下面i6、i7方法不能传递负数字符串
        int i6 = Integer.parseUnsignedInt("235");
        System.out.println("字符串‘235’转为无符号整数是：" + i6);
        int i7 = Integer.parseUnsignedInt("AE", 16);
        System.out.println("指定为16进制的‘AE’转为无符号十进制等于：" + i7);
        // 求余数
        int i8 = Integer.remainderUnsigned(5, 2);
        System.out.println("5/2的余数（转为无符号）是：" + i8);
        // 按位反转
        // 0000 0010 --> 0100 0000
        int reverse = Integer.reverse(2);
        System.out.println("2(00000000000000000000000000000" + Integer.toString(2, 2) + ")按位取反="
                + reverse + "(0" + Integer.toString(reverse, 2) + ")");
        // 按字节反转 00000000 00000000 00000000 00000010 --> 00000010 00000000 00000000 00000000
        int i9 = Integer.reverseBytes(2);
        System.out.println("2(000000000100000000000000000000" + Integer.toString(2, 2) + ")按字节（8位）取反="
                + reverse + "(000000" + Integer.toString(i9, 2) + ")");
        // // TODO: 2022/7/2 未知API
        int i10 = Integer.rotateLeft(108, 3);
        int i11 = Integer.rotateRight(108, 3);

        // 判断一个数是正数（返回1），0（返回0），负数（返回-1）
        int signum = Integer.signum(23);
        System.out.println("23的符号位是" + signum + ",代表是个正数");
        // 两数相加
        int sum = Integer.sum(23, 25);
        System.out.println("23 + 25 = " + sum);
        // 转为二进制字符串
        String s = Integer.toBinaryString(52);
        System.out.println("52转为二进制字符串" + s);
        // 转为16进制字符串
        String s1 = Integer.toHexString(52);
        System.out.println("52转为16进制字符串" + s1);
        // 转为8进制字符串
        String s2 = Integer.toOctalString(52);
        System.out.println("52转为8进制字符串" + s2);
        // 将一个整数，按照radix来转为对应的字符串
        String s3 = Integer.toString(52);
        System.out.println("将整数52搞成字符串'" + s3 + "'");
        String s4 = Integer.toString(52, 16);
        System.out.println("将整数52搞成16进制数的字符串形式'" + s4 + "'");
        long l = Integer.toUnsignedLong(-52);
        System.out.println("将整数-52无符号的long类型：" + l);
        String s5 = Integer.toUnsignedString(-52);
        System.out.println("将整数52搞成无符号整数字符串'" + s5 + "'");
        String s6 = Integer.toUnsignedString(-52, 16);
        System.out.println("将整数52搞成16进制无符号整数的字符串形式'" + s6 + "'");
        Integer integer1 = Integer.valueOf("5F", 16);
        System.out.println("5F按照16进制的格式进行解析成10进制，结果是" + integer1);
    }

    public void doublePackageTypeAPIs(){

    }

    /**
     * 包装器类的常量字段&包装器类对象方法
     */
    public void packageTypeAPIShow() {
        Integer integer = 50;
        System.out.println("--> 2.1.1 包装器类可以直接转换为其他类型数据，通过XXXValue() <--");
        System.out.println("Integer转成Short：" + integer.shortValue());
        System.out.println("Integer转成Long：" + integer.longValue());
        System.out.println("Integer转成Float：" + integer.floatValue());
        System.out.println("Integer转成Double：" + integer.doubleValue());
        System.out.println("=================================================");
        System.out.println();
        System.out.println("--> 2.1.2 包装器类提供了很多静态方法，如： <--");
        System.out.println("Java平台中Byte类型占用" + (Byte.SIZE / 8) + "字节，即" + Byte.SIZE + "位");
        System.out.println("Java平台中Integer类型占用" + (Integer.SIZE / 8) + "字节，即" + Integer.SIZE + "位");
        System.out.println("Java平台中Short类型占用" + (Short.SIZE / 8) + "字节，即" + Short.SIZE + "位");
        System.out.println("Java平台中Long类型占用" + (Long.SIZE / 8) + "字节，即" + Long.SIZE + "位");
        System.out.println("Java平台中Character类型占用" + (Character.SIZE / 8) + "字节，即" + Character.SIZE + "位");
        System.out.println("Java平台中Float类型占用" + (Float.SIZE / 8) + "字节，即" + Float.SIZE + "位");
        System.out.println("Java平台中Double类型占用" + (Double.SIZE / 8) + "字节，即" + Double.SIZE + "位");
        System.out.println("=================================================");
        System.out.println();
        System.out.println("--> 2.1.3 各大基本类型的最大最小值，如： <--");
        System.out.println("Java平台中Integer最大值：" + Integer.MAX_VALUE);
        System.out.println("Java平台中Integer最小值：" + Integer.MIN_VALUE);
        System.out.println("Java平台中Double最大值：" + Double.MAX_VALUE);
        System.out.println("Java平台中Double最小值：" + Double.MIN_VALUE);
        System.out.println("Java平台中Float最大值：" + Float.MAX_VALUE);
        System.out.println("Java平台中Float最小值：" + Float.MIN_VALUE);
        System.out.println("Java平台中Short最大值：" + Short.MAX_VALUE);
        System.out.println("Java平台中Short最小值：" + Short.MIN_VALUE);
        System.out.println("Java平台中Byte最大值：" + Byte.MAX_VALUE);
        System.out.println("Java平台中Byte最小值：" + Byte.MIN_VALUE);
        System.out.println("Java平台中Character最大值：" + Character.MAX_VALUE);
        System.out.println("Java平台中Character最小值：" + Character.MIN_VALUE);
        System.out.println("Java平台中Long最大值：" + Long.MAX_VALUE);
        System.out.println("Java平台中Long最小值：" + Long.MIN_VALUE);
        System.out.println("=================================================");
        System.out.println();
        System.out.println("--> 2.1.4 包装器类也能够获取原始类型，如： <--");
        System.out.println("Integer的原始类型：" + Integer.TYPE);
        System.out.println("Double的原始类型：" + Double.TYPE);
        System.out.println("Short的原始类型：" + Short.TYPE);
        System.out.println("Byte的原始类型：" + Byte.TYPE);
        System.out.println("Long的原始类型：" + Long.TYPE);
        System.out.println("Character的原始类型：" + Character.TYPE);
        System.out.println("Float的原始类型：" + Float.TYPE);
        System.out.println("Boolean的原始类型：" + Boolean.TYPE);
        System.out.println("=================================================");
        System.out.println();
        System.out.println("--> 2.1.5 包装器其他常量，如： <--");
        System.out.println(Float.MAX_EXPONENT);
        System.out.println(Float.MIN_EXPONENT);
        System.out.println(Float.MIN_NORMAL);
        System.out.println(Float.NaN);
        System.out.println(Float.NEGATIVE_INFINITY);
        System.out.println(Float.POSITIVE_INFINITY);

        System.out.println(Double.MAX_EXPONENT);
        System.out.println(Double.MIN_EXPONENT);
        System.out.println(Double.MIN_NORMAL);
        System.out.println(Double.NaN);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.out.println(Double.POSITIVE_INFINITY);

        System.out.println(Character.MAX_RADIX);
        System.out.println(Character.MIN_RADIX);
    }

    /**
     * 自动拆箱演示
     */
    public void packageTypeOutBox() {
        Integer f = 39;
        int f2 = f;
        System.out.println("拆箱结果-变量f2=" + f2);
    }

    /**
     * 自动装箱演示
     */
    public void packageTypeInBox() {
        int f2 = 58;
        Integer f = f2;
        System.out.println("装箱结果-变量f=" + f);
    }

    /**
     * null不能拆箱
     */
    public void nullPackageTypeCastPrimitiveType() {
        Integer f = null;
        int f2 = f;
    }



    public static void main(String[] args) {
        PackageNumberClass numberClass = new PackageNumberClass();
        numberClass.NumberPackageClassDemo();
    }
}
