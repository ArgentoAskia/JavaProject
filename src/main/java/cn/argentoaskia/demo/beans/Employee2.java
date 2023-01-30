package cn.argentoaskia.demo.beans;

@Emp2
public class Employee2<T extends Employee, E> extends Employee{

    @Emp4(clazz = Emp3.class)
    @Emp4(clazz = Emp.class)
    @Emp4(clazz = Emp2.class)
    private String name;
}
