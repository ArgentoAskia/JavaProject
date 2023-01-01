package dao.Sakila.impl;

import com.mchange.v2.beans.BeansUtils;
import dao.Sakila.SakilaDAO;
import dao.beans.Actor;
import dao.beans.ResultSetBeanUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 数据库名命名的DAO里面，只负责执行SQL（SQL执行器、ResultSet、）
public class SakilaDAOImpl implements SakilaDAO {

    private static final Logger logger = Logger.getLogger(SakilaDAOImpl.class);
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;

    // 传入Connection
    public SakilaDAOImpl(Connection connection){
        this.connection = connection;
    }
    // 通过Connection初始化SQL执行器，关闭SQL执行器
    private void initStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            logger.info("初始化Statement出现异常");
            sqlException.printStackTrace();
        }
    }
    private void closeStatement(){
        try {
            if (statement != null && !statement.isClosed()){
                statement.close();
            }
        } catch (SQLException sqlException) {
            logger.info("关闭Statement出现异常");
            sqlException.printStackTrace();
        }
    }
    private void initPreparedStatement(String sql){
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException sqlException) {
            logger.info("初始化preparedStatement出现异常");
            sqlException.printStackTrace();
        }
    }
    private void closePreparedStatement(){
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()){
                preparedStatement.close();
            }
        } catch (SQLException sqlException) {
            logger.info("关闭preparedStatement出现异常");
            sqlException.printStackTrace();
        }
    }
    private void closeResultSet(){
        try {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        } catch (SQLException sqlException) {
            logger.info("关闭resultSet出现异常");
            sqlException.printStackTrace();
        }
    }


    // mybatis主要解
    @Override
    public List<Actor> selectAll() {
        // 切记，根据Effective Java中所述，最好返回空列表而不是null。
        List<Actor> actors = new LinkedList<>();
        initStatement();
        try {
            // 1.SQl和代码高度耦合（动态拼接非常麻烦，在没有idea提示的情况下，你容易写错SQL）
            // 查询
            String sql = "SELECT * FROM actor";
            resultSet = statement.executeQuery(sql);
            // 解析ResultSet
            while (resultSet.next()){
                // beanUtil
                // 按照ResultSet的字段名，来映射我们实体类的名称，允许驼峰命名
                // 将ResultSet的一行转为一个bean对象（任何对象，以名字作为匹配，遵循数据库驼峰命名法）
                Actor actor = ResultSetBeanUtil.resultSetSolveWithColumnNameMatching(resultSet,
                        Actor.class);
                actors.add(actor);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException sqlException) {
            sqlException.printStackTrace();
        }
        closeStatement();
        closeResultSet();
        return actors;
    }

    // dbUtil(轻量级JDBC，ResultSetHandler)
    @Override
    public Actor selectById(Integer id) {
        return null;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/sakila?useSSL=false";
        String username = "root";
        String password = "sujiewei";
        Connection connection = DriverManager.getConnection(url, username, password);
        SakilaDAO sakilaDAO = new SakilaDAOImpl(connection);
        List<Actor> actors = sakilaDAO.selectAll();
        System.out.println(Arrays.toString(actors.toArray()));
    }
}
