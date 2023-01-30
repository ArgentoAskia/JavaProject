package cn.argentoaskia.demo.beans;

import java.lang.annotation.*;

// 公开的注解，不能用于继承
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Emp3 {
    Class<?> clazz() default Emp2.class;
    Fruit fruit() default Fruit.APPLE;
}
