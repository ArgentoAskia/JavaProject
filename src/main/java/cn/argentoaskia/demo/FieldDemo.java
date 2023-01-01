package cn.argentoaskia.demo;

import cn.argentoaskia.demo.beans.Employee;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


public class FieldDemo {
    public static Employee randomEmployee() throws IllegalAccessException, InstantiationException {
        Class<Employee> employeeClass = Employee.class;
        Employee employee = employeeClass.newInstance();
        Random random = new Random();
        employee.setComm(random.nextFloat());
        employee.setDeptNo(random.nextInt());
        Date date = new Date(random.nextInt(200), random.nextInt(12),random.nextInt(30) );
        employee.setHireDate(date);
        employee.setJob(UUID.randomUUID().toString());
        employee.setManager(random.nextInt());
        employee.setName(UUID.randomUUID().toString());
        employee.setNo(random.nextInt());
        employee.setSal(random.nextFloat());
        return employee;
    }
    public static void main(String[] args) throws Exception {
        Field field = Employee.class.getField("sal");
        Employee employee1 = randomEmployee();
        Employee employee2 = randomEmployee();
        System.out.println(employee1);
        System.out.println(employee2);
        Object o1 = field.get(employee1);
        Object o2 = field.get(employee2);
        System.out.println(o1);
        System.out.println(o2);
        // 区别在于泛型
        String s = field.toGenericString();
        String s1 = field.toString();
        System.out.println(s);
        System.out.println(s1);
        //        获取值：
        //        hireDate.get();
        //        hireDate.getBoolean();
        //        hireDate.getByte();
        //        hireDate.getChar();
        //        hireDate.getDouble();
        //        hireDate.getFloat();
        //        hireDate.getInt();
        //        hireDate.getLong();
        //        hireDate.getShort();

        //        获取字段的类型名，区别在于泛型的T
                field.getGenericType();
                field.getType();
        //        获取字段的权限级别，配合Modify类
                field.getModifiers();
        //        获取字段的名称
        //        hireDate.getName();


        //        判断声明了这个字段的类的class对象
                field.getDeclaringClass();

        //        获取在这个字段上的注解相关
//                field.getAnnotatedType();
//                field.getAnnotation();
//                field.getAnnotations();
//                field.getAnnotationsByType();
//                field.getDeclaredAnnotation();
//                field.getDeclaredAnnotations();
//                field.getDeclaredAnnotationsByType();

        //        判断某个注解是否被标注在该字段上
        //        field.isAnnotationPresent();
        //        判断字段是否为枚举常量
        //        field.isEnumConstant();
        //        判断字段是否为同步字段
        //        field.isSynthetic();

        //        判断字段是否可访问，当字段被public修饰时为true
        //        field.isAccessible();
        //        打开字段，修改值
//                field.setAccessible();
        //        设置值：
        //      field.set();
        //      field.setBoolean();
        //      field.setByte();
        //      field.setChar();
        //      field.setDouble();
        //      field.setFloat();
        //      field.setInt();
        //      field.setLong();
        //      field.setShort();
        field.toGenericString();
        field.toString();
    }
}