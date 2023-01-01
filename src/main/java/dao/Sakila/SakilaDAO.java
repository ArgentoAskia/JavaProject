package dao.Sakila;

import dao.beans.Actor;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DAO接口
public interface SakilaDAO {

    List<Actor> selectAll();
    Actor selectById(Integer id);
}
