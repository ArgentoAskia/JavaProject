package Java08.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 随机数工具类
 * @author Askia
 * @since 1.0
 * @version 1.4
 */
public class RandomUtil {

    public static void main(String[] args) {
        int i = RandomUtil.randomInt(50, 5000);
        String s1 = RandomUtil.randomPhoneNumber();
        System.out.println(s1);
//        for (int j = 0; j < 1000; j++) {
//            int i1 = randomInt();
//            System.out.println(i1);
//        }
//        for (int j = 0; j < 1000; j++) {
//            // HH: 24小时制，hh：12小时制
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = randomDateTime();
//            String format = simpleDateFormat.format(date);
//            System.out.println(format);
//        }
        String s = randomPhoneNumber();
        System.out.println(s);
    }

    private static final Random random = new Random();

    /**
     * 生成随机整数值(integer), 范围在 {@link Integer#MIN_VALUE}和 {@link Integer#MAX_VALUE}之间
     *
     * @return 整数范围的随机数
     * @since 1.0
     */
    public static int randomInt() {
        return random.nextInt();
    }

    /**
     * 在范围 {@code low} - {@code high} 之间生成随机数，生成范围包括上下限边界值，
     * 如 {@code RandomUtil.randomInt(3, 5)}则可能生成的数字范围是: [3, 4, 5]
     *
     * @param low 生成范围下限
     * @param high 生成范围上限
     * @return 范围内的整型随机数
     * @since 1.0
     */
    public static int randomInt(int low, int high) {
        if (low > high){
            throw new InvalidParameterException("low > high");
        }
        int bound = high - low + 1;
        int boundGap = random.nextInt(bound);
        return low + boundGap;
    }

    /**
     * 在范围 {@code low} - {@code high} 之间生成随机数,增加了两个额外参数来决定是否包含上下限边界值，
     * 如：{@code RandomUtil.randomInt(3, 8, false, false)}则生成的数字范围在：[4, 5, 6, 7]
     *
     * @param low 生成范围下限
     * @param high 生成范围上限
     * @param includeLow 是否包含下限
     * @param includeHigh 是否包含上限
     * @return 范围内的整型随机数
     * @since 1.4
     * @see RandomUtil#randomInt(int, int)
     */
    public static int randomInt(int low, int high, boolean includeLow, boolean includeHigh){
        if (!includeHigh)   high--;
        if (!includeLow)    low++;
        return randomInt(low, high);
    }

    /**
     * 生成从 {@code 0} - {@code bound} 之间的随机数，第二个参数指定生成的数字是否包含 {@code bound}
     *
     * @param bound 随机数范围上限
     * @param includeBound {@code true}包含 {@code bound}, {@code false}则不包含 {@code bound}
     * @return 范围 {@code 0 - bound} 内的整型随机数
     * @since 1.0
     */
    public static int randomInt(int bound, boolean includeBound){
        if (includeBound){
            return random.nextInt(bound + 1);
        }else {
            return random.nextInt(bound);
        }
    }

    /**
     * 随机个位数，即生成从0-9之间的数字
     * @return 0-9之间的个位数
     * @since 1.4
     */
    public static int randomSingleDigit(){
        int i = random.nextInt(10);
        return i;
    }

    /**
     * 生成随机双精度浮点数(double), 范围在 {@link Double#MIN_VALUE}和 {@link Double#MAX_VALUE}之间
     *
     * @return 双精度浮点数范围的随机数
     * @since 1.0
     */
    public static double randomDouble() {
        return random.nextDouble();
    }

    /**
     * 生成随机短整型(short), 范围在 {@link Short#MIN_VALUE}和 {@link Short#MAX_VALUE}之间
     *
     * <p>
     * <strong>
     * 注意该方法的生成方式基于 {@link Random#nextInt(int)} 方法，通过生成短整型数字的正数范围，然后通过
     * {@link Random#nextBoolean()}的方式来决定是正数还是负数。
     * </strong>
     *
     * @return 短整型随机数
     * @since 1.0
     */
    public static short randomShort() {
        int i = random.nextInt(Short.MAX_VALUE + 2);
        if (i == (Short.MAX_VALUE + 1) || !random.nextBoolean()) {
            i = -i;
        }
        String s = String.valueOf(i);
        return Short.parseShort(s);
    }

