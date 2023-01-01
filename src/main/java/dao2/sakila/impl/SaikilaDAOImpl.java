package dao2.sakila.impl;

import dao.beans.Actor;
import dao2.sakila.SakilaDAO;

import java.util.List;

// 适配器模式
public class SaikilaDAOImpl extends CommonSakilaDAo implements SakilaDAO {
    @Override
    public List<Actor> selectByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Actor> selectByLastName(String lastName) {
        return null;
    }
}
