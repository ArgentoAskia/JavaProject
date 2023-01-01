package cn.argentoaskia.demo;

import java.io.EOFException;
import java.lang.annotation.Repeatable;
import java.lang.reflect.*;
import java.rmi.AccessException;
import java.sql.CallableStatement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class MethodDemo {
    private Integer aa;

    public Integer getAa() {
        return aa;
    }

    public MethodDemo setAa(Integer aa) {
        this.aa = aa;
        return this;
    }

    public <T> T getT(Class<T> tClass){
        try {
            return tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public <T extends Throwable> T getThrow() throws T{
        T exception;
        Random random = new Random();
        boolean b = random.nextBoolean();
        if (b){
            exception = (T) new EOFException("EOF");
        }else{
            exception = (T) new AccessException("access");
        }
        throw exception;
    }

    public int getInt(){
        return 0;
    }

    public int[] getInts(){
        return new int[5];
    }
    public void hello(){
        System.out.println("Hello!!!");
    }
    private void hello2(){
        System.out.println("Hello!!!");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        // Object value = abc.class.getMethod("value").getDefaultValue();
        // System.out.println(value);
        // MethodDemo methodDemo = new MethodDemo();
        // Field aa = methodDemo.getClass().getDeclaredField("aa");
        // aa.set(methodDemo, 123);
        // Object o = aa.get(methodDemo);
        // System.out.println(o);
        // System.out.println(methodDemo.aa);
        // System.out.println(aa.isAccessible());


        // 返回值是泛型时
        MethodDemo methodDemo2 = new MethodDemo();
        Method getT = methodDemo2.getClass().getDeclaredMethod("getT", Class.class);
        Method getT2 = methodDemo2.getClass().getDeclaredMethod("getT", Class.class);
        AnnotatedType annotatedReturnType = getT.getAnnotatedReturnType();
        AnnotatedType annotatedReturnType2 = getT2.getAnnotatedReturnType();
        System.out.println(annotatedReturnType.equals(annotatedReturnType2));
        System.out.println(annotatedReturnType.getType());

        Type genericReturnType = getT.getGenericReturnType();
        System.out.println(genericReturnType.getTypeName());
        Class<?> returnType = getT.getReturnType();
        System.out.println(returnType);


        // 基本类型
        Method getInt = methodDemo2.getClass().getDeclaredMethod("getInts");
        Class<?> returnType1 = getInt.getReturnType();
        Type genericReturnType1 = getInt.getGenericReturnType();
        AnnotatedType annotatedReturnType1 = getInt.getAnnotatedReturnType();
        System.out.println(returnType1.getTypeName());
        System.out.println(genericReturnType1);
        System.out.println(annotatedReturnType1.getType());

        Method getThrow = methodDemo2.getClass().getDeclaredMethod("getThrow");
        AnnotatedType[] annotatedExceptionTypes = getThrow.getAnnotatedExceptionTypes();
        Class<?>[] exceptionTypes = getThrow.getExceptionTypes();
        Type[] genericExceptionTypes = getThrow.getGenericExceptionTypes();
        System.out.println(Arrays.toString(annotatedExceptionTypes));
        System.out.println(Arrays.toString(exceptionTypes));
        System.out.println(Arrays.toString(genericExceptionTypes));

    }


}

@interface abc {
    int value() default 555;

    String str() default "123";

    Class<?> clazz() default abc.class;
}

//class MethodTotal{
//        public void main2(String[] args){
//        Method method = null;
//        // 获取相关方法属性
//        method.getAnnotatedExceptionTypes();
//        method.getExceptionTypes();
//        method.getGenericExceptionTypes();
//
//        // 获取返回值类型
//        method.getAnnotatedReturnType();
//        method.getGenericReturnType();
//        // 获取方法返回对象的类型
//        method.getReturnType();
//
//        method.getAnnotatedParameterTypes();
//        method.getAnnotatedReceiverType();
//
//        // 获取方法的权限级别
//        method.getModifiers();
//        // 获取方法名
//        method.getName();
//        // 获取参数总数
//        method.getParameterCount();
//        // 获取方法所有参数对象
//        method.getParameters();
//        // 获取方法所有参数对象的类型
//        method.getParameterTypes();
//
//
//        method.getTypeParameters();
//
//
//        // 判断某个注解是否注在该类上
//        method.isAnnotationPresent();
//        // 获取注解相关
//        method.getAnnotation();
//        method.getAnnotations();
//        method.getAnnotationsByType();
//        method.getDeclaredAnnotation();
//        method.getDeclaredAnnotations();
//        method.getDeclaredAnnotationsByType();
//        // 获取注解类中成员的默认值，提前是该类是一个注解
//        method.getDefaultValue();
//        // 获取所有注解在参数上的注解的类型
//        method.getParameterAnnotations();
//
//        // 获取定义该方法的类
//        method.getDeclaringClass();
//
//        // 调用特定对象的本方法
//        method.invoke();
//
//        // 打开访问级别
//        method.setAccessible();
//        method.isAccessible();
//
//        // 判断是否是桥方法
//        method.isBridge();
//        // 判断是否是默认方法
//        method.isDefault();
//        // 判断是否是同步方法
//        method.isSynthetic();
//        // 判断是否是可变参数方法
//        method.isVarArgs();
//
//
//
//        method.toGenericString();
//        method.toString();
//        method.equals();
//
//    }
//}
