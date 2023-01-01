package dao.factory.impl;

import dao.Sakila.SakilaDAO;
import dao.Sakila.impl.SakilaDAOImpl;
import dao.factory.SakilaDAOFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// 管理Connection
public class SakilaDAOFactoryImpl<T extends SakilaDAO> implements SakilaDAOFactory {

    private T sakilaDAO;
    private Connection connection;

    private void initConnection(String driverName,
                                String url,
                                String username,
                                String password){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    private void initImpl(Class<T> className){
        try {
            Constructor<T> constructor = className.getConstructor(Connection.class);
            sakilaDAO = constructor.newInstance(connection);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void setImpl(Class<T> tClass){
        initImpl(tClass);
    }

    @SuppressWarnings("all")
    public SakilaDAOFactoryImpl(Properties properties){
        String driverName = properties.getProperty("driverName");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        initConnection(driverName, url, username, password);
        String impl = properties.getProperty("impl");
        if (impl != null){
            try {
                Class<T> aClass = (Class<T>) Class.forName(impl);
                setImpl(aClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                sakilaDAO = (T) new SakilaDAOImpl(connection);
            }
        }
    }
    @SuppressWarnings("all")
    public SakilaDAOFactoryImpl(String driverName,
                                String url,
                                String username,
                                String password){
        initConnection(driverName, url, username, password);
        sakilaDAO = (T) new SakilaDAOImpl(connection);
    }
    public SakilaDAOFactoryImpl(Class<T> impl,
                                String driverName,
                                String url,
                                String username,
                                String password){
        this(driverName, url, username, password);
        setImpl(impl);
    }


    @Override
    public T getInstance() {
        return sakilaDAO;
    }

    @Override
    public <T extends SakilaDAO> T getInstance(Class<T> implClazz) {
        return null;
    }
}