    /**
     * 生成随机字节(byte), 范围在 {@link Byte#MIN_VALUE}和 {@link Byte#MAX_VALUE}之间
     *
     * <p>
     * <strong>生成方法参考 {@link RandomUtil#randomShort()}</strong>
     *
     * @return 单字节范围的随机数
     * @see RandomUtil#randomShort()
     * @since 1.0
     */
    public static byte randomByte() {
        int i = random.nextInt(Byte.MAX_VALUE + 2);
        if (i == (Byte.MAX_VALUE + 1) || !random.nextBoolean()) {
            i = i * -1;
        }
        String s = String.valueOf(i);
        return Byte.parseByte(s);
    }

    /**
     * 生成随机长整型数字(long), 范围在 {@link Long#MIN_VALUE}和 {@link Long#MAX_VALUE}之间
     *
     * @return 长整型范围的随机数
     * @since 1.0
     */
    public static long randomLong() {
        return random.nextLong();
    }

    /**
     * 生成随机单精度浮点型数字(float), 范围在 {@link Float#MIN_VALUE}和 {@link Float#MAX_VALUE}之间
     *
     * @return 单精度浮点型随机数
     * @since 1.0
     */
    public static float randomFloat() {
        return random.nextFloat();
    }

    /**
     * 随机true、false值。赌狗快乐二选一。
     *
     * @return true or false
     * @since 1.0
     */
    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * 生成随机的日期+时间，范围在 {@code 1946-2-14 00:00:00} - {@link Calendar#getInstance()}。
     *
     * @return 日期对象 {@link Date}
     * @since 1.2
     */
    public static Date randomDateTime() {
        // 1.获取当前时间
        GregorianCalendar current = new GregorianCalendar();
        // 2.随机1946-当前时间的年份
        int year = randomInt(1946, current.get(Calendar.YEAR));
        // 3.随机月份
        int month = randomInt(1, 12);
        int dayOfMonth = 1;
        int hour = randomInt(0, 23);
        int minute = randomInt(0, 59);
        int second = randomInt(0, 59);
        // 4.判断月份是否闰年
        boolean leapYear = current.isLeapYear(year);

        // 5.修正年份对应月份的范围
        if (year == 1946) {
            month = randomInt(2, 12);
            if (month == 2) {
                dayOfMonth = randomInt(14, 28);
                current.set(year, month - 1, dayOfMonth, hour, minute, second);
                return current.getTime();
            }
        } else if (year == current.get(Calendar.YEAR)) {
            int currentMonth = current.get(Calendar.MONTH) + 1;
            month = randomInt(1, currentMonth);
            if (month == currentMonth) {
                int dayOfNow = current.get(Calendar.DAY_OF_MONTH);
                dayOfMonth = randomInt(1, dayOfNow);
                hour = randomInt(0, current.get(Calendar.HOUR_OF_DAY));
                minute = randomInt(0, current.get(Calendar.MINUTE));
                second = randomInt(0, current.get(Calendar.SECOND));
                current.set(year, month - 1, dayOfMonth, hour, minute, second);
                return current.getTime();
            }
        }
        // 6.随机月日期
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                dayOfMonth = randomInt(1, 31);
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                dayOfMonth = randomInt(1, 30);
                break;
            }
            case 2: {
                if (leapYear) {
                    dayOfMonth = randomInt(1, 29);
                } else {
                    dayOfMonth = randomInt(1, 28);
                }
                break;
            }
        }
        current.set(year, month - 1, dayOfMonth, hour, minute, second);
        return current.getTime();
    }

    /**
     * 随机Unicode字符
     *
     * @return Unicode字符
     * @since 1.2
     */
    public static char randomUnicode() {
        int i = random.nextInt(Short.MAX_VALUE + 1);
        char a = (char) i;
        System.out.println(a);
        return a;
    }

    /**
     * 随机ASCII字符
     *
     * @return ASCII字符，可转为byte
     * @since 1.0
     */
    public static char randomASCII() {
        int i = random.nextInt(Byte.MAX_VALUE + 1);
        return (char) i;
    }


    /**
     * 无符号的字节型，范围在0-255
     * @return 无符号字节
     * @since 1.3.1
     * @update 2022.12.28 - 1
     */
    public static int randomUnsignedByte(){
        int i = random.nextInt(256);
        return i;
    }

    /**
     * 生成随机字节数组。
     *
     * @param length 数组长度
     * @return 字节数组
     * @since 1.0
     */
    public static byte[] randomByteArray(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 随机无符号字节数组。
     * @param length
     * @return
     * @since 1.3.1
     */
    public static int[] randomUnsignedByteArray(int length){
        int[] bytes = new int[length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = randomUnsignedByte();
        }
        return bytes;
    }

    /**
     * 生成包装器类型的
     * @param length
     * @return
     * @since 1.3.1
     * @update 2022.12.28 - 1
     */
    public static Byte[] randomByteWrapperTypeArray(int length){
        Byte[] bytes = new Byte[length];
        for (int i = 0; i < bytes.length; i++) {
            byte b = randomByte();
            bytes[i] = b;
        }
        return bytes;
    }

    /**
     * 生成随机整型数组
     *
     * @param length 数组长度
     * @return 整型数组
     * @since 1.0
     * @update 2022.12.28 - 2
     */
    public static int[] randomIntArray(int length) {
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            int i1 = randomInt();
            ints[i] = i1;
        }
        return ints;
    }

    /**
     * 随机Integer数组
     * @param length
     * @return
     * @since 1.3.1
     */
    public static Integer[] randomIntWrapperTypeArray(int length){
        Integer[] integers = new Integer[length];
        for (int i = 0; i< integers.length; i++){
            int integer = randomInt();
            integers[i] = integer;
        }
        return integers;
    }


    /**
     * 生成随机ASCII字符数组。
     *
     * @param length 数组长度
     * @return ASCII字符数组
     * @since 1.0
     */
    public static char[] randomASCIIArray(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = randomASCII();
        }
        return chars;
    }

    /**
     * 生成随机Unicode字符数组
     *
     * @param length  数组长度
     * @return unicode字符数组
     * @since 1.0
     */
    public static char[] randomUnicodeArray(int length) {
        char[] unicodes = new char[length];
        for (int i = 0; i < unicodes.length; i++) {
            unicodes[i] = randomUnicode();
        }
        return unicodes;
    }

    /**
     * 数组随机选择。随机选择一位幸运成员
     * @param cases 选择源
     * @param <T> 参数只要引用类型
     * @return 幸运成员
     * @see #randomSwitch(String)
     * @see #randomSwitch(int[])
     * @see #randomSwitch(short[])
     * @see #randomSwitch(byte[])
     * @see #randomSwitch(long[])
     * @see #randomSwitch(float[])
     * @see #randomSwitch(double[])
     * @see #randomSwitch(char[])
     * @since 1.2
     */
    public static <T> T randomSwitch(T[] cases){
        int index = random.nextInt(cases.length);
        return cases[index];
    }

    /**
     *
     * @param intCases 整数数组
     * @return 随机成员
     * @see #randomSwitch(Object[])
     * @see #randomSwitch(short[])
     * @see #randomSwitch(byte[])
     * @see #randomSwitch(long[])
     * @see #randomSwitch(float[])
     * @see #randomSwitch(double[])
     * @see #randomSwitch(char[])
     * @since 1.2
     */
    public static int randomSwitch(int[] intCases){
        int index = random.nextInt(intCases.length);
        return intCases[index];
    }
    public static short randomSwitch(short[] shortCases){
        int index = random.nextInt(shortCases.length);
        return shortCases[index];
    }
    public static byte randomSwitch(byte[] byteCases){
        int index = random.nextInt(byteCases.length);
        return byteCases[index];
    }
    public static long randomSwitch(long[] longCases){
        int index = random.nextInt(longCases.length);
        return longCases[index];
    }
    public static float randomSwitch(float[] floatCases){
        int index = random.nextInt(floatCases.length);
        return floatCases[index];
    }
    public static double randomSwitch(double[] doubleCases){
        int index = random.nextInt(doubleCases.length);
        return doubleCases[index];
    }
    public static char randomSwitch(char[] charCases){
        int index = random.nextInt(charCases.length);
        return charCases[index];
    }
    public static char randomSwitch(String str){
        char[] chars = str.toCharArray();
        return randomSwitch(chars);
    }

    /*
     * 三大运营商电话开头，
     * 参考：https://baike.baidu.com/item/%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81/1417271?fr=aladdin
     */
    private static final String[] NUM_SEG_CMCC =
            {
                    "134", "135", "136", "137", "138", "139", "147", "150",
                    "151", "152", "157", "158", "159", "172", "178",
                    "182", "183", "184", "187", "188", "195", "197",
                    "198"
            };
    private static final String[] NUM_SEG_CT =
            {
                    "133", "149", "153", "180", "181", "189", "173",
                    "177", "190", "191", "193", "199"
            };

    private static final String[] NUM_SEG_CU =
            {
                    "130", "131", "132", "145",
                    "155", "156", "166", "175",
                    "176", "185", "186", "196"
            };

    /**
     * 生成随机手机号码，忽略手机运营商归属
     * @return 11位手机号
     * @since 1.2
     */
    public static String randomPhoneNumber(){
        int[] gaps = {-1, 0, 1};
        int luckyMember = randomSwitch(gaps);
        return randomPhoneNumber(luckyMember);
    }

    /**
     * 生成随机的11位手机号码。
     *
     * @param numberSegment 手机号码号段，大于0是移动号段，等于0是电信号段，小于0是联通号段
     * @return 11位手机号
     * @since 1.2
     */
    public static String randomPhoneNumber(int numberSegment){
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber.append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10));
        if(numberSegment > 0){
            int cmccIndex = random.nextInt(NUM_SEG_CMCC.length);
            phoneNumber.insert(0, NUM_SEG_CMCC[cmccIndex]);
        }else if (numberSegment == 0){
            int ctIndex = random.nextInt(NUM_SEG_CT.length);
            phoneNumber.insert(0, NUM_SEG_CT[ctIndex]);
        }else{
            int cuIndex = random.nextInt(NUM_SEG_CU.length);
            phoneNumber.insert(0, NUM_SEG_CU[cuIndex]);
        }
        return phoneNumber.toString();
    }

    /**
     * 列表的创建类型
     * @author Askia
     * @since 1.2 2022.9.15
     */
    public enum ListType{
        ArrayList, LinkedList
    }

    /**
     * 随机生成一个列表
     * @param memberSize
     * @param supplierFunction 成员发生器，方法通过这个函数式接口对象来创建List的成员
     * @param listType
     * @param <T>
     * @return
     * @since 1.2
     */
    public static <T> List<T> randomList(int memberSize,
                                         Supplier<T> supplierFunction,
                                         ListType listType){
        List<T> list = null;
        if (listType == ListType.ArrayList){
            list = new ArrayList<>();
        }else{
            list = new LinkedList<>();
        }
        for (int i = 0; i < memberSize; i++) {
            T t = supplierFunction.get();
            list.add(t);
        }
        return list;
    }

    /**
     * 
     * @param memberSize
     * @return
     * @since 1.3.1
     */
    public static List<String> randomStringList(int memberSize){
        boolean b = random.nextBoolean();
        Supplier<String> stringSupplier = () -> {
            char[] chars = randomASCIIArray(randomInt(5, 50));
            String s = String.valueOf(chars);
            return s;
        };
        return b? randomList(memberSize, stringSupplier, ListType.ArrayList):
                  randomList(memberSize, stringSupplier, ListType.LinkedList);
    }

    /**
     * 
     * @param memberSize
     * @return
     * @since 1.3
     */
    public static List<Date> randomDateList(int memberSize){
        Supplier<Date> dateSupplier = () -> {
            Date date = randomDateTime();
            return date;
        };
        return randomList(memberSize, dateSupplier, ListType.ArrayList);
    }

    // method is not support array
    @Deprecated
    public static <T> T randomObject(Class<T> objectType, Function<Random, ?> unknownTypeCreator) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 先创建对象
        T t = objectType.getConstructor().newInstance();
        // 获取类型的所有属性
        Field[] declaredFields = objectType.getDeclaredFields();
        // 循环填充值
        for (Field f :
                declaredFields) {
            Class<?> type = f.getType();
            Class<?> componentType = type.getComponentType();
            if (componentType != null){

            }
            if (type == Integer.class || type == int.class){
                int i = randomInt();
                f.setInt(t, i);
                continue;
            }
            if (type == Short.class || type == short.class){
                short i = randomShort();
                f.setShort(t, i);
                continue;
            }
            if (type == Long.class || type == long.class){
                long l = randomLong();
                f.setLong(t, l);
                continue;
            }
            if(type == Byte.class || type == byte.class){
                f.setByte(t, randomByte());
                continue;
            }
            if(type == Character.class || type == char.class){
                f.setChar(t, randomUnicode());
                continue;
            }
            if (type == Boolean.class || type == boolean.class){
                f.setBoolean(t, randomBoolean());
                continue;
            }
            if (type == Float.class || type == float.class){
                f.setFloat(t, randomFloat());
                continue;
            }
            if (type == Double.class || type == double.class){
                f.setDouble(t, randomDouble());
                continue;
            }
            if (type == Date.class){
                Date date = randomDateTime();
                f.set(t, date);
                continue;
            }
            if (type == String.class){
                char[] chars = randomASCIIArray(randomInt(10, 30));
                String s = String.valueOf(chars);
                f.set(t, s);
                continue;
            }
            if (unknownTypeCreator != null){
                Object apply = unknownTypeCreator.apply(random);
                f.set(t, f);
            }else{
                Object o = randomObject(f.getType(), null);
                f.set(t, o);
            }
        }
        return t;
    }

    /**
     * 生成随即长度的英文字母字符数组，数组长度范围在{@code 5 - 255}之间.
     *
     * @return 英文字母字符数组
     * @since 1.4
     * @see RandomUtil#randomAlphabets(int, boolean)
     */
    public static char[] randomAlphabets(){
        return randomAlphabets(randomInt(5, 255), false);
    }

    /**
     * 生成随即长度的英文字母字符数组。
     * 第一个参数可以指定生成的长度，
     * 第二个参数指定随机生成的字母是否只有小写。
     *
     * @param length 生成的英文字母字符长度，值必须大于{@code 0}
     * @param onlyLower {@code true}则生成的字母只有小写字母，{@code false}则生成的字母包含大小写
     * @return 英文字母字符数组
     * @since 1.4
     * @see RandomUtil#randomAlphabets()
     */
    public static char[] randomAlphabets(int length, boolean onlyLower){
        char[] alphabets = new char[length];
        if (onlyLower){
            for (int i = 0; i < length; i++){
                int alphabet = 0;
                alphabet = randomInt(97, 122);
                alphabets[i] = (char) alphabet;
            }
        }else{
            for (int i = 0; i < length; i++) {
                boolean flag = randomBoolean();
                int alphabet = 0;
                if (flag) alphabet = randomInt(65, 90);
                else      alphabet = randomInt(97, 122);
                alphabets[i] = (char) alphabet;
            }
        }
        return alphabets;
    }

    /**
     * 生成英文字母+数字字符组合。
     *
     * @param length 生成的英文字母+数字字符的长度，值必须大于{@code 0}
     * @param onlyLower {@code true}则生成的字母只有小写字母，{@code false}则生成的字母包含大小写
     * @return 英文字母+数字字符数组
     * @since 1.4
     */
    public static char[] randomAlphabetsNumbers(int length, boolean onlyLower){
        char[] alphabetsNumbers = new char[length];
        if (onlyLower){
            for (int i = 0; i < length; i++) {
                boolean flag = randomBoolean();
                int alphabetOrNumber = 0;
                if (flag)       alphabetOrNumber = randomInt(97, 122);
                else            alphabetOrNumber = randomInt(48, 57);
                alphabetsNumbers[i] = (char) alphabetOrNumber;
            }
        }else{
            for (int i = 0; i < length; i++) {
                int flag = randomInt(-1, 1);
                int alphabetOrNumber = 0;
                if (flag > 0)       alphabetOrNumber = randomInt(65, 90);
                else if (flag < 0)  alphabetOrNumber = randomInt(97, 122);
                else                alphabetOrNumber = randomInt(48, 57);
                alphabetsNumbers[i] = (char) alphabetOrNumber;
            }
        }
        return alphabetsNumbers;
    }


}
