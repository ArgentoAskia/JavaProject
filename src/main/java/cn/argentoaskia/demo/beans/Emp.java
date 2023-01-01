package cn.argentoaskia.demo.beans;

import java.lang.annotation.*;
import java.time.Instant;
import java.util.Date;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Emp {
    int no() default 0;
    String name() default "";
    String job() default "";
    int manager() default 0;
    String hireDate() default "2022-8-6";
    float sal() default 0.0f;
    float comm() default 0.0f;
    int deptNo() default 0;
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@interface Emp2{
    Class<?> clazz() default Emp2.class;
    Fruit fruit() default Fruit.APPLE;
}
