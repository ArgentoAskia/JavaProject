package dao;

import dao.Sakila.SakilaDAO;
import dao.Sakila.impl.SakilaDAOImpl;
import dao.beans.Actor;
import dao.factory.SakilaDAOFactory;
import dao.factory.impl.SakilaDAOFactoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class DaoTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sakila?useSSL=false";
        String username = "root";
        String password = "sujiewei";

        SakilaDAOFactory sakilaDAOFactory = new SakilaDAOFactoryImpl<>(SakilaDAOImpl.class, driverName, url, username, password);
        SakilaDAO instance = sakilaDAOFactory.getInstance();
        List<Actor> actors = instance.selectAll();
        actors.forEach(System.out::println);
    }
}
