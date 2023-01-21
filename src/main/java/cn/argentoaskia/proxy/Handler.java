package cn.argentoaskia.proxy;

import java.lang.reflect.*;
import java.util.Arrays;

public class Handler implements InvocationHandler {

    private DefaultImpl aDefault;

    public Handler(){
        aDefault = new DefaultImpl();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("============= 第一个参数：proxy代理对象 =============");
        Class<?> proxyClass = proxy.getClass();
        String proxyClassName = proxyClass.getName();
        System.out.println("代理类的类名：" + proxyClassName);
        Class<?>[] interfaces = proxyClass.getInterfaces();
        System.out.println("该代理对象实现了的接口：");
        for (Class<?> interfaceClass:
             interfaces) {
            System.out.println(interfaceClass.getName());
        }
        System.out.println("=================================================");
        System.out.println("============= 第二个参数：method代理对象 =============");
        // 调用的方法名称
        String methodName = method.getName();
        System.out.println("调用的方法名称：" + methodName);

        // 参数个数
        int parameterCount = method.getParameterCount();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();
        Class<?> declaringClass = method.getDeclaringClass();
        System.out.println("方法参数个数：" + parameterCount);
        System.out.println("方法定义在类" + declaringClass + "中");
        for (int i = 0; i < parameterCount; i++) {
            String parameterName = parameters[i].getName();
            String parameterTypeSimpleName = parameterTypes[i].getSimpleName();
            Object parameterValue = args[i];
            System.out.println("第" + i + "个参数：" + parameterTypeSimpleName + " " + parameterName + "=" + parameterValue);
        }
        // 返回值
        Class<?> returnType = method.getReturnType();
        System.out.println("方法返回值：" + returnType.getName());

        // 异常类型
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        System.out.println("该方法有" + exceptionTypes.length + "个异常,分别是：");
        for (Class<?> exceptionType :
                exceptionTypes) {
            System.out.println(exceptionType.getName());
        }
        System.out.println("=================================================");

        // 3.调用方法，获取结果
        System.out.println("============= 调用方法，返回结果 =============");
        Object invoke = method.invoke(aDefault, args);
        System.out.println("调用成功");
        System.out.println("方法返回值类型：" + invoke.getClass().getName());
        System.out.println("方法返回值是否是数组：" + invoke.getClass().isArray());
        System.out.print("输出返回值：");
        if (invoke.getClass().isArray()){
            // TODO: 2023/1/21  Arrays.toString()，添加Object参数的方法
            int length = Array.getLength(invoke);
            System.out.print("[");
            for (int i = 0; i < length; i++) {
                Object o = Array.get(invoke, i);
                if (i == length - 1) System.out.print(o);
                else                 System.out.print(o + ", ");
            }
            System.out.println("]");
        }else{
            System.out.println(invoke);
        }
        return invoke;
    }
}
