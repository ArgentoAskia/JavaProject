package cn.argentoaskia.demo.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import com.zaxxer.hikari.HikariJNDIFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataSourceTest {
    public static void main(String[] args) throws Exception {
        // TODO: 2022/11/13 1.创建MysqlDataSource对象，由于是驱动包本身自带的，所以不需要配置Driver
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/mysql");
        mysqlDataSource.setPassword("sujiewei");
        mysqlDataSource.setUser("root");

        // TODO: 2022/11/13 2.获取连接
        Connection connection = mysqlDataSource.getConnection();

        String sql = "SELECT * FROM user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String host = resultSet.getString(1);
            String user = resultSet.getString("User");
            System.out.println(host);
            System.out.println(user);
            System.out.println();
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
