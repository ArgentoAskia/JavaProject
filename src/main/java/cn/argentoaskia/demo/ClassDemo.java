package cn.argentoaskia.demo;

import cn.argentoaskia.demo.beans.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class ClassDemo {
    private static final Employee employee = new Employee();
    private static final Employee employee1 = new Employee();
    // Employee2 extends Employee
    private static final Employee2 employee2 = new Employee2();

    public static void main(String[] args) throws Exception {
        System.out.println("1.获取Class对象的方法");
        // 获取唯一的Class实例
        Class<? extends Employee> employeeClass = employee.getClass();
        System.out.println(employeeClass);

        System.out.println("=================================================");

        System.out.println("2.更加准确的类型比较，忽略父子类判别，因为是唯一实例所以可以直接==对比");
        System.out.println("employee和employee1比较：" + (employee.getClass() == employee1.getClass()));
        System.out.println("employee和employee2比较：" + (employee.getClass() == employee2.getClass()));

        System.out.println("=================================================");

        System.out.println("3-1.通过全限定类名反射创建Class实例...");
        String employeeClassStr = "cn.argentoaskia.demo.beans.Employee";
        // 加载一个类
        Class<?> employeeClassByStr = Class.forName(employeeClassStr);
        // 只获取Class实例，不参与类加载
        // Class<?> employeeClassByStr = Class.forName(employeeClassStr, false, ClassDemo.class.getClassLoader());
        System.out.println("3-2.通过newInstance()创建Employee对象实例...，调用默认构造器");
        Object o = employeeClassByStr.newInstance();
        System.out.println("默认employee对象：" + o);

        System.out.println("=================================================");

        System.out.println("4.获取类的类名：");
        System.out.println("4-1.类名的获取有四种：getName、getCanonicalName、getSimpleName、getTypeName");
        System.out.println("4-2.基本类型的四种获取方式对比：");
        Class<Integer> integerClass = int.class;
        String integerClassName = integerClass.getName();
        String integerClassCanonicalName = integerClass.getCanonicalName();
        String integerClassSimpleName = integerClass.getSimpleName();
        String integerClassTypeName = integerClass.getTypeName();
        System.out.println("intArray Class Name -- " + integerClassName);
        System.out.println("intArray Canonical Class Name -- " + integerClassCanonicalName);
        System.out.println("intArray Simple Class Name -- " + integerClassSimpleName);
        System.out.println("intArray Type Class Name -- " + integerClassTypeName);

        System.out.println("=================================================");

        System.out.println("4-3.自定义的普通类这四种获取方式对比：");
        String employeeClassName = employeeClassByStr.getName();
        String employeeClassCanonicalName = employeeClassByStr.getCanonicalName();
        String employeeClassSimpleName = employeeClassByStr.getSimpleName();
        String employeeClassTypeName = employeeClassByStr.getTypeName();
        System.out.println("Employee Class Name -- " + employeeClassName);
        System.out.println("Employee Canonical Class Name -- " + employeeClassCanonicalName);
        System.out.println("Employee Simple Class Name -- " + employeeClassSimpleName);
        System.out.println("Employee Type Class Name -- " + employeeClassTypeName);

        System.out.println("=================================================");

        System.out.println("4-4.基本类型数组的四种获取方式对比：");
        Class<int[]> intArrayClass = int[].class;
        String intArrayClassName = intArrayClass.getName();
        String intArrayClassCanonicalName = intArrayClass.getCanonicalName();
        String intArrayClassSimpleName = intArrayClass.getSimpleName();
        String intArrayClassTypeName = intArrayClass.getTypeName();
        System.out.println("intArray Class Name -- " + intArrayClassName);
        System.out.println("intArray Canonical Class Name -- " + intArrayClassCanonicalName);
        System.out.println("intArray Simple Class Name -- " + intArrayClassSimpleName);
        System.out.println("intArray Type Class Name -- " + intArrayClassTypeName);

        System.out.println("=================================================");

        System.out.println("4-5.引用类型数组的四种获取方式对比：");
        Class<Integer[]> integerArrayClass = Integer[].class;
        String integerArrayClassName = integerArrayClass.getName();
        String integerArrayClassCanonicalName = integerArrayClass.getCanonicalName();
        String integerArrayClassSimpleName = integerArrayClass.getSimpleName();
        String integerArrayClassTypeName = integerArrayClass.getTypeName();
        System.out.println("integerArray Class Name -- " + integerArrayClassName);
        System.out.println("integerArray Canonical Class Name -- " + integerArrayClassCanonicalName);
        System.out.println("integerArray Simple Class Name -- " + integerArrayClassSimpleName);
        System.out.println("integerArray Type Class Name -- " + integerArrayClassTypeName);

        System.out.println("=================================================");

        System.out.println("4-6.内部类的四种获取方式对比：");
        Class<Employee.PublicInnerEmployee> innerEmployeeClass = Employee.PublicInnerEmployee.class;
        String innerEmployeeClassName = innerEmployeeClass.getName();
        String innerEmployeeClassSimpleName = innerEmployeeClass.getSimpleName();
        String innerEmployeeClassCanonicalName = innerEmployeeClass.getCanonicalName();
        String innerEmployeeClassTypeName = innerEmployeeClass.getTypeName();
        System.out.println("innerEmployee Class Name --" + innerEmployeeClassName);
        System.out.println("innerEmployee Class Simple Name --" + innerEmployeeClassSimpleName);
        System.out.println("innerEmployee Class Canonical Name --" + innerEmployeeClassCanonicalName);
        System.out.println("innerEmployee Class Type Name --" + innerEmployeeClassTypeName);

        System.out.println("=================================================");

        System.out.println("4-7.匿名类下getSimpleName会返回空字符串,getCanonicalName会返回null");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("anonymous class name --" + this.getClass().getName());
                System.out.println("anonymous class simple name --" + this.getClass().getSimpleName());
                System.out.println("anonymous class Canonical name --" + this.getClass().getCanonicalName());
                System.out.println("anonymous class type name --" + this.getClass().getTypeName());
            }
        };
        actionListener.actionPerformed(null);

        System.out.println("=================================================");

        System.out.println("5.获取数组去掉维度的类型");
        Class<?> intArrayComponentType = intArrayClass.getComponentType();
        Class<?> integerArrayComponentType = integerArrayClass.getComponentType();
        System.out.println("int数组的原始类型：" + intArrayComponentType);
        System.out.println("Integer包装器类数组的原始类型：" + integerArrayComponentType);

        System.out.println("=================================================");

        System.out.println("6.获取类的包名");
        // String intArrayPackageName = intArrayClass.getPackage().getName();   // 抛NPE
        String employeePackageName = employeeClassByStr.getPackage().getName();
        System.out.println("employee类所在的包的包名：" + employeePackageName);

        System.out.println("=================================================");

        System.out.println("7.获取类的所有内部成员类或者接口");
        // 获取所有公开的内部类
        Class<?>[] publicEmployeeClassesAndInterfaces = employee.getClass().getClasses();
        // 获取所有非公开和公开的内部类
        Class<?>[] AllEmployeeClassesAndInterfaces = employee.getClass().getDeclaredClasses();
        System.out.println("Employee类内的所有公开的内部类和内部接口：");
        for (Class<?> cl: publicEmployeeClassesAndInterfaces) {
            System.out.println(cl);
        }
        System.out.println("------------------------------------");
        System.out.println("Employee类内的所有内部类和内部接口，包括公开的和非公开的：");
        for (Class<?> cl: AllEmployeeClassesAndInterfaces) {
            System.out.println(cl);
        }

        System.out.println("=================================================");

        System.out.println("7-1.匿名类中的getDeclaredClasses()和getClasses()");
        Employee anonymousEmployee = new Employee(){
            // 这种情况创建的类只能是default
            class AnonymousEmployee {

            }

            public void anonymousEmployee2(){
                Class<? extends Employee> anonymous = this.getClass();
                Class<?> declaringClass = anonymous.getDeclaringClass();
                Class<?> enclosingClass = anonymous.getEnclosingClass();
                System.out.println("AnonymousEmployee内部类所在的外部类是（getDeclaringClass）：" + declaringClass);
                System.out.println("AnonymousEmployee内部类所在的外部类是（getEnclosingClass）：" + enclosingClass);
            }
        };
        Class<?>[] classes = anonymousEmployee.getClass().getClasses();
        Class<?>[] declaredClasses = anonymousEmployee.getClass().getDeclaredClasses();
        System.out.println("anonymousEmployee内的所有公开的内部类和内部接口：");
        for (Class<?> cl: classes) {
            System.out.println(cl);
        }
        System.out.println("------------------------------------");
        System.out.println("anonymousEmployee内的所有内部类和内部接口，只有在匿名类中定义的才算：");
        for (Class<?> cl: declaredClasses) {
            System.out.println(cl);
        }

        System.out.println("=================================================");

        System.out.println("8.获取内部类所在的父类");
        Class<?> declaringClass = Employee.PublicInnerEmployee.class.getDeclaringClass();
        Class<?> enclosingClass = Employee.PublicInnerEmployee.class.getEnclosingClass();
        System.out.println("Employee.PublicInnerEmployee内部类所在的父类是（getDeclaringClass）：" + declaringClass);
        System.out.println("Employee.PublicInnerEmployee内部类所在的父类是（getEnclosingClass）：" + enclosingClass);
        System.out.println("---------------------------");
        System.out.println("8-1.这两个方法的最主要区别发生在匿名内部类身上，getDeclaringClass");
        new Employee(){
            // 构造代码块
            {
                anonymousEmployee2();
            }
            public void anonymousEmployee2(){
                Class<? extends Employee> anonymous = this.getClass();
                Class<?> declaringClass = anonymous.getDeclaringClass();
                Class<?> enclosingClass = anonymous.getEnclosingClass();
                System.out.println("AnonymousEmployee内部类所在的外部类是（getDeclaringClass）：" + declaringClass);
                System.out.println("AnonymousEmployee内部类所在的外部类是（getEnclosingClass）：" + enclosingClass);
            }
        };

        System.out.println("=================================================");

        System.out.println("9.获取一个类的全部字段");
        Field[] allPublicFields = employeeClassByStr.getFields();
        Field[] declaredFields = employeeClassByStr.getDeclaredFields();
        Field salPublicField = employeeClassByStr.getField("sal");
        Field jobProtectedField = employeeClassByStr.getDeclaredField("job");
        Field commDefaultField = employeeClassByStr.getDeclaredField("comm");
        Field noPrivateField = employeeClassByStr.getDeclaredField("no");
        System.out.println(Arrays.toString(allPublicFields));
        System.out.println(Arrays.toString(declaredFields));
        System.out.println(salPublicField);
        System.out.println(jobProtectedField);
        System.out.println(commDefaultField);
        System.out.println(noPrivateField);

        System.out.println("=================================================");

        System.out.println("=================================================");

        System.out.println("=================================================");

        System.out.println("12.获取一个枚举类的所有枚举常量");
        System.out.println("枚举类Fruit的所有枚举常量：" + Arrays.toString(Fruit.class.getEnumConstants()));

        System.out.println("=================================================");

        System.out.println("13.获取一个类的所有泛型参数");
        TypeVariable<? extends Class<? extends Employee2>>[] typeParameters = employee2.getClass().getTypeParameters();
        System.out.println("employee2中所有的泛型参数：" + Arrays.toString(typeParameters));

        System.out.println("=================================================");

        System.out.println("14.判别方法");
        // 判断employee是否是注解
        boolean annotation = employee.getClass().isAnnotation();
        boolean annotation1 = Emp.class.isAnnotation();

        boolean annotationPresent = employee.getClass().isAnnotationPresent(Emp.class);
        boolean annotationPresent2 = employee.getClass().isAnnotationPresent(Emp4.class);

        boolean anonymousClass = employee.getClass().isAnonymousClass();
        boolean anonymousClass1 = anonymousEmployee.getClass().isAnonymousClass();

        boolean array = employee.getClass().isArray();
        boolean array1 = int[].class.isArray();

        // 判断一个类是否是另外一个类的父类
        boolean assignableFrom = employee.getClass().isAssignableFrom(employee2.getClass());
        // 判断一个类是否是另外一个类的父类
        boolean assignableFrom2 = employee.getClass().isAssignableFrom(employee1.getClass());
        boolean assignableFrom1 = int.class.isAssignableFrom(int.class);
        boolean assignableFrom3 = int.class.isAssignableFrom(Object.class);
        System.out.println("判断employee类是否是employee2的父类:" + assignableFrom);
        System.out.println("判断employee是否与employee1是否同属一个类：" + assignableFrom2);
        System.out.println("基本类型判断只能判断自己本身：" + assignableFrom1);
        System.out.println("基本类型与任何引用类型判断都是false：" + assignableFrom3);


        boolean anEnum = employee.getClass().isEnum();
        boolean anEnum1 = Fruit.class.isEnum();

        // 判断是否是实例，包括继承的
        boolean instance = employee.getClass().isInstance(employee2);
        boolean instance1 = employee.getClass().isInstance(anEnum);

        boolean anInterface = employee.getClass().isInterface();
        boolean anInterface1 = Employee.PublicInnerInterface.class.isInterface();

        // 是否是内部类
        boolean memberClass = employee.getClass().isMemberClass();
        boolean memberClass1 = Employee.PublicInnerInterface.class.isMemberClass();
        System.out.println("Employee是否是内部类：" + memberClass);
        System.out.println("Employee.PublicInnerInterface是否是内部类：" + memberClass1);

        // 是否是基本类型的Class
        boolean primitive = employee.getClass().isPrimitive();
        boolean primitive1 = int.class.isPrimitive();
        System.out.println(primitive);
        System.out.println(primitive1);

        // 是否是合成类
        // https://stackoverflow.com/questions/399546/synthetic-class-in-java
        // https://blog.csdn.net/fengyuyeguirenenen/article/details/123271051
        boolean synthetic = employee.getClass().isSynthetic();
        System.out.println("employee对象对应的Employee类是否是合成类：" + synthetic);

        System.out.println("----------------------------------------------------");
        class A{

        }
        // 是否是本地类（局部类）
        boolean localClass1 = employee.getClass().isLocalClass();
        boolean localClass2 = A.class.isLocalClass();
        System.out.println("employee对象对应的Employee类是否是局部类：" + localClass1);
        System.out.println("A类是否是局部类：" + localClass2);


    }
}
