package cn.argentoaskia.orm.dbutils.demos;

import cn.argentoaskia.orm.dbutils.beans.Store;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

public class DbUtilsSimpleDemo {
    public static void main(String[] args) throws Exception {
        // 1.创建一条连接
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/sakila?useSSL=false";
        String username = "root";
        String password = "sujiewei";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 2.创建核心类QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();

        // 3.编写SQL语句
        String sql = "SELECT store_id AS storeId, manager_staff_id AS managerStaffId," +
                "address_id AS addressId, last_update AS lastUpdate FROM store";

        // 4.创建核心类ResultSetHandler对象
        BeanListHandler<Store> storeBeanListHandler = new BeanListHandler<>(Store.class);

        // 5.执行查询
        List<Store> stores = queryRunner.query(connection, sql, storeBeanListHandler);

        // 6.输出查询结果
        System.out.println(Arrays.toString(stores.toArray()));

        // 7.关闭连接
        if (!connection.isClosed()) connection.close();
    }
}
