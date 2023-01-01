package cn.argentoaskia.demo;

import cn.argentoaskia.demo.beans.Employee;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ClassDemo {




    public static void main(String[] args) throws Exception {
       

        System.out.println("1.获取Class对象的方法");
        Employee employee = new Employee();
        // 获取唯一的Class实例
        Class<? extends Employee> employeeClass = employee.getClass();
        System.out.println(employeeClass);

        System.out.println("=================================================");

        System.out.println("1.通过全限定类名反射创建Class实例");
        String employeeClassStr = "cn.argentoaskia.demo.beans.Employee";
        Class<?> employeeClassByStr = Class.forName(employeeClassStr);
        System.out.println(employeeClassByStr);

        System.out.println();

        System.out.println("2.通过newInstance()创建Employee对象实例");
        Object o = employeeClassByStr.newInstance();
        System.out.println(o);

        System.out.println("=================================================");

        System.out.println("1.更加准去的类型比较");
        if (employeeClass == employeeClassByStr){
            System.out.println("employeeClass和employeeClassByStr是同一个对象");
        }else{
            System.out.println("employeeClass和employeeClassByStr不是同一个对象");
        }

        System.out.println("=================================================");

        System.out.println("1.获取类的类名");
        String employeeClassName = employeeClassByStr.getName();
        String employeeClassCanonicalName = employeeClassByStr.getCanonicalName();
        String employeeClassSimpleName = employeeClassByStr.getSimpleName();
        String employeeClassTypeName = employeeClassByStr.getTypeName();
        System.out.println("Employee Class Name -- " + employeeClassName);
        System.out.println("Employee Canonical Class Name -- " + employeeClassCanonicalName);
        System.out.println("Employee Simple Class Name -- " + employeeClassSimpleName);
        System.out.println("Employee Type Class Name -- " + employeeClassTypeName);

        Class<int[]> intArrayClass = int[].class;
        String intArrayClassName = intArrayClass.getName();
        String intArrayClassCanonicalName = intArrayClass.getCanonicalName();
        String intArrayClassSimpleName = intArrayClass.getSimpleName();
        String intArrayClassTypeName = intArrayClass.getTypeName();
        System.out.println("intArray Class Name -- " + intArrayClassName);
        System.out.println("intArray Canonical Class Name -- " + intArrayClassCanonicalName);
        System.out.println("intArray Simple Class Name -- " + intArrayClassSimpleName);
        System.out.println("intArray Type Class Name -- " + intArrayClassTypeName);

        System.out.println();

        System.out.println("2.获取数组去掉维度的类型");
        Class<?> intArrayComponentType = intArrayClass.getComponentType();
        System.out.println(intArrayComponentType);

        System.out.println();

        System.out.println("3.获取类的包名");
        // String intArrayPackageName = intArrayClass.getPackage().getName();   // 抛NPE
        String employeePackageName = employeeClassByStr.getPackage().getName();
        System.out.println(employeePackageName);

        System.out.println("=================================================");

        System.out.println("1.获取类的所有内部成员类或者接口");

        System.out.println("2.获取内部成员类所在的父类");

        System.out.println("=================================================");

        System.out.println("1.获取一个类的全部字段");
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
    }
}
