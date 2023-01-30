package cn.argentoaskia.demo.beans;

import java.util.Date;

@Emp(deptNo = 50, sal = 3.5f)
@Emp2
@Emp3
public class Employee {
    private int no;
    private String name;
    @Emp(deptNo = 500, sal = 3.54f)
    @Emp2
    @Emp3
    protected String job;
    protected Integer manager;
    public Date hireDate;
    @Emp(deptNo = 5000, sal = 3.54f)
    @Emp2
    @Emp3
    public Float sal;
    Float comm;
    Integer deptNo;

    @Override
    public String toString() {
        return "Employee{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", manager=" + manager +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptNo=" + deptNo +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public Employee setNo(Integer no) {
        this.no = no;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return job;
    }

    public Employee setJob(String job) {
        this.job = job;
        return this;
    }

    public Integer getManager() {
        return manager;
    }

    public Employee setManager(Integer manager) {
        this.manager = manager;
        return this;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public Employee setHireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public Float getSal() {
        return sal;
    }

    public Employee setSal(Float sal) {
        this.sal = sal;
        return this;
    }

    public Float getComm() {
        return comm;
    }

    public Employee setComm(Float comm) {
        this.comm = comm;
        return this;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public Employee setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
        return this;
    }


    public class PublicInnerEmployee {

    }
    public static class PublicStaticInnerEmployee {

    }
    static class DefaultStaticInnerEmployee {

    }
    class DefaultInnerEmployee {

    }
    protected static class ProtectedStaticInnerEmployee {

    }
    protected class ProtectedInnerEmployee {

    }
    private static class PrivateStaticInnerEmployee {

    }
    private class PrivateInnerEmployee {

    }

    // interface 默认就是static
    public static interface PublicInnerInterface{

    }
    protected static interface ProtectedInnerInterface{

    }
    static interface DefaultInnerInterface{

    }
    private static interface PrivateInnerInterface{

    }

}

