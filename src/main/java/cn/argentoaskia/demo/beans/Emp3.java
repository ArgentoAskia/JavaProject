package cn.argentoaskia.demo.beans;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Emp3 {
    Class<?> clazz() default Emp2.class;
    Fruit fruit() default Fruit.APPLE;
}
