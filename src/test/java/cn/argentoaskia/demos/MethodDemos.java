package cn.argentoaskia.demos;

import cn.argentoaskia.demo.beans.Emp;
import cn.argentoaskia.demo.beans.Employee2;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MethodDemos {

    private Map<String, Method> employeeMethods;

    @Before
    public void init() {
        employeeMethods = new HashMap<>();
        Method[] declaredMethods = Employee2.class.getDeclaredMethods();
        for (Method me :
                declaredMethods) {
            String name = me.getName();
            employeeMethods.put(name, me);
        }
    }

    /**
     * 方法：<br>
     * {@link Method#getReturnType()}<br>
     * {@link Method#getAnnotatedReturnType()}<br>
     * {@link Method#getGenericReturnType()}<br>
     * <p>
     * 说明：<br>
     * 获取一个方法的返回值类型。
     * <br>
     * 1. 区别在与三个方法的返回不一样，{@link Method#getReturnType()}会直接返回{@link Class},
     * {@link Method#getAnnotatedReturnType()}则返回{@link AnnotatedType}
     * {@link Method#getGenericReturnType()}则返回{@link Type}
     * <br>
     * 2. 在处理泛型的时候，{@link Method#getReturnType()}会返回{@link Object},而其他两个方法会直接返回{@code T}变量
     * <br>
     * 3. {@link Method#getAnnotatedReturnType()}返回的代表任何可以被注解的类型，该接口可以获取{@link Type}对象，更多的时候适用于获取标记在对象上的注解。
     * 而{@link Method#getGenericReturnType()}则是直接返回{@link Type}接口
     */
    @Test
    public void testMethodReturnType() {
        Method method = employeeMethods.get("print6");
        Class<?> returnType = method.getReturnType();
        Type genericReturnType = method.getGenericReturnType();
        AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
        System.out.println("getReturnType返回值：" + returnType);
        System.out.println("getGenericReturnType返回值：" + genericReturnType);
        System.out.println("getAnnotatedReturnType返回值：" + annotatedReturnType);
        // AnnotatedType获取Type接口
        Type type = annotatedReturnType.getType();
        System.out.println(type);
        // 获取注解在返回值上的注解
        Emp annotation = annotatedReturnType.getDeclaredAnnotation(Emp.class);
        System.out.println(annotation);
    }

    /**
     * 方法：<br>
     * {@link Method#getAnnotatedExceptionTypes()}<br>
     * {@link Method#getExceptionTypes()}<br>
     * {@link Method#getGenericExceptionTypes()}<br>
     * <p>
     * 说明：<br>
     *     这三个方法返回值的区别参考方法{@link MethodDemos#testMethodReturnType()}
     * @see MethodDemos#testMethodReturnType()
     */
    @Test
    public void testMethodException() {
        // 带泛型异常声明的
        Method methodPrint7 = employeeMethods.get("print7");
        Method methodPrint6 = employeeMethods.get("print6");

        // getExceptionTypes()
        Class<?>[] exceptionTypes = methodPrint7.getExceptionTypes();
        Class<?>[] exceptionTypes1 = methodPrint6.getExceptionTypes();
        System.out.println("methodPrint7.getExceptionTypes()：" + Arrays.toString(exceptionTypes));
        System.out.println("methodPrint6.getExceptionTypes()：" + Arrays.toString(exceptionTypes1));
        System.out.println();

        // getAnnotatedExceptionTypes()
        AnnotatedType[] annotatedExceptionTypes = methodPrint6.getAnnotatedExceptionTypes();
        AnnotatedType[] annotatedExceptionTypes1 = methodPrint7.getAnnotatedExceptionTypes();
        // Array类可以新增这个方法Arrays.toString(target，methodOfTargetClass)
        System.out.println("methodPrint7.getAnnotatedExceptionTypes()：" + Arrays.toString(annotatedExceptionTypes1));
        System.out.println("methodPrint6.getAnnotatedExceptionTypes()：" + Arrays.toString(annotatedExceptionTypes));
        System.out.println("=================================================");
        for (AnnotatedType a :
                annotatedExceptionTypes1) {
            System.out.println(a + "：" + a.getType());
        }
        for (AnnotatedType a :
                annotatedExceptionTypes) {
            System.out.println(a + "：" + a.getType());
        }
        System.out.println("=================================================");
        Emp annotation = annotatedExceptionTypes1[0].getAnnotation(Emp.class);
        System.out.println(annotation);
        System.out.println();

        // getGenericExceptionTypes()
        Type[] genericExceptionTypes = methodPrint6.getGenericExceptionTypes();
        Type[] genericExceptionTypes1 = methodPrint7.getGenericExceptionTypes();
        System.out.println("methodPrint7.getGenericExceptionTypes()：" + Arrays.toString(genericExceptionTypes1));
        System.out.println("methodPrint6.getGenericExceptionTypes()：" + Arrays.toString(genericExceptionTypes));
    }

    /**
         * 方法：<br>
         *      {@link Method#getName()}<br>
         *      {@link Method#getModifiers()}<br>
         *      {@link Method#getDeclaringClass()}<br>
         *      {@link Method#getDefaultValue()}<br>
         * <p>
         * 说明：<br>
         *
         */
    @Test
    public void testMethodInfo(){
        //  如果方法是类方法
        Method methodPrint5 = employeeMethods.get("print5");
        String name = methodPrint5.getName();
        int modifiers = methodPrint5.getModifiers();
        Class<?> declaringClass = methodPrint5.getDeclaringClass();
        System.out.println("方法名：" + name);
        System.out.println("方法修饰符：" + modifiers);
        System.out.println("方法所属类：" + declaringClass);

        // 如果方法是注解的方法
        Method[] declaredMethods = Emp.class.getDeclaredMethods();
        for (Method me :
                declaredMethods) {
            // 获取注解成员名
            String name1 = me.getName();
            // 获取注解成员默认值
            Object defaultValue = me.getDefaultValue();
            System.out.println(name1 + "：" + defaultValue);
        }
    }


}
