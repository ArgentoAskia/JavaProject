package cn.argentoaskia.demos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class A{
    public String name;
    private String name2;

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws Exception {
        A a = new A();
        Field name = a.getClass().getDeclaredField("name");
        Field name2 = a.getClass().getDeclaredField("name2");
        // 可以设置值，因为是public
        name.set(a, "123456");
        // 不能设置值，因为是private，检查不通过
        name2.set(a, "12345677");
        System.out.println("name的Accessible：" + name.isAccessible());
        System.out.println("name2的Accessible：" +name2.isAccessible());
        System.out.println(a.getName());
        System.out.println(a.getName2());
        // private的name字段，输出false;
        Method method = a.getClass().getDeclaredMethod("setName", String.class);
        // public的setName方法，输出仍然是false;
        System.out.println(method.isAccessible());
    }
}
class B{
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        A a = new A();
        Field name = a.getClass().getDeclaredField("name");
        Field name2 = a.getClass().getDeclaredField("name2");
        // 可以设置值，因为是public
        name.set(a, "123456");
        // 不能设置值，因为是private，检查不通过
        name2.set(a, "12345677");
        System.out.println("name的Accessible：" + name.isAccessible());
        System.out.println("name2的Accessible：" +name2.isAccessible());
        System.out.println(a.getName());
        System.out.println(a.getName2());
        // private的name字段，输出false;
        Method method = a.getClass().getDeclaredMethod("setName", String.class);
        // public的setName方法，输出仍然是false;
        System.out.println(method.isAccessible());
    }
}
