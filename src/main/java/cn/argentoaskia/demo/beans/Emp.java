package cn.argentoaskia.demo.beans;

import java.lang.annotation.*;
import java.time.Instant;
import java.util.Date;

// 公开的注解，且可以被用于继承方法上
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
