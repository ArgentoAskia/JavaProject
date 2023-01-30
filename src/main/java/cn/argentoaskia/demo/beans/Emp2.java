package cn.argentoaskia.demo.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 非公开的Emp2的获取(只有同包内的成员可见)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@interface Emp2{
    Class<?> clazz() default Emp2.class;
    Fruit fruit() default Fruit.APPLE;
}
