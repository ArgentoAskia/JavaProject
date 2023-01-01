package cn.argentoaskia.demos;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * 测试类：{@link java.lang.reflect.Array}
 * 说明：反射包中Array类的基本API使用
 * 测试方法命名规则：test+类名+方法名
 * 方法的说明采用javadoc解释
 *
 * @author Askia
 */
public class ArrayDemos {

    /**
     * 方法：<br>
     * {@link java.lang.reflect.Array#newInstance(Class, int)}<br>
     * {@link java.lang.reflect.Array#newInstance(Class, int...)}<br>
     * <p>
     * 说明：<br>
     * 方法1用于创建一个动态的数组对象，你可以传class来指定数组原始类型，后面的int指代数组的长度<br>
     * 方法2用于创建多维度数组，第一个参数相同，第二个参数指定每个维度的长度。
     */
    @Test
    public void testArrayNewInstance() {
        // 1.创建int[]
        System.out.println("创建一个新的int[5]，名字是o");
        Object o = Array.newInstance(int.class, 5);
        String s = Arrays.toString((int[]) o);
        System.out.println("数组o的成员：" + s);
        // 2.创建由5个int[]组成的数组，也就是int[5][]
        // 在二维数组中，一般int[行数][列数]
        // 像int[5][]的数组，列数是不确定的，也就是说后期可以赋值5个不同长度的int[]给int[5][]
        // 说到底还是指针（引用）
        System.out.println("创建一个新的int[5][]，名字是ints");
        Object o2 = Array.newInstance(int[].class, 5);
        int[][] ints = (int[][]) o2;
        System.out.println("int[][] ints的长度" + ints.length);
        // 因为还没指定具体的int[]，所以一开始输出全是null
        // Object[] 能够接受任何二位数组类型，如int[][]，Integer[][]
        String s1 = Arrays.toString(ints);
        System.out.println("初始化int[][] ints的值是：" + s1);

        System.out.println("填充int[][] ints");
        // 填充int[][] ints
        int[] a = {12, 23, 22};
        int[] b = {44, 54, 545, 444, 54, 5454};
        ints[0] = a;
        ints[1] = b;
        String s2 = Arrays.toString(ints);
        System.out.println("现在int[][] ints的值是：" + s2);

        // TODO: 2022/9/21  homework-扩展方法，循环遍历打印不同维度所有数组
        System.out.println("翻译成人话就是：[");
        for (int[] numbers : ints
        ) {
            System.out.println("   " + Arrays.toString(numbers) + ",");
        }
        System.out.println("]");

    }

    /**
     * 方法：<br>
     * {@link java.lang.reflect.Array#set(Object, int, Object)}<br>
     * {@link java.lang.reflect.Array#setBoolean(Object, int, boolean)}<br>
     * {@link java.lang.reflect.Array#setByte(Object, int, byte)}<br>
     * {@link java.lang.reflect.Array#setChar(Object, int, char)}<br>
     * {@link java.lang.reflect.Array#setDouble(Object, int, double)}<br>
     * {@link java.lang.reflect.Array#setFloat(Object, int, float)}<br>
     * {@link java.lang.reflect.Array#setInt(Object, int, int)}<br>
     * {@link java.lang.reflect.Array#setLong(Object, int, long)}<br>
     * {@link java.lang.reflect.Array#setShort(Object, int, short)}<br>
     * <p>
     * 说明：<br>
     * 上面的方法都是给某个数组赋值，其中参数{@code Object}是数组任意的数组对象，
     * 参数{@code int}代表下标，最后一个参数代表要赋予的值
     */
    @Test
    public void testArraySetXXX() {
        // 1.创建int[]
        System.out.println("创建一个新的int[5]，名字是o");
        Object o = Array.newInstance(int.class, 5);
        String s = Arrays.toString((int[]) o);
        System.out.println("数组o的成员：" + s);
        System.out.println("开始赋值");
        Array.set(o, 0, 2);
        Array.setInt(o, 1, 3);
        Array.setInt(o, 2, 4);
        Array.setInt(o, 4, 6);
        Array.setInt(o, 3, 5);
        String s2 = Arrays.toString((int[]) o);
        System.out.println("现在数组o的成员：" + s2);
    }

    /**
     * 方法：<br>
     * {@link java.lang.reflect.Array#get(Object, int)}}<br>
     * {@link java.lang.reflect.Array#getBoolean(Object, int)})}<br>
     * {@link java.lang.reflect.Array#getByte(Object, int)})}<br>
     * {@link java.lang.reflect.Array#getChar(Object, int)})}<br>
     * {@link java.lang.reflect.Array#getDouble(Object, int)}<br>
     * {@link java.lang.reflect.Array#getFloat(Object, int)} )}<br>
     * {@link java.lang.reflect.Array#getInt(Object, int)}<br>
     * {@link java.lang.reflect.Array#getLong(Object, int)} )}<br>
     * {@link java.lang.reflect.Array#getShort(Object, int)} )}<br>
     * {@link java.lang.reflect.Array#getLength(Object)} )}<br>
     * <p>
     * 说明：<br>
     * 上面的方法都是获取数组中某个值，其中参数{@code Object}是数组任意的数组对象，参数{@code int}代表下标
     * <br>
     * 最后一个方法获取数组长度
     */
    @Test
    public void testArrayGetXXX(){
        // 1.创建int[]
        System.out.println("创建一个新的int[5]，名字是o");
        Object o = Array.newInstance(int.class, 5);
        Array.set(o, 0, 2);
        Array.setInt(o, 1, 3);
        Array.setInt(o, 2, 4);
        Array.setInt(o, 4, 6);
        Array.setInt(o, 3, 5);
        System.out.println("数组o的长度：" + Array.getLength(o));
        System.out.println("第一个成员：" + Array.get(o, 0));
        System.out.println("第二个成员：" + Array.getInt(o, 1));
        System.out.println("第三个成员：" + Array.getInt(o, 2));
        System.out.println("第四个成员：" + Array.getInt(o, 3));
        System.out.println("第五个成员：" + Array.getInt(o, 4));
    }
}
