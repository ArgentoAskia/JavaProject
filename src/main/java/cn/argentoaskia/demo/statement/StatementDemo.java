package cn.argentoaskia.demo.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
        String username = "root";
        String password = "sujiewei";
        Connection connection = DriverManager.getConnection(url, username, password);


        Statement statement = connection.createStatement();

        String createTableSql = "";

        String insertDataSql = "";
        String selectDataSql = "";

        String dropTableSql = "";

        boolean execute = statement.execute(createTableSql);
        if (!execute){
            System.out.println("创建表失败");
            return;
        }
        int i = statement.executeUpdate(insertDataSql);
        if (i < 0){
            System.out.println("插入数据失败");
            return;
        }
        ResultSet resultSet = statement.executeQuery(selectDataSql);
        while (resultSet.next()){

        }
        boolean execute1 = statement.execute(dropTableSql);
        if (!execute1){
            System.out.println("删除失败！！，请手动删除数据表");
        }
    }
}
