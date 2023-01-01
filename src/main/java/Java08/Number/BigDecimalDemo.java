package Java08.Number;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

// https://blog.csdn.net/xieganyu3460/article/details/82720925
public class BigDecimalDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("=================================================");
        System.out.println("--> 1.1-采用参数为BigInteger的构造器创建对象 <--");
        BigInteger bigInteger = new BigInteger("256");
        // 256
        BigDecimal decimal = new BigDecimal(bigInteger);
        // 256 * 10^-10 = 2.56 * 10^-8 = 2.56 * E-8
        BigDecimal decimalWithScale = new BigDecimal(bigInteger, 10);
        System.out.println("decimal:" + decimal);
        System.out.println("decimalWithScale:" + decimalWithScale);
        System.out.println();

        System.out.println("--> 1.2-采用参数为Char[]、String的构造器创建对象 <--");
        BigDecimal strDecimal = new BigDecimal("-2356.2325212142");
        BigDecimal charsDecimal = new BigDecimal(new char[]{'2','4','.','5', '8', '9'});
        System.out.println(strDecimal);
        System.out.println(charsDecimal);
        System.out.println();

        System.out.println("--> 1.3-采用参数为double、int、long的构造器创建对象 <--");
        // 注意采用double创建BigDecimal，可能会出现不精确的情况，如传入参数2351.52154258
        // 实际上参数2351.52154258可能会被解析成2351.52154258000018671737052500247955322265625
        BigDecimal doubleDecimal = new BigDecimal(2351.52154258);
        BigDecimal intDecimal = new BigDecimal(235621);
        BigDecimal longDecimal = new BigDecimal(2222552212252125L);
        System.out.println(doubleDecimal);
        System.out.println(intDecimal);
        System.out.println(longDecimal);
        System.out.println();
        System.out.println("=================================================");
        System.out.println("--> 2.1基本运算方法 <--");
        // 256 + 2351.52154258000018671737052500247955322265625
        // 加法
        BigDecimal addResult = decimal.add(doubleDecimal);
        System.out.println("加法：" + addResult);
        // 减法
        BigDecimal subtract = longDecimal.subtract(intDecimal);
        System.out.println("减法：" + subtract);
        // 乘法
        BigDecimal multiply = strDecimal.multiply(charsDecimal);
        System.out.println("乘法：" + multiply);
        // 除法，不带round，不带round,注意要同精度（scale）
        BigDecimal decimalWithScale2 = new BigDecimal(new BigInteger(String.valueOf(2)), 8);
        BigDecimal divide = decimalWithScale.divide(decimalWithScale2);
        System.out.println("除法不带舍入：" + divide);
        // 除法，带round，为了防止无限循环，需要指定位的舍入
        // 例子：2356.2325212142   ÷   24.589 = 95.8246582298 6701370531538492822
        // ROUND_CEILING，当结果是负数的时候，舍入方式=ROUND_DOWN，当结果是正数的时候，舍入方式是ROUND_UP
        BigDecimal divide1 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_CEILING);
        // 和ROUND_CEILING模式相反。
        BigDecimal divide3 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_FLOOR);

        // 向下省略，如：95.8246582298 6701370531538492822 = 95.8246582298
        BigDecimal divide2 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_DOWN);
        // 向上省略，如：95.8246582298 6701370531538492822 = 95.8246582299
        BigDecimal divide5 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_UP);
        // 除数和被除数精度已经确定，肯定能除得尽，不是循环小数。
        BigDecimal divide4 = decimalWithScale.divide(decimalWithScale2, BigDecimal.ROUND_UNNECESSARY);

        // 四舍五入，不包括五，省略位第一位需要大于5，如：
        // 95.8246582298 6701370531538492822中，省略位的第一位是6，因为6>5，所以四舍五入到95.8246582299
        BigDecimal divide6 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_HALF_DOWN);
        // 四舍五入，包括五，省略位第一位需要大于等于5，如：
        // 95.8246582298 6701370531538492822中，省略位的第一位是6，因为6>=5，所以四舍五入到95.8246582299
        BigDecimal divide8 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_HALF_UP);
        // 奇偶数四舍五入，看省略位第一位是奇数还是偶数，如果是偶数，则采用ROUND_HALF_UP，如果是奇数则采用ROUND_HALF_DOWN
        // 95.8246582298 6701370531538492822中，省略位的第一位是6，6是偶数，所以使用则采用ROUND_HALF_UP
        BigDecimal divide7 = strDecimal.divide(charsDecimal, BigDecimal.ROUND_HALF_EVEN);
        System.out.println("除法ROUND_CEILING舍入：" + divide1);
        System.out.println("除法ROUND_DOWN舍入：" + divide2);
        System.out.println("除法ROUND_FLOOR舍入：" + divide3);
        System.out.println("除法ROUND_UNNECESSARY舍入：" + divide4);
        System.out.println("除法ROUND_UP舍入：" + divide5);
        System.out.println("除法ROUND_HALF_DOWN舍入：" + divide6);
        System.out.println("除法ROUND_HALF_EVEN舍入：" + divide7);
        System.out.println("除法ROUND_HALF_UP舍入：" + divide8);

        // 除法整数部分 + 余数
        //  24.589 * 95 = 2335.955 | 2356.2325212142 - 2335.955 = 20.2775212142
        BigDecimal[] bigDecimals = strDecimal.divideAndRemainder(charsDecimal);
        System.out.println("除法除数：" + bigDecimals[0]);
        System.out.println("除法余数：" + bigDecimals[1]);

        // 除法整数部分
        BigDecimal decimal1 = strDecimal.divideToIntegralValue(charsDecimal);
        System.out.println("除法整数部分：" + decimal1);

        // 取余数
        BigDecimal decimal2 = strDecimal.remainder(charsDecimal);
        System.out.println("余数部分：" + decimal2);

        // 幂运算
        BigDecimal pow = strDecimal.pow(5);
        System.out.println("2356.2325212142^5=" + pow);
        System.out.println();
        System.out.println("=================================================");

        System.out.println("--> 3.1 比较、绝对值、除法、正负号等 <--");
        // 绝对值
        BigDecimal abs = strDecimal.abs();
        System.out.println("strDecimal绝对值：" + abs);

        // 更加准确的比较
        BigDecimal bigDecimal = new BigDecimal("2356.2325212142000000012002122258552");
        System.out.println("2356.2325212142000000012002122258552 == 2356.2325212142 ? " + bigDecimal.equals(abs));
        System.out.println("2356.2325212142000000012002122258552 == 2356.2325212142 ? " + bigDecimal.compareTo(abs));

        // 比较大小
        System.out.println("max(2356.2325212142000000012002122258552 ,2356.2325212142) = " + bigDecimal.max(abs));
        System.out.println("min(2356.2325212142000000012002122258552 ,2356.2325212142) = " + bigDecimal.min(abs));

        // 小数点左移5位
        System.out.println("小数点左移5位：" + bigDecimal.movePointLeft(5));
        // 小数点右移10位
        System.out.println("小数点右移10位：" + bigDecimal.movePointRight(10));

        // 正负数切换
        BigDecimal negate = bigDecimal.negate();
        System.out.println("变成负数：" + negate);
        System.out.println("返回当前数字：" + strDecimal.plus());

        // 获取精度precision
        // 1.023123123131 = 0.1023123123131，小数最高精度有13位
        BigDecimal bigDecimal1 = new BigDecimal("1.023123123131");
        int precision = bigDecimal.precision();
        int precision1 = bigDecimal1.precision();
        System.out.println(precision);
        System.out.println(precision1);

        // 获取标度(scale)
        // 1023123123131 scale 12 = 1.023123123131
        // 23562325212142000000012002122258552 scale 31 = 2356.2325212142000000012002122258552
        int scale = bigDecimal1.scale();
        int scale1 = bigDecimal.scale();
        System.out.println(scale);
        System.out.println(scale1);

        // 2356.2325212142000000012002122258552 * 10^5 = 235623252.12142000000012002122258552
        BigDecimal bigDecimal2 = bigDecimal.scaleByPowerOfTen(5);
        System.out.println(bigDecimal2);

        // 获取符号
        int signum = bigDecimal.signum();
        System.out.println(signum);

        // 去除该小数的所有尾部0，如：
        // -123.22100000 = -123.221
        // -95.00000 = -95
        BigDecimal bigDecimal3 = decimal1.stripTrailingZeros();
        System.out.println(bigDecimal3);

        // 小数的有效位，如：
        // 25.562的有效位是0.001
        // -95.00000 = -0.000095 * 10 ^ 7= 1 * 10 ^ -7
        BigDecimal ulp1 = decimal1.ulp();
        BigDecimal ulp = charsDecimal.ulp();
        System.out.println(ulp);
        System.out.println(ulp1);
        System.out.println();
        System.out.println("=================================================");
        System.out.println("--> 4.1 转化类型的API <--");
        double v = strDecimal.doubleValue();
        int i = charsDecimal.intValue();
        int i1 = intDecimal.intValueExact();
        long l = strDecimal.longValue();
        long l1 = longDecimal.longValueExact();
        short i2 = strDecimal.shortValue();
        BigInteger bigInteger1 = strDecimal.unscaledValue();
        String s = strDecimal.toString();
        BigInteger bigInteger2 = strDecimal.toBigInteger();
        System.out.println("doubleValue:" + v);
        System.out.println("intValue:" + i);
        System.out.println("intValueExact:" + i1);
        System.out.println("longValue:" + l);
        System.out.println("longValueExact:" + l1);
        System.out.println("shortValue:" + i2);
        System.out.println("unscaledValue:" + bigInteger1);
        System.out.println("toString:" + s);
        System.out.println("toBigInteger:" + bigInteger2);
    }
}
