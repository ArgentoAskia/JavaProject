package cn.argentoaskia.demo.beans;

import java.lang.annotation.*;

// 可以重复标记的注解
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Emp4.Emp4List.class)
public @interface Emp4 {

    Class<?> clazz() default Emp2.class;
    Fruit fruit() default Fruit.APPLE;




    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Emp4List{
        Emp4[] value();
    }
}
