package dao2.sakila.impl;

import dao.beans.Actor;
import dao2.CommonDAO;

import java.util.List;

public class CommonSakilaDAo implements CommonDAO<Actor> {
    @Override
    public List<Actor> selectAll() {
        return null;
    }

    @Override
    public Actor selectById(Integer id) {
        return null;
    }

    @Override
    public int insert(Actor data) {
        return 0;
    }

    @Override
    public int delete(Actor data) {
        return 0;
    }

    @Override
    public int update(Actor data) {
        return 0;
    }
}
