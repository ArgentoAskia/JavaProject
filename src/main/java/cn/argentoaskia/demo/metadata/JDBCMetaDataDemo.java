package cn.argentoaskia.demo.metadata;

import javax.sound.sampled.AudioInputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.*;
import java.util.StringJoiner;

public class JDBCMetaDataDemo {
    private static void printCatalogsResultSet(ResultSet resultSet) throws SQLException {
        System.out.print("数据库所有可用的Catalogs（类别名称，数据库名，表空间等）：");
        StringJoiner stringJoiner = new StringJoiner(", ","[", "]");
        while(resultSet.next()){
            String catalog = resultSet.getString(1);
            stringJoiner.add(catalog);
        }
        System.out.println(stringJoiner.toString());
    }
    private static void printSchemasResultSet(ResultSet resultSet) throws SQLException {
        System.out.print("数据库所有可用的Schemas（）：");
        System.out.println("[TABLE_SCHEM | TABLE_CATALOG]");
        while(resultSet.next()){
            String tableSchema = resultSet.getString(1);
            String tableCatalog = resultSet.getString(2);
            System.out.println("    [" + tableSchema + " | " + tableCatalog + "]");
        }
    }
    private static void printClientInfoProperties(ResultSet resultSet) throws SQLException {
        System.out.print("数据库ClientInfo（客户端信息）：");
        System.out.println("[NAME | MAX_LEN | DEFAULT_VALUE | DESCRIPTION]");
        while(resultSet.next()){
            String name = resultSet.getString("NAME");
            int max_len = resultSet.getInt("MAX_LEN");
            String default_value = resultSet.getString("DEFAULT_VALUE");
            String description = resultSet.getString("DESCRIPTION");
            System.out.println("    [" + name + " | " + max_len + " | " + default_value + " | " + description +"]");
        }
    }
    private static void printTableTypes(ResultSet resultSet) throws SQLException {
        System.out.print("获取数据库支持的表的类型，如：table（表）、view（视图）等等：");
        StringJoiner stringJoiner = new StringJoiner(", ","[", "]");
        while(resultSet.next()){
            String table_type = resultSet.getString("TABLE_TYPE");
            stringJoiner.add(table_type);
        }
        System.out.println(stringJoiner.toString());
    }

