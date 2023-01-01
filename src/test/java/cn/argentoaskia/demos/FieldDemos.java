package cn.argentoaskia.demos;

import cn.argentoaskia.demo.beans.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class FieldDemos {

    // 随机生成一个Employee类的对象
    private Employee randomEmployee() throws IllegalAccessException, InstantiationException {
        Class<Employee> employeeClass = Employee.class;
        Employee employee = employeeClass.newInstance();
        Random random = new Random();
        employee.setComm(random.nextFloat());
        employee.setDeptNo(random.nextInt());
        Date date = new Date(random.nextInt(200), random.nextInt(12), random.nextInt(30));
        employee.setHireDate(date);
        employee.setJob(UUID.randomUUID().toString());
        employee.setManager(random.nextInt());
        employee.setName(UUID.randomUUID().toString());
        employee.setNo(random.nextInt());
        employee.setSal(random.nextFloat());
        return employee;
    }

    private Field salPublicField;
    private Field noPrivateField;
    private Employee employee1;
    private Employee employee2;

    @Before
    public void init() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        salPublicField = Employee.class.getField("sal");
        // private Field需要使用getDeclaredField()
        noPrivateField = Employee.class.getDeclaredField("no");
        employee1 = randomEmployee();
        employee2 = randomEmployee();
        System.out.println("employee1对象：" + employee1);
        System.out.println("employee2对象：" + employee2);
        System.out.println("-----------------------------------------------------------------------");
    }

    /**
     * 方法：<br>
     * {@link Field#get(Object)}<br>
     * {@link Field#getBoolean(Object)}<br>
     * {@link Field#getByte(Object)}<br>
     * {@link Field#getChar(Object)}<br>
     * {@link Field#getDouble(Object)}<br>
     * {@link Field#getFloat(Object)}<br>
     * {@link Field#getInt(Object)}<br>
     * {@link Field#getLong(Object)}<br>
     * {@link Field#getShort(Object)}<br>
     * <p>
     * 说明：<br>
     * 获取字段的值，传递的{@code Object}参数一般是指具有这个{@code sal}字段的对象。
     * 这里要特别注意基本类型和包装器类的转换，getXXX方法只能用于获取基本类型的字段，get方法用于获取引用类型和<strong>包装器类</strong>
     *
     * <p>
     *     <strong>
     *         对于获取非public的字段并设置值，还需要禁止权限访问，靠方法{@link Field#setAccessible(boolean)}提供{@code true}实现。
     *     </strong>
     */
    @Test
    public void testFieldGet() throws IllegalAccessException {
        // sal字段是Float类型
        Object o = salPublicField.get(employee1);
        // no字段是private字段，需要禁止权限检查才能获取值
        noPrivateField.setAccessible(true);
        int anInt = noPrivateField.getInt(employee2);
        System.out.println(o);
        System.out.println(anInt);
    }

    @Test
    public void testFieldData() throws NoSuchFieldException {
        // 局部本地类
        class Student<T>{
            private T date;
        }

        // 获取定义了这个字段的类的Class对象
        Class<?> declaringClass = noPrivateField.getDeclaringClass();
        System.out.println(declaringClass);
        // 获取这个字段的类型
        Class<?> type = noPrivateField.getType();
        Type genericType = noPrivateField.getGenericType();
        System.out.println(type);
        System.out.println(genericType);

        // 上面两个获取字段类型的方法区别在于获取泛型上。getType()返回Object，getGenericType()返回T变量
        Field dates = Student.class.getDeclaredField("date");
        Class<?> type1 = dates.getType();
        Type genericType1 = dates.getGenericType();
        System.out.println(type1);
        System.out.println(genericType1);


        // 获取字段名称
        String name = noPrivateField.getName();
        String name1 = dates.getName();
        System.out.println(name);
        System.out.println(name1);
        int modifiers = noPrivateField.getModifiers();
        System.out.println(modifiers);

    }

    @Test
    public void testFieldIsMethods() throws NoSuchFieldException {
        class Outer {
             class Inner{

            }
        }
        // 判断是否是枚举常量
        Field apple = Fruit.class.getField("APPLE");
        boolean enumConstant1 = apple.isEnumConstant();
        boolean enumConstant = noPrivateField.isEnumConstant();
        System.out.println(enumConstant);
        System.out.println(enumConstant1);

        // 判断一个字段是否是合成字段
        // 所谓合成字段就是Java生成的字段，最常见的就是嵌套类里面的this$X
        // 参考：http://cn.voidcc.com/question/p-rkzlpzpo-nq.html
        //      https://stackoverflow.com/questions/3298130/

        boolean synthetic = noPrivateField.isSynthetic();
        System.out.println(synthetic);

        Field[] declaredFields1 = Outer.class.getDeclaredFields();
        for (Field f :
                declaredFields1) {
            System.out.println(f.getName() + ":" + f.isSynthetic());
        }
        Field[] declaredFields = Outer.Inner.class.getDeclaredFields();
        for (Field f :
                declaredFields) {
            System.out.println(f.getName() + ":" + f.isSynthetic());
        }
    }

    @Test
    public void testFieldAnnotation() throws NoSuchFieldException {
        // 在继承体系中的getDeclaredFields
//        Field[] declaredFields = Employee2.class.getDeclaredFields();
//        for (Field f :
//                declaredFields) {
//            System.out.println(f);
//        }
//        Field[] fields = Employee2.class.getFields();
//        for (Field f :
//                fields) {
//            System.out.println(f);
//        }

        AnnotatedType annotatedType = salPublicField.getAnnotatedType();

        // getAnnotation VS getDeclaredAnnotation，在获取Field上，他们两个的作用相同。
        Field sal = Employee2.class.getField("sal");
        Emp3 annotation = sal.getAnnotation(Emp3.class);
        Emp3 declaredAnnotation = sal.getDeclaredAnnotation(Emp3.class);
        System.out.println(annotation);
        System.out.println(declaredAnnotation);

        // Annotationx相关API
        // 获取字段上的特定的public注解，包括从父类继承而来的
        Emp annotation1 = salPublicField.getAnnotation(Emp.class);
        // 获取字段上的特定的public注解，不包括从父类继承而来的
        Emp3 declaredAnnotation1 = salPublicField.getDeclaredAnnotation(Emp3.class);
        // 获取字段上的所有的public注解，包括从父类继承而来的
        Annotation[] annotations = salPublicField.getAnnotations();
        // 获取字段上的所有的public注解，不包括从父类继承而来的
        Annotation[] declaredAnnotations = salPublicField.getDeclaredAnnotations();
        for (Annotation a :
                annotations) {
            System.out.println(a);
        }
        for (Annotation a :
                declaredAnnotations) {
            System.out.println(a);
        }

        // 用于可重复的注解上
        Field name = Employee2.class.getDeclaredField("name");
        Annotation[] annotations1 = name.getAnnotationsByType(Emp4.class);
        Annotation[] annotations2 = name.getDeclaredAnnotationsByType(Emp4.class);
        for (Annotation a :
                annotations1) {
            System.out.println(a);
        }
        for (Annotation a :
                annotations2) {
            System.out.println(a);
        }

        // 判断某个注解是否被标记在字段上
        boolean annotationPresent = salPublicField.isAnnotationPresent(Emp.class);
        boolean annotationPresent1 = salPublicField.isAnnotationPresent(Test.class);
        System.out.println(annotationPresent);
        System.out.println(annotationPresent1);
    }

    @Test
    public void testFieldToString() throws NoSuchFieldException {
        // 局部本地类
        class Student<T>{
            private T date;
        }
        String s = salPublicField.toString();
        String s1 = salPublicField.toGenericString();
        System.out.println(s);
        System.out.println(s1);
        // 区别仍然在泛型上
        Field dates = Student.class.getDeclaredField("date");
        String s2 = dates.toString();
        String s3 = dates.toGenericString();
        System.out.println(s2);
        System.out.println(s3);
    }

}
