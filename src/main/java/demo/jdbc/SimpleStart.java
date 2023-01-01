package demo.jdbc;

import java.sql.*;


@Deprecated
public class SimpleStart {

    public void simpleStart() throws ClassNotFoundException, SQLException {
        // 1.添加坐标
        // 2.加载驱动，方法有两种
        // 注意随着mysql驱动包的版本更新，内置两个驱动类，分别是：
        // > com.mysql.jdbc.Driver
        // > com.mysql.cj.jdbc.Driver(推荐)
        // 加载驱动方式1
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 加载驱动方式2
        // DriverManager.registerDriver(new Driver());

        // 3.获取数据库连接对象
        Connection sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oracledata",
                "root", "sujiewei");
        // 4.定义SQL语句
        String sql = "select * from emp";

        // 5.获取执行SQL语句的对象
        Statement statement = sqlConnection.createStatement();
        // 6.执行SQL
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("EMPNO    ENAME    JOB    MGR    HIREDATE    SAL    COMM    DEPTNO");
        while(resultSet.next()){
            int EMPNO = resultSet.getInt(1);
            String ENAME = resultSet.getString(2);
            String JOB = resultSet.getString(3);
            String MGR = resultSet.getString(4);
            Date HIREDATE = resultSet.getDate(5);
            Float SAL = resultSet.getFloat(6);
            Float COMM = resultSet.getFloat(7);
            int DEPTNO = resultSet.getInt(8);
            System.out.println(EMPNO + "    " +ENAME+ "    " +JOB+ "    " +MGR+ "    " +HIREDATE+ "    " +SAL+ "    " +COMM+ "    " +DEPTNO);
        }
        resultSet.close();
        statement.close();
        sqlConnection.close();
    }

}