    private static void showDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {
        // 判断当前用户是否可以调用所有存储过程
        boolean b = databaseMetaData.allProceduresAreCallable();
        // 判断当前用户是否可以选择所有的表格
        boolean b1 = databaseMetaData.allTablesAreSelectable();
        // 判断是否自动提交失败时关闭全部的ResultSet
        boolean b2 = databaseMetaData.autoCommitFailureClosesAllResultSets();
        // 判断自增key是否总是返回
        boolean b3 = databaseMetaData.generatedKeyAlwaysReturned();
        boolean readOnly = databaseMetaData.isReadOnly();
        boolean b4 = databaseMetaData.supportsTransactions();

        System.out.println("判断当前用户是否可以调用所有存储过程: " + b);
        System.out.println("判断当前用户是否可以选择所有的表格: " + b1);
        System.out.println("判断是否自动提交失败时关闭全部的ResultSet: " + b2);
        System.out.println("判断自增key是否总是返回: " + b3);
        System.out.println("判断数据库是否只允许读操作: " + readOnly);
        System.out.println("判断数据库是否支持事务: " + b4);

        // 获取默认事务隔离级别
        int defaultTransactionIsolation = databaseMetaData.getDefaultTransactionIsolation();
        // 获取数据库的产品名称
        String databaseProductName = databaseMetaData.getDatabaseProductName();
        // 获取数据库的版本号
        String databaseProductVersion = databaseMetaData.getDatabaseProductVersion();
        // 获取数据库的主版本号
        int databaseMajorVersion = databaseMetaData.getDatabaseMajorVersion();
        // 获取数据库的次版本号
        int databaseMinorVersion = databaseMetaData.getDatabaseMinorVersion();
        // 获取JDBC主版本号
        int jdbcMajorVersion = databaseMetaData.getJDBCMajorVersion();
        // 获取JDBC次版本号
        int jdbcMinorVersion = databaseMetaData.getJDBCMinorVersion();
        // 获取数据库的用户名
        String userName = databaseMetaData.getUserName();
        // 获取数据库的URL
        String url1 = databaseMetaData.getURL();
        // 获取数据库的驱动名称
        String driverName = databaseMetaData.getDriverName();
        // 获取数据库的驱动版本号
        String driverVersion = databaseMetaData.getDriverVersion();
        // 获取数据库最大连接数
        int maxConnections = databaseMetaData.getMaxConnections();
        // 获取数据库中所有的Catalogs（类别名称，数据库名，表空间等）
        ResultSet catalogs = databaseMetaData.getCatalogs();
        // 获取catalog和table之间的连接符号，一般返回.
        // 如sakila数据库中的actor表的表示则：sakila.actor
        String catalogSeparator = databaseMetaData.getCatalogSeparator();
        // 返回创建数据库的默认术语，一般返回database或者CATALOG
        String catalogTerm = databaseMetaData.getCatalogTerm();
        // 获取数据库名称的最大长度
        int maxCatalogNameLength = databaseMetaData.getMaxCatalogNameLength();
        // 获取数据库的schemas
        ResultSet schemas = databaseMetaData.getSchemas();

        System.out.println("获取数据库默认事务隔离级别: " + defaultTransactionIsolation);
        System.out.println("获取数据库的产品名称: " + databaseProductName);
        System.out.println("获取数据库的版本号: " + databaseProductVersion);
        System.out.println("获取数据库的主版本号：" + databaseMajorVersion);
        System.out.println("获取数据库的次版本号：" + databaseMinorVersion);
        System.out.println("获取JDBC主版本号：" + jdbcMajorVersion);
        System.out.println("获取JDBC次版本号：" + jdbcMinorVersion);
        System.out.println("获取数据库的用户名: " + userName);
        System.out.println("获取数据库的URL: " + url1);
        System.out.println("获取数据库的驱动名称: " + driverName);
        System.out.println("获取数据库的驱动版本号: " + driverVersion);
        System.out.println("获取数据库期望的最大连接数: " + maxConnections);
        printCatalogsResultSet(catalogs);
        System.out.println("获取catalog和table之间的连接符号: " + catalogSeparator);
        System.out.println("获取创建数据库的默认术语: " + catalogTerm);
        System.out.println("获取数据库名称的最大长度: " + maxCatalogNameLength);
        printSchemasResultSet(schemas);

        // 获取客户端信息数据
        ResultSet clientInfoProperties = databaseMetaData.getClientInfoProperties();
        printClientInfoProperties(clientInfoProperties);
        // 获取列名的最大长度
        int maxColumnNameLength = databaseMetaData.getMaxColumnNameLength();
        System.out.println("获取列名的最大长度: " + maxColumnNameLength);
        // 获取groupBy子句的最大列数
        int maxColumnsInGroupBy = databaseMetaData.getMaxColumnsInGroupBy();
        System.out.println("获取groupBy子句的最大列数: " + maxColumnsInGroupBy);
        // 获取Index子句的最大列数
        int maxColumnsInIndex = databaseMetaData.getMaxColumnsInIndex();
        System.out.println("获取Index子句的最大列数: " + maxColumnsInIndex);
        // 获取OrderBy子句的最大列数
        int maxColumnsInOrderBy = databaseMetaData.getMaxColumnsInOrderBy();
        System.out.println("获取OrderBy子句的最大列数: " + maxColumnsInOrderBy);
        // 获取Select子句的最大列数
        int maxColumnsInSelect = databaseMetaData.getMaxColumnsInSelect();
        System.out.println("获取Select子句的最大列数: " + maxColumnsInSelect);
        // 获取表支持的最大列数
        int maxColumnsInTable = databaseMetaData.getMaxColumnsInTable();
        System.out.println("获取表支持的最大列数: " + maxColumnsInTable);
        // 获取游标名的最大长度
        int maxCursorNameLength = databaseMetaData.getMaxCursorNameLength();
        System.out.println("获取游标名的最大长度: " + maxCursorNameLength);
        // 获取Index的最大长度
        int maxIndexLength = databaseMetaData.getMaxIndexLength();
        System.out.println("获取Index的最大长度: " + maxIndexLength);
        // 获取最大Statement的数量
        int maxStatements = databaseMetaData.getMaxStatements();
        System.out.println("获取最大Statement的数量: " + maxStatements);

        // 获取SQL2003版本的关键字
        String sqlKeywords = databaseMetaData.getSQLKeywords();
        System.out.println("获取SQL2003版本的关键字: " + sqlKeywords);

        // 获取数据库中所有String相关的函数的函数名
        String stringFunctions = databaseMetaData.getStringFunctions();
        System.out.println("获取数据库中所有String相关的函数的函数名: " + stringFunctions);
        // 获取数据库中所有numeric（数字相关）相关的函数的函数名
        String numericFunctions = databaseMetaData.getNumericFunctions();
        System.out.println("获取数据库中所有numeric（数字相关）相关的函数的函数名: " + numericFunctions);
        // 获取数据库中所有系统相关的函数的函数名
        String systemFunctions = databaseMetaData.getSystemFunctions();
        System.out.println("获取数据库中所有系统相关的函数的函数名: " + systemFunctions);
        // 获取数据库中所有时间相关的函数的函数名
        String timeDateFunctions = databaseMetaData.getTimeDateFunctions();
        System.out.println("获取数据库中所有时间相关的函数的函数名: " + timeDateFunctions);

        // 获取数据库支持的表的类型，如：table（表）、view（视图）等等
        ResultSet tableTypes = databaseMetaData.getTableTypes();
        printTableTypes(tableTypes);

        // 获取类型信息
        ResultSet typeInfo = databaseMetaData.getTypeInfo();


        // 获取CRM表的信息
        ResultSet crm = databaseMetaData.getTables("crm", null, "%", new String[]{"TABLE", "VIEW"});
    }


