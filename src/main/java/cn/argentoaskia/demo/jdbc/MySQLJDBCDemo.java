package cn.argentoaskia.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLJDBCDemo {

    /**
     * JDBC快速入门。
     * 下面带// TODO的都是注释，普通的带//的都是代码，可以二选一。
     * @param args 参数2
     */
    public static void main(String[] args) throws Exception {
        // TODO: 2022/8/11
        //  1.导入相应数据库的驱动包，如：mysql --> mysql-connector-java、oracle --> ojdbc、
        //    MS SQL Server --> sqljdbc

        // TODO: 2022/8/11
        //  2. 加载驱动，涉及到的类是Driver,加载的方式有两种:

        // TODO:
        //  方式1：利用JDBC中的驱动管理类DriverManager

        //  Driver mysqlDriver = new com.mysql.jdbc.Driver();
        //  DriverManager.registerDriver(mysqlDriver);

        // TODO:
        //  方式2：利用类加载机制，加载Driver中的静态代码块，实际上原理也是方式1。
        Class.forName("com.mysql.jdbc.Driver");

        // TODO:
        //  自从mysql驱动包到了8.0之后，mysql的驱动就多了一个，分别是：
        //  com.mysql.jdbc.Driver    (旧的驱动包)
        //  com.mysql.cj.jdbc.Driver (新的驱动包)
        //  在注册旧的驱动包的时候，控制台会提示出异常信息,下面的两种驱动注册都是可以的。
        //  Class.forName("com.mysql.cj.jdbc.Driver");
        //  Class.forName("com.mysql.jdbc.Driver");

        // TODO: 2022/8/11
        //  3.获取连接，涉及到的类是DriverManager.getConnection()
        //    需要传递三个参数：数据库URL、用户名、密码
        //    URL写法：jdbc:mysql://localhost:3306/mysql?参数
        String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
        String username = "root";
        String password = "sujiewei";
        // TODO: 2022/8/23
        //  关键点：获取连接
        Connection connection = DriverManager.getConnection(url, username, password);


        // TODO: 2022/8/11
        //  4.获取statement，也叫SQL执行器，常用的：
        //     - PreparedStatement： 预处理SQL执行器
        //     - Statement：         基本SQL执行器
        //     - CallableStatement： 存储过程执行器
        //    使用SQL执行器之前需要编写SQL语句。
        String sql = "SELECT * FROM user";
        // String preparedSql = "SELECT * FROM user where Host = ?";

        // TODO:
        //  获取SQL执行器的方法需要用到connection对象，对应的方法如下：
        //  connection.createStatement()            --> Statement
        //  connection.prepareStatement(String sql) --> PreparedStatement
        //  connection.prepareCall(String sql)      --> CallableStatement
        //  如果是preparedStatement的话后期则需要通过setXXX()方法填充参数，如：
        //  setXXX(index，XXX)：index从1开始，代表第一个?，XXX代表用于填充的参数。
        Statement statement = connection.createStatement();
        // PreparedStatement preparedStatement = connection.prepareStatement(preparedSql);
        // preparedStatement.setString(1, "localhost");

        // TODO: 2022/8/11
        //  5.执行SQL语句，根据SQL语句的类型，执行不同的方法：
        //  execute(String sql)：DDL，create、drop、alter...
        //  executeUpdate(String sql)：insert、delete、update
        //  executeQuery(String sql)：select
        //  -
        //  其中execute(String sql)返回boolean类型，代表执行的返回结果，true代表执行成功，false代表执行失败
        //  executeUpdate(String sql)返回一个整数，代表收影响的行数，例如插入一行数据，插入成功则返回1，一般判断是否>0
        //  来判断是否插入成功
        //  executeQuery(String sql)返回一个ResultSet，代表查询出来的二维表。
        ResultSet resultSet = statement.executeQuery(sql);
        // ResultSet resultSet1 = preparedStatement.executeQuery();

        // TODO: 2022/8/11
        //   6.结果处理，对于execute(String sql)、executeUpdate(String sql)结果没啥特别处理的方式，
        //      但是对于executeQuery(String sql)则需要自行解析ResultSet，解析的步骤如下：
        //      1.调用next()，让ResultSet指向下一行数据，如果没有数据了则返回false，退出循环。
        //      2.通过调用getXXX()的方法来获取数据。
        while (resultSet.next()){
            String host = resultSet.getString(1);
            String user = resultSet.getString("User");
            System.out.println(host);
            System.out.println(user);
        }

        /*
        while(resultSet1.next()){
            String host = resultSet1.getString(1);
            String user = resultSet1.getString(2);
            String Select_priv = resultSet1.getString(3);
            String Insert_priv = resultSet1.getString(4);
            System.out.println(host);
            System.out.println(user);
            System.out.println(Select_priv);
            System.out.println(Insert_priv);
        }

         */

        // TODO: 2022/8/11
        //   7. 关闭资源，resultSet要关，statement要关、connection要关。
        resultSet.close();
        statement.close();
        connection.close();
        // preparedStatement.close();
        // resultSet1.close();

    }
}
