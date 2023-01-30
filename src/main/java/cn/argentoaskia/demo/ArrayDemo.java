package cn.argentoaskia.demo;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Java中动态数组的创建和获取
 */
public class ArrayDemo {
    public static void main(String[] args) {
        Object o = Array.newInstance(int[].class, 5);
        int[][] ints = (int[][])o;
        System.out.println(ints.length);
        System.out.println(Arrays.toString(ints[0]));
        System.out.println(Arrays.toString(ints[1]));
        System.out.println(Arrays.toString(ints[2]));
        System.out.println(Arrays.toString(ints[3]));
        System.out.println(Arrays.toString(ints[4]));
        int[] a = {12,23,22};
        int[] b = {44,54,545,444,54,5454};
        ints[0] = a;
        ints[1] = b;
        System.out.println(Arrays.toString(ints[0]));
        System.out.println(Arrays.toString(ints[1]));
        System.out.println(Arrays.toString(ints[2]));
        System.out.println(Arrays.toString(ints[3]));
        System.out.println(Arrays.toString(ints[4]));
    }
}