    private static void showParameterMetaData(ParameterMetaData parameterMetaData) throws SQLException {

        // 获取预处理SQL中？的个数
        int parameterCount = parameterMetaData.getParameterCount();
        System.out.println("?的个数：" + parameterCount);
//        String parameterClassName = parameterMetaData.getParameterClassName(2);
//        System.out.println(parameterClassName);
//        int parameterType = parameterMetaData.getParameterType(1);
//        String parameterTypeName = parameterMetaData.getParameterTypeName(1);
//        int precision = parameterMetaData.getPrecision(1);
//        int scale = parameterMetaData.getScale(1);
//        System.out.println(parameterType);
//        System.out.println(parameterTypeName);
//        System.out.println(precision);
//        System.out.println(scale);
    }
    private static void showResultSetMetaData(ResultSetMetaData resultSetMetaData) throws SQLException {
        System.out.println("获取结果集的列数: " + resultSetMetaData.getColumnCount());
        System.out.println("获取第一列的列名称: " + resultSetMetaData.getColumnName(1));
        System.out.println("获取第二列的SQL类型对应于java.sql.Types类的字段: " + resultSetMetaData.getColumnType(2));
        System.out.println("获取第一列的SQL类型: " + resultSetMetaData.getColumnTypeName(1));
        System.out.println("获取第一列SQL类型对应于Java的类型: " + resultSetMetaData.getColumnClassName(1));
        System.out.println("获取第一列所在的表的名称: " + resultSetMetaData.getTableName(1));
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO: 2022/8/12 所谓元素据值得是数据库中不可划分的数据，一般这类数据包括数据库名称、驱动包版本、表列名、表值等等。
        // 元数据类主要有三个：DatabaseMetaData、
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
        String username = "root";
        String password = "sujiewei";
        //  关键点：获取连接
        Connection connection = DriverManager.getConnection(url, username, password);

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        showDatabaseMetaData(databaseMetaData);

        System.out.println("--------------------------- 华丽分割线 --------------------------");
        String preparedSql = "SELECT * FROM user where Host = ? AND User = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(preparedSql);
        preparedStatement.setString(1, "localhost");
        preparedStatement.setObject(2, "Java");
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        showParameterMetaData(parameterMetaData);


        System.out.println("--------------------------- 华丽分割线 --------------------------");
        String sql = "SELECT * FROM user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        showResultSetMetaData(resultSetMetaData);

        connection.close();
        preparedStatement.close();
        statement.close();
        resultSet.close();

    }
}
