package cn.argentoaskia.demo.jdbc;



import java.sql.*;

/**
 * 步骤和mysql差不多，只不过连接方式不太一样
 */
public class OracleJDBCDemo {
    public static void main(String[] args) throws Exception{
        // TODO: 2022/11/13  1.两个驱动二选一
        Driver driver = new oracle.jdbc.OracleDriver();
//        Driver driver1 = new oracle.jdbc.driver.OracleDriver();

        // TODO: 2022/11/13  2.注册驱动，方法二选一
        DriverManager.registerDriver(driver);
//        Class.forName("oracle.jdbc.OracleDriver");
//        Class.forName("oracle.jdbc.driver.OracleDriver");

        // TODO: 2022/11/13  3.获取连接
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String username = "scott";
        String password = "sujiewei";
        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM DEPT";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int deptno = resultSet.getInt("DEPTNO");
            String dname = resultSet.getString("DNAME");
            String loc = resultSet.getString("LOC");
            System.out.println(deptno);
            System.out.println(dname);
            System.out.println(loc);
            System.out.println();
        }
        statement.close();
        resultSet.close();
        connection.close();
    }
}
